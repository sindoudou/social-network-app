package com.sg.social.app.command;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

import com.sg.social.app.domain.User;
import com.sg.social.app.repository.Repository;

public class PostCommand implements Command {
	private static final Pattern REGEX = Pattern.compile("^(\\S+) -> (.+)$");

	private Repository repository;

	public PostCommand(Repository repository) {
		this.repository = repository;
	}

	public void execute(String commandLine) {
		Matcher matcher = REGEX.matcher(commandLine);
		matcher.find();

		String userName = matcher.group(1);
		String message = matcher.group(2);

		User user = repository.getOrCreateUser(userName);
		user.addPost(message);
	}

	public boolean matches(String input) {
		if (StringUtils.isNotBlank(input)) {
			Matcher matcher = REGEX.matcher(input);
			return matcher.find();
		}
		return false;
	}

}
