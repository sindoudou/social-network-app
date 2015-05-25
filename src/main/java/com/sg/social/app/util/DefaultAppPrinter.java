package com.sg.social.app.util;

import java.io.PrintStream;

import com.sg.social.app.domain.Post;

public class DefaultAppPrinter implements AppPrinter {
	private static final String MESSAGE_FORMAT_FOR_WALL = "%s - %s %s";
	private static final String MESSAGE_FORMAT_FOR_USER = "%s %s";

	private PrintStream printStream;
	private TimeFormatter timeFormatter;

	public DefaultAppPrinter(PrintStream printStream, TimeFormatter timeFormatter) {
		this.printStream = printStream;
		this.timeFormatter = timeFormatter;
	}

	@Override
	public void displayPostForWall(Post post) {
		printStream.println(String.format(MESSAGE_FORMAT_FOR_WALL, post.getUserName(), post.getMessage(),
				timeFormatter.formatTimeDifference(post.getPublishDate())));
	}

	@Override
	public void displayPostForUser(Post post) {
		printStream.println(String.format(MESSAGE_FORMAT_FOR_USER, post.getMessage(),
				timeFormatter.formatTimeDifference(post.getPublishDate())));
	}

	@Override
	public void displayMessage(String message) {
		printStream.println(message);
	}

}
