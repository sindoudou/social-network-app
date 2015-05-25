package com.sg.social.app.command;

import java.util.ArrayList;
import java.util.List;

import com.sg.social.app.repository.Repository;
import com.sg.social.app.util.AppPrinter;

public class CommandParser {
	private List<Command> commands = new ArrayList<>();
	private Command invalidCommand;

	public CommandParser(Repository repository, AppPrinter appPrinter) {
		commands.add(new ExitCommand(appPrinter));
		commands.add(new PostCommand(repository));
		commands.add(new ReadCommand(repository, appPrinter));
		commands.add(new FollowCommand(repository));
		commands.add(new WallCommand(repository, appPrinter));

		invalidCommand = new InvalidCommand(appPrinter);
	}

	public void executeCommand(String commandLine) {
		Command command = getCommand(commandLine);
		command.execute(commandLine);
	}

	public Command getCommand(String commandLine) {
		for (Command command : commands) {
			if (command.matches(commandLine))
				return command;
		}
		return invalidCommand;
	}

}
