package pageObjectFactory;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.openqa.selenium.WebElement;

public class SearchResultPageObjectFactory extends UtilFactory {
	
	public void validateTextInSearchResultInfoBar(String expectedText) {

		String locatorValue = "//*[contains(@data-component-type,'result-info-bar')]";

		try {
			// Fetching the validation text from AUT
			String actualText = getText(LOCATOR_TYPE_XPATH, locatorValue);

			validateTextContains(actualText, expectedText);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void scrollToFilterSection(String filterType) throws Exception {

		String filterSectionlocatorPart = "//*[@id='filters']//*[text()='${text}']";
		
		String filterSectionlocatorValue = getElementReplacementString(filterSectionlocatorPart, filterType);

		try {
			scrollElementIntoView(LOCATOR_TYPE_XPATH, filterSectionlocatorValue);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void selectFilterOption(String filterType, String filterOption) throws Exception {

		String filterOptionlocatorPart = "//*[@id='filters']//*[text()='${text}']//ancestor::div[contains(@id,'_browse-bin-title')]//following-sibling::*[position()=1]//*[@aria-label='${text}']//a";
		
		String filterOptionlocatorValue = getElementReplacementString(filterOptionlocatorPart, filterType, filterOption);

		try {
			click(LOCATOR_TYPE_XPATH, filterOptionlocatorValue);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void validateAllSrpProductPricesAreDisplayed(boolean ProductTilesDisplayPrice) {

		String pdtTileLocatorValue = "//*[contains(@cel_widget_id,'MAIN-SEARCH_RESULTS')]";
		String priceLocatorStart = "(//*[contains(@cel_widget_id,'MAIN-SEARCH_RESULTS')]//*[@data-a-color='base']//*[@class='a-offscreen'])[";
		String LocatorEnd = "]";
		String attributeName = "innerText";
		
		try {
			// Getting the list of product tiles
			List<WebElement> productsList = getElementList(LOCATOR_TYPE_XPATH, pdtTileLocatorValue);

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
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void printAllSrpProductNamesAndPriceOrderedFromPriceLowToHigh() {

		String pdtTileLocatorValue = "//*[contains(@cel_widget_id,'MAIN-SEARCH_RESULTS')]";
		String attributeName = "innerText";
		String priceLocatorStart = "(//*[contains(@cel_widget_id,'MAIN-SEARCH_RESULTS')]//*[@data-a-color='base']//*[@class='a-offscreen'])[";
		String nameLocatorStart = "(//*[contains(@cel_widget_id,'MAIN-SEARCH_RESULTS')]//*[contains(@class,'a-color-base a-text-normal')])[";
		String LocatorEnd = "]";

		try {
			// Getting the list of product tiles
			List<WebElement> productsList = getElementList(LOCATOR_TYPE_XPATH, pdtTileLocatorValue);

			// Create a list to store product information [name, price] as Object array
			List<Object[]> productList = new ArrayList<>();

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
				productList.sort(Comparator.comparingDouble(SearchResultPageObjectFactory::getPrice));
			}
			System.out.println("==================================================================================");

			// Printing the names and prices of each product, ordered from low to high price
			for (Object[] productData : productList) {
				System.out.println("Product: " + productData[0] + ", Price: $" + productData[1]);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// A static method to extract the price from the product array (method reference)
	public static double getPrice(Object[] product) {
		return (double) product[1];
	}

}
