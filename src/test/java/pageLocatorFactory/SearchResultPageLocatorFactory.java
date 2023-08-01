package pageLocatorFactory;

public class SearchResultPageLocatorFactory {

	public static final String CSS_SEARCH_RESULT_INFO_BAR = "[data-component-type*='result-info-bar']";
	public static final String XPATH_FILTER_SECTION_LOCATOR_PART = "//*[@id='filters']//*[text()='${text}']";
	public static final String XPATH_FILTER_OPTION_LOCATOR_PART = "//ancestor::div[contains(@id,'_browse-bin-title')]//following-sibling::*[position()=1]//*[@aria-label='${text}']//a";
	public static final String XPATH_PRODUCT_TILES = "//*[contains(@cel_widget_id,'MAIN-SEARCH_RESULTS')]";
	public static final String XPATH_PRODUCT_TILES_PRICE = "(//*[contains(@cel_widget_id,'MAIN-SEARCH_RESULTS')]//*[@data-a-color='base']//*[@class='a-offscreen'])";
	public static final String XPATH_PRODUCT_TILES_NAME = "(//*[contains(@cel_widget_id,'MAIN-SEARCH_RESULTS')]//h2/a)";
}