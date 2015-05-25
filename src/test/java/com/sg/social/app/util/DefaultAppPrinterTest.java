package com.sg.social.app.util;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.Date;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import com.sg.social.app.domain.Post;

@RunWith(MockitoJUnitRunner.class)
public class DefaultAppPrinterTest {
	private DefaultAppPrinter printer;
	private TimeFormatter timeFormatter = new TimeFormatter();
	private PrintStream printStream;
	private ByteArrayOutputStream baos;

	@Before
	public void setUp() {
		baos = new ByteArrayOutputStream();
		printStream = new PrintStream(baos);
		printer = new DefaultAppPrinter(printStream, timeFormatter);
	}

	@Test
	public void testDisplayPostForWall() throws UnsupportedEncodingException {
		Post post = new Post("Alice", "I love the weather today", new Date(System.currentTimeMillis() - 5 * 1000L));
		printer.displayPostForWall(post);
		String expectedResult = "Alice - I love the weather today (5 seconds ago)\n";
		Assert.assertEquals(expectedResult, baos.toString("UTF-8"));
	}

	@Test
	public void testDisplayPostForUser() throws UnsupportedEncodingException {
		Post post = new Post("Alice", "I love the weather today", new Date(System.currentTimeMillis() - 5 * 1000L));
		printer.displayPostForUser(post);
		String expectedResult = "I love the weather today (5 seconds ago)\n";
		Assert.assertEquals(expectedResult, baos.toString("UTF-8"));
	}

	@Test
	public void testDisplayMessage() throws UnsupportedEncodingException {
		String message = "Blah";
		printer.displayMessage(message);
		Assert.assertEquals(message + "\n", baos.toString("UTF-8"));
	}
}
