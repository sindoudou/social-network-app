package com.sg.social.app.command;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.PrintStream;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.sg.social.app.domain.User;
import com.sg.social.app.repository.Repository;
import com.sg.social.app.util.AppPrinter;
import com.sg.social.app.util.DefaultAppPrinter;
import com.sg.social.app.util.TimeFormatter;

@RunWith(MockitoJUnitRunner.class)
public class CommandParserTest {
	private static final String USER = "Alice";
	private static final String USER_2 = "Bob";
	private static final String MESSAGE = "I Love the weather today.";
	private static final String POST_COMMAND = USER + " -> " + MESSAGE;
	private static final String FOLLOW_COMMAND = USER + " follows " + USER_2;
	private static final String WALL_COMMAND = USER + " wall";

	private CommandParser commandParser;

	@Mock
	private Repository repository;
	private TimeFormatter timeFormatter = new TimeFormatter();
	private PrintStream printStream;

	@Before
	public void setUp() {
		AppPrinter printer = new DefaultAppPrinter(printStream, timeFormatter);
		commandParser = spy(new CommandParser(repository, printer));
	}

	@Test
	public void executePostCommandTest() throws Exception {
		User user = mock(User.class);
		when(repository.getOrCreateUser(USER)).thenReturn(user);
		commandParser.executeCommand(POST_COMMAND);
		verify(commandParser).getCommand(POST_COMMAND);
	}

	@Test
	public void getPostCommandTest() throws Exception {
		Command returnedCommand = commandParser.getCommand(POST_COMMAND);
		Assert.assertTrue(returnedCommand instanceof PostCommand);
	}

	@Test
	public void getFollowCommandTest() throws Exception {
		Command returnedCommand = commandParser.getCommand(FOLLOW_COMMAND);
		Assert.assertTrue(returnedCommand instanceof FollowCommand);
	}

	@Test
	public void getReadCommandTest() throws Exception {
		Command returnedCommand = commandParser.getCommand(USER);
		Assert.assertTrue(returnedCommand instanceof ReadCommand);
	}

	@Test
	public void getWallCommandTest() throws Exception {
		Command returnedCommand = commandParser.getCommand(WALL_COMMAND);
		Assert.assertTrue(returnedCommand instanceof WallCommand);
	}
}
