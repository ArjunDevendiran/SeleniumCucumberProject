package stepDefinitions;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;

public class SearchResultPage {

	WebDriver driver = null;

	@Before
	public void browserSetup() {

		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();

		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		driver.manage().window().maximize();
	}

	@After
	public void teardown() throws InterruptedException {

		Thread.sleep(2000);
		driver.close();
		driver.quit();
	}

	@Given("user is in home page")
	public void user_is_in_home_page() {

		driver.navigate().to("https://www.amazon.com/");
	}

	@When("user enters a <searchKeyword> in search box")
	public void user_enters_a_search_keyword_in_search_box() {

		driver.findElement(By.xpath("//*[@placeholder='Search Amazon']")).sendKeys("phone case");

	}

	@When("clicks search submit button")
	public void clicks_search_submit_button() {

		driver.findElement(By.xpath("//*[@id='nav-search-submit-button']")).click();

		// If user wants to click Enter button use this
		// driver.findElement(By.xpath("//*[@placeholder='Search
		// Amazon']")).sendKeys(Keys.ENTER);

	}

	@Then("Validate <expectedText> is displayed in Search result page")
	public void Validate_expectedText_is_displayed_in_Search_result_page() {

		String actualText = driver.findElement(By.xpath("//*[contains(@data-component-type,'result-info-bar')]"))
				.getText();
		
		String expectedText = "phone case";

		actualText.contains(expectedText);
	}

	@When("user selects <filterOption> filter option under <filterType> filter")
	public void user_selects_filter_option_filter_option_under_filter_type_filter() throws Exception {

		WebElement eleToView = driver.findElement(By.xpath("//*[@id='filters']//*[text()='Material']"));

		// Javascript executor
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("arguments[0].scrollIntoView();", eleToView);
		Thread.sleep(800);

		driver.findElement(By.xpath(
				"//*[@id='filters']//*[text()='Material']//ancestor::div[contains(@id,'_browse-bin-title')]//following-sibling::*[position()=1]//*[@aria-label='Carbon Fiber']"))
				.click();
		Thread.sleep(2000);

	}

	@Then("Validate all the product list displays price")
	public void validate_all_the_product_list_displays_price() {

		// Getting the list of product tiles
		List<WebElement> productsList = driver
				.findElements(By.xpath("//*[contains(@cel_widget_id,'MAIN-SEARCH_RESULTS')]"));

		// Validating price for product tiles
		boolean ProductTilesDisplayPrice = true;

		for (int i = 0; i <= productsList.size(); i++) {

			WebElement productPrice = productsList.get(i).findElement(By.xpath(
					"//*[contains(@cel_widget_id,'MAIN-SEARCH_RESULTS')]//*[@data-a-color='base']//*[@class='a-offscreen']"));

			if (!productPrice.isDisplayed()) {
				ProductTilesDisplayPrice = false;
				System.out.println("Product tile at index position - " + i + " is not displaying price.");
			}
		}

		if (ProductTilesDisplayPrice) {
			System.out.println("All the Product tiles are displaying price.");
		} else {
			System.out.println("Few Product tiles are not displaying price.");
		}

	}

	@Then("Print all the names and price of products, ordered from low to high price")
	public void print_all_the_names_and_price_of_products_ordered_from_low_to_high_price() {

		// Getting the list of product tiles
		List<WebElement> productsList = driver
				.findElements(By.xpath("//*[contains(@cel_widget_id,'MAIN-SEARCH_RESULTS')]"));
		
		// Create a list to store product information as Object arrays
        List<Object[]> productList = new ArrayList<>();
        
     // Extract the product names and prices and add them to the list as Object arrays
        for (WebElement product : productsList) {
            WebElement nameElement = product.findElement(By.xpath("//*[contains(@cel_widget_id,'MAIN-SEARCH_RESULTS')]//*[contains(@class,'a-color-base a-text-normal')]"));
            WebElement priceElement = product.findElement(By.xpath("//*[contains(@cel_widget_id,'MAIN-SEARCH_RESULTS')]//*[@data-a-color='base']//*[@class='a-offscreen']"));

            String name = nameElement.getText().trim();
            String priceText = priceElement.getText().trim();
            double price = Double.parseDouble(priceText.replaceAll("[^0-9.]", ""));

            Object[] productArray = new Object[]{name, price};
            productList.add(productArray);
            
         // Sort the products based on their prices (low to high)
            productList.sort(Comparator.comparingDouble(SearchResultPage::getPrice));

            // Print the names and prices of each product, ordered from low to high price
            for (Object[] productData : productList) {
                System.out.println("Product: " + productData[0] + ", Price: $" + productData[1]);
            }
        }
	}
	
	// A static method to extract the price from the product array (method reference)
    public static double getPrice(Object[] product) {
        return (double) product[1];
    }

}
