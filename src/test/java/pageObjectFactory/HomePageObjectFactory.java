package pageObjectFactory;

import pageLocatorFactory.HomePageLocatorFactory;

public class HomePageObjectFactory extends UtilFactory {
	
	HomePageLocatorFactory homPageLocator = new HomePageLocatorFactory();
	
	public void loadHomePage(String url) {

		try {
			loadUrl(url);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void enterTextInSearchField(String searchText) {

		String searchFieldlocatorValue = homPageLocator.XPATH_SEARCH_FIELD;
		
		try {
			enterString(LOCATOR_TYPE_XPATH, searchFieldlocatorValue, searchText);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void clickSearchSubmitButton() {

		String searchSubmitBtnlocatorValue = homPageLocator.XPATH_SEARCH_SUBMIT_BUTTON;

		try {
			click(LOCATOR_TYPE_XPATH, searchSubmitBtnlocatorValue);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
