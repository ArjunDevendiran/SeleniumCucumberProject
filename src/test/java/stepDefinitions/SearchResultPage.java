package stepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjectFactory.HomePageObjectFactory;
import pageObjectFactory.SearchResultPageObjectFactory;
import pageObjectFactory.UtilFactory;

public class SearchResultPage extends UtilFactory {
	
	HomePageObjectFactory homePage = new HomePageObjectFactory();
	SearchResultPageObjectFactory searchResultPage = new SearchResultPageObjectFactory();

	@Before
	public void browserStart() {

		browserSetup();
	}

	@After
	public void browserEbd() {

		teardown();
	}

	@Given("user is in home page")
	public void user_is_in_home_page() {

		String url = "https://www.amazon.com/";
		
		homePage.loadHomePage(url);
	}

	@When("user enters a <searchKeyword> in search box")
	public void user_enters_a_search_keyword_in_search_box() {

		String searchText = "phone case";
		
		homePage.enterTextInSearchField(searchText);
	}

	@When("clicks search submit button")
	public void clicks_search_submit_button() {

		homePage.clickSearchSubmitButton();
	}

	@Then("Validate <expectedText> is displayed in Search result page")
	public void validate_expected_text_is_displayed_in_search_result_page() throws Exception {
		
		String expectedText = "phone case";

		searchResultPage.validateTextInSearchResultInfoBar(expectedText);
	}

	@When("user selects <filterOption> filter option under <filterType> filter")
	public void user_selects_filter_option_filter_option_under_filter_type_filter() throws Exception {

		String filterType = "Material";
		String filterOption = "Carbon Fiber";

		searchResultPage.scrollToFilterSection(filterType);
		searchResultPage.selectFilterOption(filterType, filterOption);
	}

	@Then("Validate all the product list displays price")
	public void validate_all_the_product_list_displays_price() {

		searchResultPage.validateAllSrpProductPricesAreDisplayed(true);
	}

	@Then("Print all the names and price of products, ordered from low to high price")
	public void print_all_the_names_and_price_of_products_ordered_from_low_to_high_price() {

		searchResultPage.printAllSrpProductNamesAndPriceOrderedFromPriceLowToHigh();
	}
}
