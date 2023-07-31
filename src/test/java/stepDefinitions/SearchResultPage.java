package stepDefinitions;

import baseFactory.UtilFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjectFactory.HomePageObjectFactory;
import pageObjectFactory.SearchResultPageObjectFactory;

public class SearchResultPage extends UtilFactory {

	HomePageObjectFactory homePage = new HomePageObjectFactory();
	SearchResultPageObjectFactory searchResultPage = new SearchResultPageObjectFactory();

	@Before
	public void browserStart() {

		// Setup Browser
		browserSetup();
	}

	@After
	public void browserEbd() {

		teardown();
	}

	@Given("User is in home page {string}")
	public void user_is_in_home_page(String url) {

		// Load URL
		homePage.loadHomePage(url);
	}

	@When("User enters search keyword {string} in search box")
	public void user_enters_search_keyword_in_search_box(String searchKeyword) {

		// Enter search Keyword
		homePage.enterTextInSearchField(searchKeyword);
	}

	@When("Clicks search submit button")
	public void clicks_search_submit_button() {

		// Click Search submit button
		homePage.clickSearchSubmitButton();
	}

	@Then("Validate expected search keyword {string} is displayed in Search result page")
	public void validate_expected_search_keyword_is_displayed_in_search_result_page(String expectedText) {

		// Validate searched text in SRP
		searchResultPage.validateTextInSearchResultInfoBar(expectedText);
	}

	@When("User selects {string} filter option under {string} filter type")
	public void user_selects_filter_option_under_filter_type(String filterOption, String filterType) throws Exception {

		// Scroll to filter type and select filter option
		searchResultPage.scrollToFilterSection(filterType);
		searchResultPage.selectFilterOption(filterType, filterOption);
	}

	@Then("Validate all the products in list displays price")
	public void validate_all_the_products_in_list_displays_price() {

		// Validate all the products display price
		searchResultPage.validateAllSrpProductPricesAreDisplayed(true);
	}

	@Then("Print all the names and price of products, ordered from low to high price")
	public void print_all_the_names_and_price_of_products_ordered_from_low_to_high_price() {

		// Print all the product's name and their price in console
		searchResultPage.printAllSrpProductNamesAndPriceOrderedFromPriceLowToHigh();
	}

	
	/* 
	 * These step definitions uses DataTable to control Test Data
	 * 
	 * @Given("User is in home page <url>") public void
	 * user_is_in_home_page_url(DataTable dataTable) {
	 * 
	 * String url = dataTable.asList().get(0);
	 * 
	 * // Load URL homePage.loadHomePage(url); }
	 * 
	 * @When("User enters search keyword <searchKeyword> in search box") public void
	 * user_enters_search_keyword_search_keyword_in_search_box(DataTable dataTable)
	 * {
	 * 
	 * String searchKeyword = dataTable.asList().get(0);
	 * 
	 * // Enter search Keyword homePage.enterTextInSearchField(searchKeyword); }
	 * 
	 * @When("Clicks search submit button") public void
	 * clicks_search_submit_button() { // Click Search submit button
	 * homePage.clickSearchSubmitButton(); }
	 * 
	 * @Then("Validate expected search keyword <expectedText> is displayed in Search result page"
	 * ) public void
	 * validate_expected_search_keyword_expected_text_is_displayed_in_search_result_page
	 * (DataTable dataTable) {
	 * 
	 * String expectedText = dataTable.asList().get(0);
	 * 
	 * // Validate searched text in SRP
	 * searchResultPage.validateTextInSearchResultInfoBar(expectedText); }
	 * 
	 * @When("User selects <filterOption> filter option under <filterType> filter type"
	 * ) public void
	 * user_selects_filter_option_filter_option_under_filter_type_filter_type(
	 * DataTable dataTable) throws Exception {
	 * 
	 * String filterOption = dataTable.asList().get(0); String filterType =
	 * dataTable.asList().get(1);
	 * 
	 * // Scroll to filter type and select filter option
	 * searchResultPage.scrollToFilterSection(filterType);
	 * searchResultPage.selectFilterOption(filterType, filterOption); }
	 * 
	 * @Then("Validate all the products in list displays price") public void
	 * validate_all_the_products_in_list_displays_price() {
	 * 
	 * // Validate all the products display price
	 * searchResultPage.validateAllSrpProductPricesAreDisplayed(true); }
	 * 
	 * @Then("Print all the names and price of products, ordered from low to high price"
	 * ) public void
	 * print_all_the_names_and_price_of_products_ordered_from_low_to_high_price() {
	 * 
	 * // Print all the product's name and their price in console
	 * searchResultPage.printAllSrpProductNamesAndPriceOrderedFromPriceLowToHigh();
	 * }
	 * 
	 */
}
