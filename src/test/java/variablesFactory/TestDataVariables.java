package variablesFactory;

import baseFactory.PropertyLoader;

public class TestDataVariables extends PropertyLoader {

	String fileLocation = "/properties/testData.properties";

	public String TEST_URL = loadProperties(fileLocation).getProperty("test.url");
	public String SEARCH_TEXT = loadProperties(fileLocation).getProperty("search.text");
	public String FILTER_TYPE = loadProperties(fileLocation).getProperty("filter.type");
	public String FILTER_OPTION = loadProperties(fileLocation).getProperty("filter.option");
}
