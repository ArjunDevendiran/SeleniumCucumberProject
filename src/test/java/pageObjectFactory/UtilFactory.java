package pageObjectFactory;

import java.time.Duration;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import pageLocatorFactory.UtilLocatorFactory;

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

		} catch (Exception e) {
			throw e;
		}
	}

	public void teardown() {

		try {
			// Closing broswer instance
			driver.close();
			driver.quit();
		} catch (Exception e) {
			throw e;
		}
	}

	public void maximizeWindow() {

		try {
			driver.manage().window().maximize();
		} catch (Exception e) {
			throw e;
		}
	}

	public void loadUrl(String url) {

		try {
			driver.navigate().to(url);
		} catch (Exception e) {
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
			}
			return webElement;

		} catch (Exception e) {
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
			System.out.println("Provide valid locator Type");
		}

		if (elementLists.size() > 0) {
			System.out.println("WebElement found");
		} else {
			System.out.println("WebElement not Found");
		}
		return elementLists;

	}

	protected void enterString(String locatorType, String locatorValue, String fieldValue) throws Exception {
		try {
			webElement = getElement(locatorType, locatorValue);
			webElement.sendKeys(fieldValue);

		} catch (Exception e) {
			throw e;
		}
	}

	protected void click(String locatorType, String locatorValue) throws Exception {
		try {
			webElement = getElement(locatorType, locatorValue);
			webElement.click();

		} catch (Exception e) {
			throw e;
		}
	}

	protected String getText(String locatorType, String locatorValue) throws Exception {
		try {
			webElement = getElement(locatorType, locatorValue);
			return webElement.getText();

		} catch (Exception e) {
			throw e;
		}
	}

	protected void validateTextContains(String actualText, String expectedText) {
		try {
			if (actualText.toLowerCase().contains(expectedText.toLowerCase())) {

				System.out.println("Text matches");
			} else {
				System.out.println("Text does not matche");
				Assert.assertTrue(false);
			}

		} catch (Exception e) {
			throw e;
		}
	}

	protected void scrollElementIntoView(String locatorType, String locatorValue) {
		try {
			webElement = getElement(locatorType, locatorValue);
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].scrollIntoView();", webElement);
		} catch (Exception e) {
			throw e;
		}
	}

	protected String getAttributeValue(String locatorType, String locatorValue, String attributeName) {
		webElement = getElement(locatorType, locatorValue);
		return webElement.getAttribute(attributeName);
	}

	public String getElementReplacementString(String locatorValue, String replacement) {
		locatorValue = locatorValue.replaceAll("\\$\\{.+?\\}", replacement);
		return locatorValue;
	}

	public String getElementReplacementString(String locatorValue, String replacement1, String replacement2) {
		locatorValue = locatorValue.replaceFirst("\\$\\{.+?\\}", replacement1);
		locatorValue = locatorValue.replaceFirst("\\$\\{.+?\\}", replacement2);
		return locatorValue;
	}

}
