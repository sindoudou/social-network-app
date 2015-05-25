package com.sg.social.app.command;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.sg.social.app.domain.User;
import com.sg.social.app.repository.Repository;

@RunWith(MockitoJUnitRunner.class)
public class PostCommandTest {
	private static final String USER = "Alice";
	private static final String MESSAGE = "I Love the weather today.";

	private static final String COMMAND = USER + " -> " + MESSAGE;

	private PostCommand command;
	@Mock
	private Repository repository;

	@Before
	public void setUp() {
		command = new PostCommand(repository);
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
	public void testPost() {
		User user = mock(User.class);

		when(repository.getOrCreateUser(USER)).thenReturn(user);

		command.execute(COMMAND);

		verify(repository).getOrCreateUser(USER);
		verify(user).addPost(MESSAGE);

	}

}
