package com.sg.social.app.domain;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class PostTest {

	@Test
	public void testPost() {
		Post post = new Post("Alice", "I Love the weather today");
		Assert.assertEquals("I Love the weather today", post.getMessage());
		Assert.assertEquals("Alice", post.getUserName());
		Assert.assertNotNull(post.getPublishDate());
	}

}
