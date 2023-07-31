package baseFactory;

import java.time.Duration;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class UtilFactory extends UtilLocatorFactory {

	private static WebDriver driver = null;
	private static WebElement webElement = null;

	public void browserSetup() {

		try {
			// Instantiating the Selenium WebDriver instance with the ChromeDriver
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();

			// Adding waits
			driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

			// Maximizing window
			maximizeWindow();

			Log4jWrapper.info("Browser Setup completed.");
		} catch (Exception e) {
			Log4jWrapper.error("Could not setup Browser");
			throw e;
		}
	}

	public void teardown() {

		try {
//			driver.close();
//			driver.quit();

			Log4jWrapper.info("Driver instance is terminated.");
		} catch (Exception e) {
			Log4jWrapper.error("Could not terminate Driver instance.");
			throw e;

		}
	}

	public void maximizeWindow() {

		try {
			driver.manage().window().maximize();

			Log4jWrapper.info("Maximized Window.");
		} catch (Exception e) {
			Log4jWrapper.error("Could not maximize Window.");
			throw e;
		}
	}

	public void loadUrl(String url) {

		try {
			driver.navigate().to(url);

			Log4jWrapper.info("Loaded url - " + url);
		} catch (Exception e) {
			Log4jWrapper.error("Could not load url - " + url);
			throw e;
		}
	}

	public WebElement getElement(String locatorType, String locatorValue) {

		webElement = null;
		try {
			if (locatorType.equalsIgnoreCase(LOCATOR_TYPE_XPATH)) {
				webElement = driver.findElement(By.xpath(locatorValue));
			} else if (locatorType.equalsIgnoreCase(LOCATOR_TYPE_CSS_SELECTOR)) {
				webElement = driver.findElement(By.cssSelector(locatorValue));
			} else {
				System.out.println("Provide valid locator Type");
				Log4jWrapper.warn("Provide valid locator Type");
			}
			Log4jWrapper.info("Found the WebElement.");
			return webElement;
		} catch (Exception e) {
			Log4jWrapper.error("Could not find the WebElement.");
			throw e;
		}
	}

	public List<WebElement> getElementList(String locatorType, String locatorValue) {

		List<WebElement> elementLists = null;

		if (locatorType.equalsIgnoreCase(LOCATOR_TYPE_XPATH)) {
			elementLists = driver.findElements(By.xpath(locatorValue));
		} else if (locatorType.equalsIgnoreCase("LOCATOR_TYPE_CSS_SELECTOR")) {
			elementLists = driver.findElements(By.cssSelector(locatorValue));
		} else {
			Log4jWrapper.warn("Provide valid locator Type");
		}

		if (elementLists.size() > 0) {
			System.out.println("WebElement found");
		} else {
			System.out.println("WebElement not Found");
		}
		Log4jWrapper.info("Found the WebElement list of size - " + elementLists.size());
		return elementLists;

	}

	protected void enterString(String locatorType, String locatorValue, String fieldValue) {
		try {
			webElement = getElement(locatorType, locatorValue);
			webElement.sendKeys(fieldValue);

			Log4jWrapper.info("Entered String - " + fieldValue);
		} catch (Exception e) {
			Log4jWrapper.error("Could not enter String - " + fieldValue);
			throw e;
		}
	}

	protected void click(String locatorType, String locatorValue) {
		try {
			webElement = getElement(locatorType, locatorValue);
			webElement.click();

			Log4jWrapper.info("Clicked the element");
		} catch (Exception e) {
			Log4jWrapper.error("Could not click the element");
			throw e;
		}
	}

	protected String getText(String locatorType, String locatorValue) {
		try {
			webElement = getElement(locatorType, locatorValue);

			String text = webElement.getText();

			Log4jWrapper.info("Got the text.");
			return text;
		} catch (Exception e) {
			Log4jWrapper.error("Could not get the text");
			throw e;
		}
	}

	protected void validateTextContains(String actualText, String expectedText) {

		if (actualText.toLowerCase().contains(expectedText.toLowerCase())) {
			Assert.assertTrue("Actual text contains Expected Text", true);
		} else {
			Assert.assertTrue("Actual text does not contain Expected Text", false);
		}
	}

	protected void scrollElementIntoView(String locatorType, String locatorValue) {
		try {
			webElement = getElement(locatorType, locatorValue);
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].scrollIntoView();", webElement);

			Log4jWrapper.info("Scrolled to the element.");
		} catch (Exception e) {
			Log4jWrapper.error("Could not scroll to the element.");
			throw e;
		}
	}

	protected String getAttributeValue(String locatorType, String locatorValue, String attributeName) {
		try {
			webElement = getElement(locatorType, locatorValue);
			String attribute = webElement.getAttribute(attributeName);

			Log4jWrapper.info("Got the attribute - " + attribute);
			return attribute;
		} catch (Exception e) {
			Log4jWrapper.error("Could not get the attribute");
			throw e;
		}
	}

	public String getElementReplacementString(String locatorValue, String replacement) {
		try {
			locatorValue = locatorValue.replaceAll("\\$\\{.+?\\}", replacement);

			Log4jWrapper.info("Got the updated locator.");
			return locatorValue;
		} catch (Exception e) {
			Log4jWrapper.error("Could not get the updated locator.");
			throw e;
		}
	}

	public String getElementReplacementString(String locatorValue, String replacement1, String replacement2) {
		try {
			locatorValue = locatorValue.replaceFirst("\\$\\{.+?\\}", replacement1);
			locatorValue = locatorValue.replaceFirst("\\$\\{.+?\\}", replacement2);

			Log4jWrapper.info("Got the updated locator.");
			return locatorValue;
		} catch (Exception e) {
			Log4jWrapper.error("Could not get the updated locator.");
			throw e;
		}
	}

}
