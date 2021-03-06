package com.sg.social.app.command;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

import com.sg.social.app.domain.Post;
import com.sg.social.app.domain.User;
import com.sg.social.app.repository.Repository;
import com.sg.social.app.util.AppPrinter;

public class ReadCommand implements Command {
	private static final Pattern REGEX = Pattern.compile("^\\S+$");

	private Repository repository;
	private AppPrinter postDisplayer;

	public ReadCommand(Repository repository, AppPrinter postDisplayer) {
		this.repository = repository;
		this.postDisplayer = postDisplayer;
	}

	@Override
	public void execute(String commandLine) {
		User user = repository.getOrCreateUser(commandLine.trim());
		List<Post> posts = user.getPosts();
		// Sort posts.
		Collections.sort(posts, new Comparator<Post>() {
			@Override
			public int compare(Post p1, Post p2) {
				return p2.getPublishDate().compareTo(p1.getPublishDate());
			}
		});
		for (Post post : posts) {
			postDisplayer.displayPostForUser(post);
		}
	}

	@Override
	public boolean matches(String input) {
		if (StringUtils.isNotBlank(input)) {
			Matcher matcher = REGEX.matcher(input);
			return matcher.find();
		}
		return false;
	}

}
