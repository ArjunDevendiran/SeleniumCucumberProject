package pageObjectFactory;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.WebElement;

import baseFactory.Log4jWrapper;
import baseFactory.UtilFactory;
import pageLocatorFactory.SearchResultPageLocatorFactory;

public class SearchResultPageObjectFactory extends UtilFactory {

	public void validateTextInSearchResultInfoBar(String expectedText) {

		String searchResultInfoBarlocatorValue = SearchResultPageLocatorFactory.CSS_SEARCH_RESULT_INFO_BAR;

		try {
			String actualText = getText(LOCATOR_TYPE_CSS_SELECTOR, searchResultInfoBarlocatorValue);

			validateTextContains(actualText, expectedText);

		} catch (Exception e) {
			Log4jWrapper.error("Could not get Search element");
			throw e;
		}
	}

	public void validateExpectedTextInPdtNamesInSrp(String expectedText) {

		String searchResultInfoBarlocatorValue = SearchResultPageLocatorFactory.CSS_SEARCH_RESULT_INFO_BAR;
		String pdtNameslocatorPart = SearchResultPageLocatorFactory.XPATH_PRODUCT_TILES_NAME;
		String LocatorOpen = LOCATOR_SQUARE_BRACKET_OPEN;
		String LocatorClose = LOCATOR_SQUARE_BRACKET_CLOSE;
		String attributeName = ATTRIBUTE_INNER_TEXT;

		try {
			String actualText = getText(LOCATOR_TYPE_CSS_SELECTOR, searchResultInfoBarlocatorValue);
			validateTextContains(actualText, expectedText);

			// Getting the list of product tiles
			List<WebElement> productsList = getElementList(LOCATOR_TYPE_XPATH, pdtNameslocatorPart);

			for (int i = 0; i < productsList.size(); i++) {

				String productNameLocatorValue = pdtNameslocatorPart + LocatorOpen + (i + 1) + LocatorClose;

				// Getting the name of each product
				String name = getAttributeValue(LOCATOR_TYPE_XPATH, productNameLocatorValue, attributeName);

				// Validating Search result product names contains searched terms
				if (name.contains(expectedText)) {
					Log4jWrapper.info(
							"Searched Product tile at position - " + (i + 1) + " is displaying expected text.");
				} else {
					Log4jWrapper.warn(
							"Searched Product tile at position - " + (i + 1) + " is not displaying expected text.");
				}
			}
		} catch (Exception e) {
			Log4jWrapper.error("Could not get Search element");
			throw e;
		}
	}

	public void scrollToFilterSection(String filterType) {

		String filterSectionlocatorPart = SearchResultPageLocatorFactory.XPATH_FILTER_SECTION_LOCATOR_PART;

		try {
			String filterSectionlocatorValue = getElementReplacementString(filterSectionlocatorPart, filterType);

			scrollElementIntoView(LOCATOR_TYPE_XPATH, filterSectionlocatorValue);

		} catch (Exception e) {
			Log4jWrapper.error("Could not get filter element");
			throw e;
		}
	}

	public void selectFilterOption(String filterType, String filterOption) {

		String filterSectionlocatorPart = SearchResultPageLocatorFactory.XPATH_FILTER_SECTION_LOCATOR_PART;
		String filterOptionlocatorPart = SearchResultPageLocatorFactory.XPATH_FILTER_OPTION_LOCATOR_PART;
		String combinedLocatorValue = filterSectionlocatorPart + filterOptionlocatorPart;

		try {
			String filterOptionlocatorValue = getElementReplacementString(combinedLocatorValue, filterType,
					filterOption);

			click(LOCATOR_TYPE_XPATH, filterOptionlocatorValue);

		} catch (Exception e) {
			Log4jWrapper.error("Could not get filter option element");
			throw e;
		}
	}

	public void validateAllSrpProductPricesAreDisplayed(boolean ProductTilesDisplayPrice) {

		String LocatorOpen = LOCATOR_SQUARE_BRACKET_OPEN;
		String LocatorClose = LOCATOR_SQUARE_BRACKET_CLOSE;
		String attributeName = ATTRIBUTE_INNER_TEXT;

		String pdtTileLocatorValue = SearchResultPageLocatorFactory.XPATH_PRODUCT_TILES;
		String priceLocatorPart = SearchResultPageLocatorFactory.XPATH_PRODUCT_TILES_PRICE;

		try {
			// Getting the list of product tiles
			List<WebElement> productsList = getElementList(LOCATOR_TYPE_XPATH, pdtTileLocatorValue);

			for (int i = 0; i < productsList.size(); i++) {

				String productPriceLocatorValue = priceLocatorPart + LocatorOpen + (i + 1) + LocatorClose;

				// Getting the price of each product
				String priceText = getAttributeValue(LOCATOR_TYPE_XPATH, productPriceLocatorValue, attributeName);

				// Validating whether the price text is displayed or not
				if (!priceText.isEmpty()) {
					Log4jWrapper.info("Product tile at position - " + (i + 1) + " is displaying price.");
				} else {
					ProductTilesDisplayPrice = false;
					Log4jWrapper.warn("Product tile at position - " + (i + 1) + " is not displaying price.");
				}
			}

			// Printing the overall status of price display
			if (ProductTilesDisplayPrice) {
				Log4jWrapper.info("All the Product tiles are displaying price.");
				Assert.assertTrue("Validated all Products have price.", ProductTilesDisplayPrice);
			} else {
				Log4jWrapper.error("Few Product tiles are not displaying price.");
				Assert.assertTrue("All products should have price.", ProductTilesDisplayPrice);
			}
		} catch (Exception e) {
			Log4jWrapper.error("Could not get product elements.");
			throw e;
		}
	}

	public void printAllSrpProductNamesAndPriceOrderedFromPriceLowToHigh() {

		String LocatorOpen = LOCATOR_SQUARE_BRACKET_OPEN;
		String LocatorClose = LOCATOR_SQUARE_BRACKET_CLOSE;
		String attributeName = ATTRIBUTE_INNER_TEXT;

		String pdtTileLocatorValue = SearchResultPageLocatorFactory.XPATH_PRODUCT_TILES;
		String priceLocatorPart = SearchResultPageLocatorFactory.XPATH_PRODUCT_TILES_PRICE;
		String nameLocatorPart = SearchResultPageLocatorFactory.XPATH_PRODUCT_TILES_NAME;

		try {
			// Getting the list of product tiles
			List<WebElement> productsList = getElementList(LOCATOR_TYPE_XPATH, pdtTileLocatorValue);

			// Create a list to store product information [name, price] as Object array
			List<Object[]> productList = new ArrayList<>();

			// Extracting the product names and prices and adding them to productList
			for (int i = 1; i < productsList.size(); i++) {

				String productNameLocatorValue = nameLocatorPart + LocatorOpen + (i + 1) + LocatorClose;
				String productPriceLocatorValue = priceLocatorPart + LocatorOpen + (i + 1) + LocatorClose;

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
			Log4jWrapper
					.info("\n\n\n" + "Below is the list of Product's name and Price in Ascending order:" + "\n\n\n");
			
			for (Object[] productData : productList) {
				Log4jWrapper.info("Price: $" + productData[1] + ", Product: " + productData[0]);
			}
		} catch (Exception e) {
			Log4jWrapper.error("Could not get product elements.");
			throw e;
		}
	}

	// A static method to extract the price from the product array (method
	// reference)
	public static double getPrice(Object[] product) {
		return (double) product[1];
	}

}
