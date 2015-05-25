package com.sg.social.app;

import java.util.Scanner;

import com.sg.social.app.command.CommandParser;
import com.sg.social.app.repository.InMemoryRepository;
import com.sg.social.app.repository.Repository;
import com.sg.social.app.util.AppPrinter;
import com.sg.social.app.util.DefaultAppPrinter;
import com.sg.social.app.util.TimeFormatter;

/**
 * Social Networking App - Main class.
 *
 */
public class App {
	private static final String PROMPT = ">";
	private static final Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		Repository repository = new InMemoryRepository();
		AppPrinter printer = new DefaultAppPrinter(System.out, new TimeFormatter());
		CommandParser commandParser = new CommandParser(repository, printer);

		while (true) {
			System.out.print(PROMPT);
			String input = scanner.nextLine();
			commandParser.executeCommand(input);
		}
	}
}
