package com.task.simple.logger;

import java.io.IOException;
import java.io.Writer;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class AbstractLogger implements MyLogger {
    protected static final String LOG_PATTERN = "{%d}::{%s}::{%s}";
    protected static final String TIME_PATTERN = "%04d-%02d-%02dT%02d:%02d:%02d+%02d:00"; // 2015-02-02T01:43:19+00:00

    @Override
    public void log(int info, String message) {
        Writer writer = null;
        try {
            writer = getOutputWriter();
        } catch (IOException e1) {
            getLogger().log(Level.WARNING, "Log stream could not be opened", e1);
        }
        try {
            writer.write(createLogMessage(info, message));
            writer.flush();
        } catch (IOException e) {
            getLogger().log(Level.WARNING, "Log message was not written", e);
        } finally {
            try {
                writer.close();
            } catch (IOException e) {
                getLogger().log(Level.WARNING, "Log output stream was not closed correctly", e);
            }
        }
    }

    protected String createLogMessage(final int level, final String message) {
        return String.format(LOG_PATTERN, level, getISO8901Time(), message);
    }

    abstract Writer getOutputWriter() throws IOException;

    protected Logger getLogger() {
        return Logger.getLogger(this.getClass().getName());
    }

    protected String getISO8901Time() {
        Calendar calendar = getCurrentCalendar();
        return String.format(TIME_PATTERN, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH),
                calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), calendar.get(Calendar.SECOND),
                TimeUnit.HOURS.convert(calendar.get(Calendar.ZONE_OFFSET), TimeUnit.MILLISECONDS));
    }
    
    protected Calendar getCurrentCalendar(){
    	return Calendar.getInstance();
    }
}
