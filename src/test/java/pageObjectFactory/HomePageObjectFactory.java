package pageObjectFactory;

public class HomePageObjectFactory extends UtilFactory {
	
	public void loadHomePage(String url) {

		try {
			// Loading Test url
			loadUrl(url);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void enterTextInSearchField(String searchText) {

		String locatorValue = "//*[@placeholder='Search Amazon']";
		
		try {
			enterString(LOCATOR_TYPE_XPATH, locatorValue, searchText);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void clickSearchSubmitButton() {

		String locatorValue = "//*[@id='nav-search-submit-button']";

		try {
			// Clicking Search submit link
			click(LOCATOR_TYPE_XPATH, locatorValue);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
