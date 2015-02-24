package com.task.simple.logger;

import java.io.BufferedWriter;
import java.io.Writer;
import java.util.Calendar;
import java.util.logging.Logger;

import org.junit.Before;
import org.mockito.Mock;

public class FileLoggerTest extends AbstractLoggerTest {

	@Mock
	private BufferedWriter writer;
	@Mock
	private Logger logger;
	
	@Before
	public void setUp(){
		super.setUp();
	}
	
	@Override
	AbstractLogger createLogger() {
		return new ConsoleLogger(){
			@Override
			 Writer getOutputWriter() {
				return writer;
			}
			@Override
			protected
			Logger getLogger(){
				return logger;
			}
			@Override
			protected Calendar getCurrentCalendar(){
				return calendar;
			}
		};
	}
}
