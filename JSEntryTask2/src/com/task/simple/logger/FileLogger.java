package com.task.simple.logger;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;

public class FileLogger extends AbstractLogger {
    private File logFile;

    public FileLogger(final File logFile) {
        this.logFile = logFile;
    }

    @Override
    Writer getOutputWriter() throws FileNotFoundException {
        return new BufferedWriter(new OutputStreamWriter(new FileOutputStream(logFile)));
    }
}
