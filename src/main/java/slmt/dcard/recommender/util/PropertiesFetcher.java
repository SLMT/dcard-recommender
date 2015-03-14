package slmt.dcard.recommender.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PropertiesFetcher {
	private static Logger logger = Logger.getLogger(PropertiesFetcher.class
			.getName());

	private static final String DEFAULT_CONFIG_FILE = "config.properties";

	static {
		// checks if configuration succeeds
		boolean config = false;

		// gets the customized path of the configuration file
		String path = System.getProperty("config.file");
		if (path != null)
			config = readConfigurationFile(path);

		// read default file if there is no customized path or configuration fails
		if (!config) {
			if (logger.isLoggable(Level.INFO))
				logger.info("use default configuration file");

			config = readConfigurationFile(DEFAULT_CONFIG_FILE);
		}

		// program exits if all fails
		if (!config) {
			if (logger.isLoggable(Level.SEVERE))
				logger.severe("cannot read configuration, program exit");

			System.exit(ErrorExitCode.CONFIG_FILE_READING_FAIL);
		}
	}

	public static String getPropertyAsString(String propertyName) {
		String value = getPropertyValue(propertyName);

		// cannot find property, program exiting
		if (value == null || value.isEmpty()) {
			if (logger.isLoggable(Level.SEVERE))
				logger.severe("cannot find property: " + propertyName
						+ ", program exit");
			System.exit(ErrorExitCode.CANNOT_FIND_NEEDED_CONFIG_VALUE);
		}

		return value;
	}

	public static String getPropertyAsString(String propertyName,
			String defaultValue) {
		String value = getPropertyValue(propertyName);

		// cannot find property, using default value
		if (value == null || value.isEmpty()) {
			if (logger.isLoggable(Level.WARNING))
				logger.warning("cannot find property: " + propertyName
						+ ", using default value: " + defaultValue);
			return defaultValue;
		}

		return value;
	}

	public static int getPropertyAsInteger(String propertyName) {
		String value = getPropertyValue(propertyName);

		// cannot find property, program exiting
		if (value == null || value.isEmpty()) {
			if (logger.isLoggable(Level.SEVERE))
				logger.severe("cannot find property: " + propertyName
						+ ", program exit");
			System.exit(ErrorExitCode.CANNOT_FIND_NEEDED_CONFIG_VALUE);
		}

		// parses it into a integer value
		int intValue;
		try {
			intValue = Integer.parseInt(value);
		} catch (NumberFormatException e) {
			if (logger.isLoggable(Level.SEVERE))
				logger.severe("parsing property: " + propertyName
						+ " into integer fails, program exit");
			System.exit(ErrorExitCode.CANNOT_FIND_NEEDED_CONFIG_VALUE);
			return -1;
		}

		return intValue;
	}

	public static int getPropertyAsInteger(String propertyName, int defaultValue) {
		String value = getPropertyValue(propertyName);

		// cannot find property, using default value
		if (value == null || value.isEmpty()) {
			if (logger.isLoggable(Level.WARNING))
				logger.warning("can't find property: " + propertyName
						+ ", using default value: " + defaultValue);
			return defaultValue;
		}

		// parses it into a integer value
		int intValue;
		try {
			intValue = Integer.parseInt(value);
		} catch (NumberFormatException e) {
			if (logger.isLoggable(Level.WARNING))
				logger.warning("parsing property: " + propertyName
						+ " into integer fails, using default value: "
						+ defaultValue);
			intValue = defaultValue;
		}

		return intValue;
	}

	private static boolean readConfigurationFile(String path) {
		boolean read = false;

		FileInputStream fis = null;
		try {
			fis = new FileInputStream(path);
			System.getProperties().load(fis);
			read = true;
		} catch (IOException e) {
			if (logger.isLoggable(Level.WARNING))
				logger.warning("error reading configuration file, reason: "
						+ e.getMessage());
		} finally {
			try {
				if (fis != null)
					fis.close();
			} catch (IOException e) {
				// do nothing
			}
		}

		return read;
	}

	private static String getPropertyValue(String propertyName) {
		String value = System.getProperty(propertyName);

		if (value == null)
			return null;

		return value.trim();
	}
}
