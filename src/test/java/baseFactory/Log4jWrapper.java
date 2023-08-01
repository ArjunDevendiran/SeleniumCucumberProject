package baseFactory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Log4jWrapper {

	private static final Logger LOGGER = LogManager.getLogger(Log4jWrapper.class);

	public static void info(String message) {
		LOGGER.info(message);
	}

	public static void warn(String message) {
		LOGGER.warn(message);
	}

	public static void error(String message) {
		LOGGER.error(message);
	}
}
