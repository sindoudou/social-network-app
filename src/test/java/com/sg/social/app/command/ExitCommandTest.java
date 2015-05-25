package com.sg.social.app.command;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import com.sg.social.app.util.AppPrinter;
import com.sg.social.app.util.DefaultAppPrinter;
import com.sg.social.app.util.TimeFormatter;

@RunWith(MockitoJUnitRunner.class)
public class ExitCommandTest {
	private static final String COMMAND = "exit";
	private ExitCommand command;
	private TimeFormatter timeFormatter = new TimeFormatter();
	private PrintStream printStream;
	private ByteArrayOutputStream baos;

	@Before
	public void setUp() {
		baos = new ByteArrayOutputStream();
		printStream = new PrintStream(baos);
		AppPrinter printer = new DefaultAppPrinter(printStream, timeFormatter);
		command = new ExitCommand(printer);

	}

	@Test
	public void testMatch() {
		Assert.assertTrue(command.matches(COMMAND));
	}

	@Test
	public void testMatchNull() {
		Assert.assertFalse(command.matches(null));
	}

	@Test
	public void testMatchEmpty() {
		Assert.assertFalse(command.matches(""));
	}

	// @Test
	public void testExit() throws UnsupportedEncodingException {
		command.execute(COMMAND);
		String result = baos.toString("UTF-8");
		String expectedResult = "Bye!";
		Assert.assertEquals(expectedResult, result);
	}

}
