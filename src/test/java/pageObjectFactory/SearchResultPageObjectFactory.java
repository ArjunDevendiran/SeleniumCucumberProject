package pageObjectFactory;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.openqa.selenium.WebElement;

import pageLocatorFactory.SearchResultPageLocatorFactory;

public class SearchResultPageObjectFactory extends UtilFactory {
	
	SearchResultPageLocatorFactory searchResultPageLocator = new SearchResultPageLocatorFactory();
	
	public void validateTextInSearchResultInfoBar(String expectedText) {

		String searchResultInfoBarlocatorValue = searchResultPageLocator.XPATH_SEARCH_RESULT_INFO_BAR;

		try {
			String actualText = getText(LOCATOR_TYPE_XPATH, searchResultInfoBarlocatorValue);

			validateTextContains(actualText, expectedText);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void scrollToFilterSection(String filterType) throws Exception {

		String filterSectionlocatorPart = searchResultPageLocator.XPATH_FILTER_SECTION_LOCATOR_PART;
		
		String filterSectionlocatorValue = getElementReplacementString(filterSectionlocatorPart, filterType);

		try {
			scrollElementIntoView(LOCATOR_TYPE_XPATH, filterSectionlocatorValue);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void selectFilterOption(String filterType, String filterOption) throws Exception {

		String filterSectionlocatorPart = searchResultPageLocator.XPATH_FILTER_SECTION_LOCATOR_PART;
		String filterOptionlocatorPart = searchResultPageLocator.XPATH_FILTER_OPTION_LOCATOR_PART;
		
		String combinedLocatorValue = filterSectionlocatorPart + filterOptionlocatorPart;
		
		String filterOptionlocatorValue = getElementReplacementString(combinedLocatorValue, filterType, filterOption);

		try {
			click(LOCATOR_TYPE_XPATH, filterOptionlocatorValue);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void validateAllSrpProductPricesAreDisplayed(boolean ProductTilesDisplayPrice) {

		String LocatorOpen = LOCATOR_SQUARE_BRACKET_OPEN;
		String LocatorClose = LOCATOR_SQUARE_BRACKET_CLOSE;
		String attributeName = ATTRIBUTE_INNER_TEXT;
		
		String pdtTileLocatorValue = searchResultPageLocator.XPATH_PRODUCT_TILES;
		String priceLocatorPart = searchResultPageLocator.XPATH_PRODUCT_TILES_PRICE;
		
		try {
			// Getting the list of product tiles
			List<WebElement> productsList = getElementList(LOCATOR_TYPE_XPATH, pdtTileLocatorValue);

			for (int i = 1; i < productsList.size(); i++) {

				String productPriceLocatorValue = priceLocatorPart + LocatorOpen + i + LocatorClose;

				// Getting the price of each product
				String priceText = getAttributeValue(LOCATOR_TYPE_XPATH, productPriceLocatorValue, attributeName);

				// Validating whether the price text is displayed or not
				if (!priceText.isEmpty()) {
					System.out.println("Product tile at position - " + (i - 1) + " is displaying price.");
				} else {
					ProductTilesDisplayPrice = false;
					System.out.println("Product tile at position - " + (i - 1) + " is not displaying price.");
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
		
		String LocatorOpen = LOCATOR_SQUARE_BRACKET_OPEN;
		String LocatorClose = LOCATOR_SQUARE_BRACKET_CLOSE;
		String attributeName = ATTRIBUTE_INNER_TEXT;
		
		String pdtTileLocatorValue = searchResultPageLocator.XPATH_PRODUCT_TILES;
		String priceLocatorPart = searchResultPageLocator.XPATH_PRODUCT_TILES_PRICE;
		String nameLocatorPart = searchResultPageLocator.XPATH_PRODUCT_TILES_NAME;

		try {
			// Getting the list of product tiles
			List<WebElement> productsList = getElementList(LOCATOR_TYPE_XPATH, pdtTileLocatorValue);

			// Create a list to store product information [name, price] as Object array
			List<Object[]> productList = new ArrayList<>();

			// Extracting the product names and prices and adding them to productList
			for (int i = 1; i < productsList.size(); i++) {

				String productNameLocatorValue = nameLocatorPart + LocatorOpen + i + LocatorClose;
				String productPriceLocatorValue = priceLocatorPart + LocatorOpen + i + LocatorClose;

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
