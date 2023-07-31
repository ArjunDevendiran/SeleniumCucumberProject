package pageLocatorFactory;

public class SearchResultPageLocatorFactory {

	public final String XPATH_SEARCH_RESULT_INFO_BAR = "//*[contains(@data-component-type,'result-info-bar')]";
	public final String XPATH_FILTER_SECTION_LOCATOR_PART = "//*[@id='filters']//*[text()='${text}']";
	public final String XPATH_FILTER_OPTION_LOCATOR_PART = "//ancestor::div[contains(@id,'_browse-bin-title')]//following-sibling::*[position()=1]//*[@aria-label='${text}']//a";
	public final String XPATH_PRODUCT_TILES = "//*[contains(@cel_widget_id,'MAIN-SEARCH_RESULTS')]";
	public final String XPATH_PRODUCT_TILES_PRICE = "(//*[contains(@cel_widget_id,'MAIN-SEARCH_RESULTS')]//*[@data-a-color='base']//*[@class='a-offscreen'])";
	public final String XPATH_PRODUCT_TILES_NAME = "(//*[contains(@cel_widget_id,'MAIN-SEARCH_RESULTS')]//h2/a)";
}