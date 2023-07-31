package pageObjectFactory;

import baseFactory.Log4jWrapper;
import baseFactory.UtilFactory;
import pageLocatorFactory.HomePageLocatorFactory;

public class HomePageObjectFactory extends UtilFactory {
	
	HomePageLocatorFactory homPageLocator = new HomePageLocatorFactory();
	
	public void loadHomePage(String url) {
		
		loadUrl(url);
	}
	
	public void enterTextInSearchField(String searchText) {

		String searchFieldlocatorValue = homPageLocator.XPATH_SEARCH_FIELD;
		
		try {
			enterString(LOCATOR_TYPE_XPATH, searchFieldlocatorValue, searchText);

		} catch (Exception e) {
			Log4jWrapper.error("Could not get element");
			throw e;
		}
	}
	
	public void clickSearchSubmitButton() {

		String searchSubmitBtnlocatorValue = homPageLocator.XPATH_SEARCH_SUBMIT_BUTTON;

		try {
			click(LOCATOR_TYPE_XPATH, searchSubmitBtnlocatorValue);

		} catch (Exception e) {
			Log4jWrapper.error("Could not get element");
			throw e;
		}
	}

}
