package com.sg.social.app.command;

public interface Command {
	/**
	 * Executes the input command string
	 * 
	 * @param commandLine
	 *            Command string to execute
	 * @return the result of the command
	 */
	public void execute(String commandLine);

	/**
	 * Checks if the given command is eligible to execute the given string
	 * 
	 * @param input
	 *            command string which is able to be executed with this command
	 * @return
	 */
	boolean matches(String input);
}
