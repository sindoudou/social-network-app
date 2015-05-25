package com.sg.social.app.command;

import com.sg.social.app.util.AppPrinter;

public class InvalidCommand implements Command {

	private AppPrinter postDisplayer;

	public InvalidCommand(AppPrinter postDisplayer) {
		this.postDisplayer = postDisplayer;
	}

	@Override
	public void execute(String commandLine) {
		postDisplayer.displayMessage(String.format("Command: %s is invalid!", commandLine));
	}

	@Override
	public boolean matches(String input) {
		return false;
	}

}
