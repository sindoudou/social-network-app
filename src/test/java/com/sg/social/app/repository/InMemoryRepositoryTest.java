package com.sg.social.app.repository;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.sg.social.app.domain.User;

public class InMemoryRepositoryTest {
	private static final String USER = "Alice";

	private InMemoryRepository repository;

	@Before
	public void setUp() {
		repository = new InMemoryRepository();
	}

	@Test
	public void testGetOrCreateUser() {
		User user = repository.getOrCreateUser(USER);
		Assert.assertNotNull(user);
		Assert.assertEquals(USER, user.getName());
	}

	@Test
	public void testGetExistingUser() {
		User user = repository.getOrCreateUser(USER);
		User existingUser = repository.getOrCreateUser(USER);
		Assert.assertEquals(user, existingUser);
	}
}
