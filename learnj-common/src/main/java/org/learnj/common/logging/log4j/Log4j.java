package org.learnj.common.logging.log4j;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.PatternLayout;

public class Log4j {
	
	public static void initForConsole() {
		initForConsole(Level.ALL);
	}
	
	public static void initForConsole(Level level) {
        ConsoleAppender consoleAppender = new ConsoleAppender();
        consoleAppender.setLayout(new PatternLayout("%d{yyyy-MM-dd HH:mm:ss.SSS}[%-5p][%-25c{1}] - %m%n"));
        consoleAppender.setTarget("System.out");
        consoleAppender.setName("console");
        consoleAppender.activateOptions();
        LogManager.getRootLogger().addAppender(consoleAppender);
        LogManager.getRootLogger().setLevel(level);
	}

}
