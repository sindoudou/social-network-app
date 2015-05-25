package com.sg.social.app.domain;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class UserTest {
	private User user;

	@Before
	public void setup() {
		user = new User("Alice");
	}

	@Test
	public void testPost() {
		user.addPost("I Love the weather today");
		Assert.assertEquals(1, user.getPosts().size());
		Assert.assertEquals("I Love the weather today", user.getPosts().get(0).getMessage());
		Assert.assertEquals("Alice", user.getPosts().get(0).getUserName());
	}

	@Test
	public void testFollowing() {
		user.addFollowing(new User("Bob"));
		Assert.assertEquals(1, user.getFollowingUsers().size());
		Assert.assertEquals("Bob", user.getFollowingUsers().get(0).getName());
	}

	@Test
	public void testUniqueFollowing() {
		user.addFollowing(new User("Bob"));
		user.addFollowing(new User("Bob"));
		Assert.assertEquals(1, user.getFollowingUsers().size());
	}

	@Test
	public void testSetFollowing() {
		user.setFollowingUsers(Arrays.asList(new User("Bob")));
		Assert.assertEquals(1, user.getFollowingUsers().size());
	}

}
