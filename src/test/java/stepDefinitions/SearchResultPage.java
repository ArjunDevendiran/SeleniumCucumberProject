package stepDefinitions;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjectFactory.UtilFactory;

public class SearchResultPage extends UtilFactory {

	WebDriver driver = null;

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

		try {
			// Loading Test url
			loadUrl(url);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@When("user enters a <searchKeyword> in search box")
	public void user_enters_a_search_keyword_in_search_box() {

		String locatorValue = "//*[@placeholder='Search Amazon']";
		String searchText = "phone case";

		try {
			enterString(LOCATOR_TYPE_XPATH, locatorValue, searchText);

		} catch (Exception e) {
			e.printStackTrace();
		}

//		// Entering Search keyword in Search field
//		driver.findElement(By.xpath("//*[@placeholder='Search Amazon']")).sendKeys("phone case");
	}

	@When("clicks search submit button")
	public void clicks_search_submit_button() {

		String locatorValue = "//*[@id='nav-search-submit-button']";

		try {
			// Clicking Search submit link
			click(LOCATOR_TYPE_XPATH, locatorValue);

		} catch (Exception e) {
			e.printStackTrace();
		}

		// Clicking Search submit link
//		driver.findElement(By.xpath("//*[@id='nav-search-submit-button']")).click();
	}

	@Then("Validate <expectedText> is displayed in Search result page")
	public void validate_expected_text_is_displayed_in_search_result_page() {

		String locatorValue = "//*[contains(@data-component-type,'result-info-bar')]";

		// Defining the expected text
		String expectedText = "phone case";

		try {
			// Fetching the validation text from AUT
			String actualText = getText(LOCATOR_TYPE_XPATH, locatorValue);

			validateTextContains(actualText, expectedText);
		} catch (Exception e) {
			e.printStackTrace();
		}

//		// Fetching the validation text from AUT
//		String actualText = driver.findElement(By.xpath("//*[contains(@data-component-type,'result-info-bar')]"))
//				.getText();
//
//		// Defining the expected text
//		String expectedText = "phone case";
//
//		// Validating Expected text matches the actual text displayed
//		actualText.contains(expectedText);
	}

	@When("user selects <filterOption> filter option under <filterType> filter")
	public void user_selects_filter_option_filter_option_under_filter_type_filter() throws Exception {

		String materiallocatorValue = "//*[@id='filters']//*[text()='Material']";
		String materialOptionlocatorValue = "//*[@id='filters']//*[text()='Material']//ancestor::div[contains(@id,'_browse-bin-title')]//following-sibling::*[position()=1]//*[@aria-label='Carbon Fiber']//a";

		try {
			scrollElementIntoView(LOCATOR_TYPE_XPATH, materiallocatorValue);
			click(LOCATOR_TYPE_XPATH, materialOptionlocatorValue);
		} catch (Exception e) {
			e.printStackTrace();
		}

//		// Finding the Filter option to scroll into view
//		WebElement eleToView = driver.findElement(By.xpath("//*[@id='filters']//*[text()='Material']"));
//
//		// Scrolling to the Filter option by using Javascript executor
//		JavascriptExecutor js = ((JavascriptExecutor) driver);
//		js.executeScript("arguments[0].scrollIntoView();", eleToView);
//
//		// Clicking the Filter option
//		driver.findElement(By.xpath(
//				"//*[@id='filters']//*[text()='Material']//ancestor::div[contains(@id,'_browse-bin-title')]//following-sibling::*[position()=1]//*[@aria-label='Carbon Fiber']//a"))
//				.click();
//		Thread.sleep(2000);
	}

	@Then("Validate all the product list displays price")
	public void validate_all_the_product_list_displays_price() {

		boolean ProductTilesDisplayPrice = true;

		String pdtTileLocatorValue = "//*[contains(@cel_widget_id,'MAIN-SEARCH_RESULTS')]";

		// Getting the list of product tiles
		List<WebElement> productsList = getElementList(LOCATOR_TYPE_XPATH, pdtTileLocatorValue);

		String priceLocatorStart = "(//*[contains(@cel_widget_id,'MAIN-SEARCH_RESULTS')]//*[@data-a-color='base']//*[@class='a-offscreen'])[";
		String LocatorEnd = "]";
		String attributeName = "innerText";

		for (int i = 1; i < productsList.size(); i++) {

			String productPriceLocatorValue = priceLocatorStart + i + LocatorEnd;

			// Getting the price of each product
			String priceText = getAttributeValue(LOCATOR_TYPE_XPATH, productPriceLocatorValue, attributeName);

			// Validating whether the price text is displayed or not
			if (!priceText.isEmpty()) {
				System.out.println("Product tile at index position - " + i + " is displaying price.");
			} else {
				ProductTilesDisplayPrice = false;
				System.out.println("Product tile at index position - " + i + " is not displaying price.");
			}
		}

		// Printing the overall status of price display
		if (ProductTilesDisplayPrice) {
			System.out.println("All the Product tiles are displaying price.");
		} else {
			System.out.println("Few Product tiles are not displaying price.");
		}

//		 Getting the list of product tiles
//		List<WebElement> productsList = driver
//				.findElements(By.xpath("//*[contains(@cel_widget_id,'MAIN-SEARCH_RESULTS')]"));

//		for (int i = 1; i < productsList.size(); i++) {
//
//			// Getting the price element of each product tile
//			WebElement productPrice = productsList.get(i).findElement(By.xpath(
//					"(//*[contains(@cel_widget_id,'MAIN-SEARCH_RESULTS')]//*[@data-a-color='base']//*[@class='a-offscreen'])["
//							+ i + "]"));
//
//			// Getting the price text of the price element
//			String priceText = productPrice.getAttribute("innerText");
//
//			// Validating whether the price text is displayed or not
//			if (!priceText.isEmpty()) {
//				System.out.println("Product tile at index position - " + i + " is displaying price.");
//			} else {
//				ProductTilesDisplayPrice = false;
//				System.out.println("Product tile at index position - " + i + " is not displaying price.");
//			}
//		}
//
//		// Printing the overall status of price display
//		if (ProductTilesDisplayPrice) {
//			System.out.println("All the Product tiles are displaying price.");
//		} else {
//			System.out.println("Few Product tiles are not displaying price.");
//		}
	}

	@Then("Print all the names and price of products, ordered from low to high price")
	public void print_all_the_names_and_price_of_products_ordered_from_low_to_high_price() {

		String pdtTileLocatorValue = "//*[contains(@cel_widget_id,'MAIN-SEARCH_RESULTS')]";

		// Getting the list of product tiles
		List<WebElement> productsList = getElementList(LOCATOR_TYPE_XPATH, pdtTileLocatorValue);

		// Create a list to store product information [name, price] as Object array
		List<Object[]> productList = new ArrayList<>();

		String attributeName = "innerText";
		String priceLocatorStart = "(//*[contains(@cel_widget_id,'MAIN-SEARCH_RESULTS')]//*[@data-a-color='base']//*[@class='a-offscreen'])[";
		String nameLocatorStart = "(//*[contains(@cel_widget_id,'MAIN-SEARCH_RESULTS')]//*[contains(@class,'a-color-base a-text-normal')])[";
		String LocatorEnd = "]";

		// Extracting the product names and prices and adding them to productList
		for (int i = 1; i < productsList.size(); i++) {

			String productNameLocatorValue = nameLocatorStart + i + LocatorEnd;
			String productPriceLocatorValue = priceLocatorStart + i + LocatorEnd;

			// Getting the price of each product
			String priceText = getAttributeValue(LOCATOR_TYPE_XPATH, productPriceLocatorValue, attributeName);

			// Getting the price of each product
			String name = getAttributeValue(LOCATOR_TYPE_XPATH, productNameLocatorValue, attributeName);

			// Parsing the price string to double and removing any special characters in it
			double price = Double.parseDouble(priceText.replaceAll("[^0-9.]", ""));

			// Adding the names and prices in productList
			Object[] productArray = new Object[] { name, price };
			productList.add(productArray);

			// Sorting the products based on their prices (low to high)
			productList.sort(Comparator.comparingDouble(SearchResultPage::getPrice));
		}

		System.out.println("==================================================================================");

		// Printing the names and prices of each product, ordered from low to high price
		for (Object[] productData : productList) {
			System.out.println("Product: " + productData[0] + ", Price: $" + productData[1]);
		}

//		// Getting the list of product tiles
//		List<WebElement> productsList = driver
//				.findElements(By.xpath("//*[contains(@cel_widget_id,'MAIN-SEARCH_RESULTS')]"));
//
//		// Create a list to store product information [name, price] as Object array
//		List<Object[]> productList = new ArrayList<>();
//
//		// Extracting the product names and prices and adding them to productList
//		for (int i = 1; i < productsList.size(); i++) {
//			WebElement nameElement = productsList.get(i).findElement(By.xpath(
//					"(//*[contains(@cel_widget_id,'MAIN-SEARCH_RESULTS')]//*[contains(@class,'a-color-base a-text-normal')])["
//							+ i + "]"));
//			WebElement priceElement = productsList.get(i).findElement(By.xpath(
//					"(//*[contains(@cel_widget_id,'MAIN-SEARCH_RESULTS')]//*[@data-a-color='base']//*[@class='a-offscreen'])["
//							+ i + "]"));
//
//			// Getting the name and price text
//			String name = nameElement.getAttribute("innerText");
//			String priceText = priceElement.getAttribute("innerText");
//
//			// Parsing the price string to double and removing any special characters in it
//			double price = Double.parseDouble(priceText.replaceAll("[^0-9.]", ""));
//
//			// Adding the names and prices in productList
//			Object[] productArray = new Object[] { name, price };
//			productList.add(productArray);
//
//			// Sorting the products based on their prices (low to high)
//			productList.sort(Comparator.comparingDouble(SearchResultPage::getPrice));
//		}
//
//		System.out.println("==================================================================================");
//
//		// Printing the names and prices of each product, ordered from low to high price
//		for (Object[] productData : productList) {
//			System.out.println("Product: " + productData[0] + ", Price: $" + productData[1]);
//		}
	}

	// A static method to extract the price from the product array (method
	// reference)
	public static double getPrice(Object[] product) {
		return (double) product[1];
	}

}
