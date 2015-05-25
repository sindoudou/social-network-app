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
public class WallCommandTest {
	private static final String USER = "Alice";
	private static final String USER_2 = "Bob";

	private static final String COMMAND = USER + " wall";

	private WallCommand command;
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
		command = new WallCommand(repository, printer);
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
	public void testMatchEmptyl() {
		Assert.assertFalse(command.matches(""));
	}

	@Test
	public void testRead() throws UnsupportedEncodingException {
		User user = spy(new User(USER));
		user.setPosts(Arrays.asList(new Post(USER, "I love the weather today", new Date(
				System.currentTimeMillis() - 5 * 1000L)),
				new Post(USER, "I love NY", new Date(System.currentTimeMillis() - 20 * 1000L))));

		User user2 = spy(new User(USER_2));
		user2.setPosts(Arrays.asList(new Post(USER_2, "I love Paris", new Date(System.currentTimeMillis() - 10 * 1000L))));

		user.addFollowing(user2);

		when(repository.getOrCreateUser(USER)).thenReturn(user);
		when(repository.getOrCreateUser(USER_2)).thenReturn(user2);

		command.execute(COMMAND);

		verify(repository).getOrCreateUser(USER);
		verify(user).getPosts();
		verify(user).getFollowingUsers();

		verify(user2).getPosts();

		String result = baos.toString("UTF-8");
		String expectedResult = "Alice - I love the weather today (5 seconds ago)\nBob - I love Paris (10 seconds ago)\nAlice - I love NY (20 seconds ago)\n";
		Assert.assertEquals(expectedResult, result);
	}
}
