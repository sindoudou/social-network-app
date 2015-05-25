package com.sg.social.app;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.sg.social.app.command.CommandParserTest;
import com.sg.social.app.command.ExitCommandTest;
import com.sg.social.app.command.FollowCommandTest;
import com.sg.social.app.command.InvalidCommandTest;
import com.sg.social.app.command.PostCommandTest;
import com.sg.social.app.command.ReadCommandTest;
import com.sg.social.app.command.WallCommandTest;
import com.sg.social.app.domain.PostTest;
import com.sg.social.app.domain.UserTest;
import com.sg.social.app.repository.InMemoryRepositoryTest;
import com.sg.social.app.util.DefaultAppPrinterTest;
import com.sg.social.app.util.TimeFormatterTest;

/**
 * Unit test for simple App.
 */
@RunWith(Suite.class)
@SuiteClasses({ CommandParserTest.class, FollowCommandTest.class, PostCommandTest.class, ReadCommandTest.class,
		WallCommandTest.class, PostTest.class, UserTest.class, TimeFormatterTest.class, DefaultAppPrinterTest.class,
		InMemoryRepositoryTest.class, InvalidCommandTest.class, ExitCommandTest.class })
public class AppTest {

}
