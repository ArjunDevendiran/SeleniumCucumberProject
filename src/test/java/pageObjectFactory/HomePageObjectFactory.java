package pageObjectFactory;

import baseFactory.Log4jWrapper;
import baseFactory.UtilFactory;
import pageLocatorFactory.HomePageLocatorFactory;

public class HomePageObjectFactory extends UtilFactory {

	public void loadHomePage(String url) {

		loadUrl(url);
	}

	public void enterTextInSearchField(String searchText) {

		String searchFieldlocatorValue = HomePageLocatorFactory.CSS_SEARCH_FIELD;

		try {
			enterString(LOCATOR_TYPE_CSS_SELECTOR, searchFieldlocatorValue, searchText);

		} catch (Exception e) {
			Log4jWrapper.error("Could not get element");
			throw e;
		}
	}

	public void clickSearchSubmitButton() {

		String searchSubmitBtnlocatorValue = HomePageLocatorFactory.XPATH_SEARCH_SUBMIT_BUTTON;

		try {
			click(LOCATOR_TYPE_XPATH, searchSubmitBtnlocatorValue);

		} catch (Exception e) {
			Log4jWrapper.error("Could not get element");
			throw e;
		}
	}

}
