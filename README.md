# SeleniumCucumberProject
This is a Maven project with Java, Selenium, and Cucumber technology.

All the details will be provided here shortly.

Branch 1: feature/B1_Create_Maven_Project
- Maven project is set up.

Branch 2: feature/B2_Add_Dependencies_in_POM
- Following Dependencies are added in the pom.xml file
	* Cucumber JVM: Java
	* JUnit
	* Cucumber JVM: JUnit
	* Selenium Java
	* WebDriverManager

- Installed Cucumber Eclipse Plugin.

Branch 3: feature/B3_Create_FF_SD_RC_PF
- Created Feature file - searchResultPage.feature
- Created Step definition Class - searchResultPage.java
- Created Test runner class - TestSearchResultPage.java
- Ran the JUnit test to check if above files are getting connected to each other

Branch 4: feature/B4_Create_Test_Script
- Added Dependency - maven-compiler-plugin in pom.xml file
- Added the following code in the SearchResultPage.java file
	* Created functions with @Before and @After Hooks to hold Driver setup and close methods respectively
	* Defined the logic to perform dedicated actions in each test step. Utilized Java and Selenium APIs. Details mentioned below:
		1. @Given("user is in home page") 
			- Loading URL
		2. @When("user enters a <searchKeyword> in search box")
			- Finding the search field and entering the search keyword
		3. @When("clicks search submit button")
			- Clicking the search submit button
		4. @Then("Validate <expectedText> is displayed in Search result page")
			- Fetching the validation text displayed in AUT and validating it with the expected text
		5. @When("user selects <filterOption> filter option under <filterType> filter")
			- Scrolling to the filter option and clicking it
		6. @Then("Validate all the product list displays price")
			- Storing the list of product tiles from SRP
			- Iterating through the list and getting their prices
			- Validating each product tile contains the price
		7. @Then("Print all the names and price of products, ordered from low to high price")
			- Printing the product name and price in the console

- Note: Removed a few code snippets which are not required	

Branch 5: feature/B5_Restructuring_Code
- Created UtilFactory.java class in pageObjectFactory package - It contains all the generic functions
- Created UtilLocatorFactory.java in pageLocatorFactory package - It contains all the constants of generic functions
- Created SearchPageLocatorFactory.java in pageLocatorFactory package - It contains all the Locators of SearchPage	
- In SearchResultPage.java Replace the raw codes with generic functions from UtilFactory.java

Branch 6: feature/B6_Restructuring_Code
- Created HomePageObjectFactory.java class in pageObjectFactory package - It contains all the functions dedicated to the Home page
- Updated UtilLocatorFactory.java in the pageLocatorFactory package with a few more generic functions
- Updated SearchPageObjectFactory.java to SearchResultPageObjectFactory.java and added all the functions dedicated to the Search result page	
- In SearchResultPage.java Replace the generic functions (called from UtilFactory.java) with the page object functions defined in pageObjectFactory classes

Branch 7: feature/B7_Restructuring_Code
- Created HomePageLoctcatorFactory.java class in pageLocatorFactory package - It contains all the Locators of the Home page elements
- Updated SearchPageLocatorFactory to SearchResultPageLocatorFactory.java class in pageLocatorFactory package - It contains all the Locators of the Search result page elements
- Added common attributes in UtilLocatorFactory.java

Branch 8: feature/B8_Restructuring_Code
- Parameterized Test Data in the steps of searchResultPage.feature
- Added the comments for each step in searchResultPage.java

Branch 9: feature/B9_Implementing_log4j
- Added dependencies - log4j-core and log4j-api
- Created log4j.xml file and a wrapper class to call log4j functions
- Created step definition with DataTable and commented on this code

Branch 10: feature/B10_Code_Refactoring
- Implemented properties file to pass test data from it to the feature file
- Included 3 feature files:
	* file 1 - test data defined in steps
	* file 2 - test data defined in DataTable
	* file 3 - test data defined in properties file
- Created variables package to store test data from properties file
- Updated code formats, removed unintended codes