package com.sg.social.app.command;

import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Date;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.sg.social.app.domain.Post;
import com.sg.social.app.domain.User;
import com.sg.social.app.repository.Repository;
import com.sg.social.app.util.AppPrinter;
import com.sg.social.app.util.DefaultAppPrinter;
import com.sg.social.app.util.TimeFormatter;

@RunWith(MockitoJUnitRunner.class)
public class ReadCommandTest {
	private static final String USER = "Alice";
	private ReadCommand command;
	@Mock
	private Repository repository;
	private TimeFormatter timeFormatter = new TimeFormatter();
	private PrintStream printStream;
	private ByteArrayOutputStream baos;

	@Before
	public void setUp() {
		baos = new ByteArrayOutputStream();
		printStream = new PrintStream(baos);
		AppPrinter printer = new DefaultAppPrinter(printStream, timeFormatter);
		command = new ReadCommand(repository, printer);
	}

	@Test
	public void testMatch() {
		Assert.assertTrue(command.matches(USER));
	}

	@Test
	public void testMatchNull() {
		Assert.assertFalse(command.matches(null));
	}

	@Test
	public void testMatchEmptyl() {
		Assert.assertFalse(command.matches(""));
	}

	@Test
	public void testRead() throws UnsupportedEncodingException {
		User user = spy(new User(USER));
		user.setPosts(Arrays.asList(new Post(USER, "I love the weather today", new Date(
				System.currentTimeMillis() - 5 * 1000L)),
				new Post(USER, "I love NY", new Date(System.currentTimeMillis() - 10 * 1000L))));

		when(repository.getOrCreateUser(USER)).thenReturn(user);

		command.execute(USER);

		verify(repository).getOrCreateUser(USER);
		verify(user).getPosts();

		String result = baos.toString("UTF-8");
		String expectedResult = "I love the weather today (5 seconds ago)\nI love NY (10 seconds ago)\n";
		Assert.assertEquals(expectedResult, result);
	}
}
