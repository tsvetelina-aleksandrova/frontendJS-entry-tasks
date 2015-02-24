package com.task.simple.logger;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.Calendar;
import java.util.logging.Level;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public abstract class AbstractLoggerTest {

	AbstractLogger logger;
	@Mock
	protected Calendar calendar;
	
	protected final String exampleLogMessage = "example message";
	protected final String exampleLogTime = "2015-02-02T01:43:19+00:00";

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		mockCalendar();
		logger = createLogger();
	}

	abstract AbstractLogger createLogger();

	@Test
	public void testLogSuccessful() throws Exception {
		logger.log(LogLevel.INFO, exampleLogMessage);
		final String expectdLoggedMessage = "{1}::" + "{" + exampleLogTime + "}::" + "{" + exampleLogMessage + "}";
		Mockito.verify(logger.getOutputWriter()).write(expectdLoggedMessage);
		Mockito.verify(logger.getOutputWriter()).close();
	}

	@Test
	public void testDataIOS8910Format() {
		assertEquals("Unexpected time and/or time format!", 
				exampleLogTime, logger.getISO8901Time());
	}
	
	@Test
	public void testLogUnsuccessful() throws Exception{
		Mockito.doThrow(new IOException()).when(logger.getOutputWriter()).write(Mockito.anyString());
		logger.log(LogLevel.INFO, exampleLogMessage);
		Mockito.verify(logger.getLogger()).log(Mockito.any(Level.class), Mockito.anyString(), Mockito.any(Exception.class));
		Mockito.verify(logger.getOutputWriter()).close();
	}

	private void mockCalendar() {
		Mockito.stub(calendar.get(Calendar.YEAR)).toReturn(2015);
		Mockito.stub(calendar.get(Calendar.MONTH)).toReturn(2);
		Mockito.stub(calendar.get(Calendar.DAY_OF_MONTH)).toReturn(2);
		Mockito.stub(calendar.get(Calendar.HOUR_OF_DAY)).toReturn(1);
		Mockito.stub(calendar.get(Calendar.MINUTE)).toReturn(43);
		Mockito.stub(calendar.get(Calendar.SECOND)).toReturn(19);
		Mockito.stub(calendar.get(Calendar.ZONE_OFFSET)).toReturn(0);
	}

}
