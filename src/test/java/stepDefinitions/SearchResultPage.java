package stepDefinitions;

import baseFactory.UtilFactory;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjectFactory.HomePageObjectFactory;
import pageObjectFactory.SearchResultPageObjectFactory;
import variablesFactory.TestDataVariables;

public class SearchResultPage extends UtilFactory {

	HomePageObjectFactory homePage = new HomePageObjectFactory();
	SearchResultPageObjectFactory searchResultPage = new SearchResultPageObjectFactory();
	TestDataVariables testDataConstants = new TestDataVariables();

	@Before
	public void browserStart() {

		// Setup Browser
		browserSetup();
	}

	@After
	public void browserEnd() {

		// Browser terminated gracefully
		teardown();
	}

	@Given("^User is in home page$")
	public void userInHomePage() {

		String url = testDataConstants.TEST_URL;

		// Load URL
		homePage.loadHomePage(url);
	}

	@Given("^User is in home page <url>$")
	public void userInHomePage(DataTable dataTable) {

		String url = dataTable.asList().get(0);

		// Load URL
		homePage.loadHomePage(url);
	}

	@Given("User is in home page {string}")
	public void userInHomePage(String url) {

		// Load URL
		homePage.loadHomePage(url);
	}

	@When("^User enters search keyword in search box$")
	public void userEntersSearchKeywordInSearchBox() {

		String searchKeyword = testDataConstants.SEARCH_TEXT;

		// Enter search Keyword
		homePage.enterTextInSearchField(searchKeyword);
	}

	@When("^User enters search keyword <searchKeyword> in search box$")
	public void userEntersSearchKeywordInSearchBox(DataTable dataTable) {

		String searchKeyword = dataTable.asList().get(0);

		// Enter search Keyword
		homePage.enterTextInSearchField(searchKeyword);
	}

	@When("User enters search keyword {string} in search box")
	public void userEntersSearchKeywordInSearchBox(String searchKeyword) {

		// Enter search Keyword
		homePage.enterTextInSearchField(searchKeyword);
	}

	@When("^Clicks search submit button$")
	public void clicksSearchSubmitButton() {

		// Click Search submit button
		homePage.clickSearchSubmitButton();
	}

	@Then("^Validate expected search keyword is displayed in Search result page$")
	public void validateExpectedSearchKeywordIsDisplayedInSrp() {

		String expectedText = testDataConstants.SEARCH_TEXT;

		// Validate searched text in SRP
		searchResultPage.validateTextInSearchResultInfoBar(expectedText);
		searchResultPage.validateExpectedTextInPdtNamesInSrp(expectedText);
	}

	@Then("^Validate expected search keyword <expectedText> is displayed in Search result page$")
	public void validateExpectedSearchKeywordIsDisplayedInSrp(DataTable dataTable) {

		String expectedText = dataTable.asList().get(0);

		// Validate searched text in SRP
		searchResultPage.validateTextInSearchResultInfoBar(expectedText);
		searchResultPage.validateExpectedTextInPdtNamesInSrp(expectedText);
	}

	@Then("Validate expected search keyword {string} is displayed in Search result page")
	public void validateExpectedSearchKeywordIsDisplayedInSrp(String expectedText) {

		// Validate searched text in SRP
		searchResultPage.validateTextInSearchResultInfoBar(expectedText);
		searchResultPage.validateExpectedTextInPdtNamesInSrp(expectedText);
	}

	@When("^User selects filter option under filter type$")
	public void userSelectsFilterOptionUnderFilterType() {

		String filterType = testDataConstants.FILTER_TYPE;
		String filterOption = testDataConstants.FILTER_OPTION;

		// Scroll to filter type and select filter option
		searchResultPage.scrollToFilterSection(filterType);
		searchResultPage.selectFilterOption(filterType, filterOption);
	}

	@When("^User selects <filterOption> filter option under <filterType> filter type$")
	public void userSelectsFilterOptionUnderFilterType(DataTable dataTable) {

		String filterOption = dataTable.asList().get(0);
		String filterType = dataTable.asList().get(1);

		// Scroll to filter type and select filter option
		searchResultPage.scrollToFilterSection(filterType);
		searchResultPage.selectFilterOption(filterType, filterOption);
	}

	@When("User selects {string} filter option under {string} filter type")
	public void userSelectsFilterOptionUnderFilterType(String filterOption, String filterType) {

		// Scroll to filter type and select filter option
		searchResultPage.scrollToFilterSection(filterType);
		searchResultPage.selectFilterOption(filterType, filterOption);
	}

	@Then("^Validate all the products in list displays price$")
	public void validateAllProductsPriceIsDisplayed() {

		// Validate all the products display price
		searchResultPage.validateAllSrpProductPricesAreDisplayed(true);
	}

	@Then("^Print all the names and price of products, ordered from low to high price$")
	public void printAllProductNamesAndPriceOrderedLowToHighPrice() {

		// Print all the product's name and their price in console
		searchResultPage.printAllSrpProductNamesAndPriceOrderedFromPriceLowToHigh();
	}
}
