package com.sg.social.app.command;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
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
public class FollowCommandTest {
	private static final String USER_1 = "Alice";
	private static final String USER_2 = "Bob";
	private static final String COMMAND = USER_1 + " follows " + USER_2;

	private FollowCommand command;
	@Mock
	private Repository repository;

	@Before
	public void setUp() {
		command = new FollowCommand(repository);
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
	public void testFollow() {
		User user1 = spy(new User(USER_1));
		User user2 = mock(User.class);

		when(repository.getOrCreateUser(USER_1)).thenReturn(user1);
		when(repository.getOrCreateUser(USER_2)).thenReturn(user2);

		command.execute(COMMAND);

		verify(repository).getOrCreateUser(USER_1);
		verify(repository).getOrCreateUser(USER_2);
		verify(user1).addFollowing(user2);
		Assert.assertTrue(user1.getFollowingUsers().contains(user2));
	}

}
