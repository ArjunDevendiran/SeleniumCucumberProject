package baseFactory;

import java.util.Properties;

public class PropertyLoader {

	Properties properties = new Properties();

	public Properties loadProperties(String propertiesFile) {

		try {
			properties.load(getClass().getResourceAsStream(propertiesFile));

			Log4jWrapper.info("Loaded properties file");
		} catch (Exception e) {
			Log4jWrapper.error("Could not loaded properties file");
			e.printStackTrace();
		}
		return properties;
	}
}
