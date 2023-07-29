package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class SearchResultPage {

	@Given("user is in home page")
	public void user_is_in_home_page() {
	    
		System.out.println(" ====================== start" );
		// Write code here that turns the phrase above into concrete actions

	}

	@When("user enters a <searchKeyword> in search box")
	public void user_enters_a_search_keyword_in_search_box() {
	    // Write code here that turns the phrase above into concrete actions

	}
	
	@When("clicks search submit button")
	public void clicks_search_submit_button() {
	    // Write code here that turns the phrase above into concrete actions

	}

	@When("user is navigated to search results page")
	public void user_is_navigated_to_search_results_page() {
	    // Write code here that turns the phrase above into concrete actions

	}

	@Then("Validate the results for <searchKeyword> are showing in SRP")
	public void validate_the_results_for_search_keyword_are_showing_in_srp() {
	    // Write code here that turns the phrase above into concrete actions

	}

	@When("user selects <filterOptiob> filter option under <filterType> filter")
	public void user_selects_filter_optiob_filter_option_under_filter_type_filter() {
	    // Write code here that turns the phrase above into concrete actions

	}

	@Then("Validate all the product list displays price")
	public void validate_all_the_product_list_displays_price() {
	    // Write code here that turns the phrase above into concrete actions

	}

	@Then("Print all the names and price of products, ordered from low to high price")
	public void print_all_the_names_and_price_of_products_ordered_from_low_to_high_price() {
		
		System.out.println(" ====================== end" );
	    // Write code here that turns the phrase above into concrete actions

	}

}
