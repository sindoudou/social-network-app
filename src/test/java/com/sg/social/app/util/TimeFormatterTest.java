package com.sg.social.app.util;

import java.util.Date;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TimeFormatterTest {
	private TimeFormatter formatter;

	@Before
	public void setUp() {
		formatter = new TimeFormatter();
	}

	@Test
	public void formatTimeDifferenceTest() {
		Date date = new Date();
		String response = formatter.formatTimeDifference(date);
		Assert.assertEquals("(0 second ago)", response);
	}

	@Test
	public void formatTimeDifferenceTestWithMinutes() {
		Date date = new Date(System.currentTimeMillis() - 10 * 60 * 1000L);
		String response = formatter.formatTimeDifference(date);
		Assert.assertEquals("(10 minutes ago)", response);
	}

	@Test
	public void formatTimeDifferenceTestWithHours() {
		Date date = new Date(System.currentTimeMillis() - 1 * 60 * 60 * 1000L);
		String response = formatter.formatTimeDifference(date);
		Assert.assertEquals("(1 hour ago)", response);
	}

	@Test
	public void formatTimeDifferenceTestWithDays() {
		Date date = new Date(System.currentTimeMillis() - 24 * 60 * 60 * 1000L);
		String response = formatter.formatTimeDifference(date);
		Assert.assertEquals("(1 day ago)", response);
	}
}
