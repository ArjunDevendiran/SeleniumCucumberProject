# SeleniumCucumberProject
This is a Maven project with Java, Selenium and Cucumber technology.

All the details will be provided here shortly.

Branch 1: feature/B1_Create_Maven_Project
- Maven project is setup.

Branch 2: feature/B2_Add_Dependencies_in_POM
- Following Dependancies are added in pom.xml file
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
- Added Dependancy - maven-compiler-plugin in pom.xml file
- Added following code in SearchResultPage.java file
	* Created functions with @Before and @After Hooks to hold Driver setup and close methods respectively
	* Defined the logic to perform dedicated actions in each test steps. Utilized Java and Selenium APIs. Details mentioned below:
		1. @Given("user is in home page") 
			- Loading url
		2. @When("user enters a <searchKeyword> in search box")
			- Finding the search field and entering search keyword
		3. @When("clicks search submit button")
			- Clicking search submit button
		4. @Then("Validate <expectedText> is displayed in Search result page")
			- Fetching the validation text displayed in AUT and validating it with the expected text
		5. @When("user selects <filterOption> filter option under <filterType> filter")
			- Scrolling to the filter option and clicking it
		6. @Then("Validate all the product list displays price")
			- Storing the list of product tiles from SRP
			- Iterating through the list and getting their prices
			- Validating each product tile contains price
		7. @Then("Print all the names and price of products, ordered from low to high price")
			- Printing the product name and price in console

- Note: Removed few code snippets which are not required			