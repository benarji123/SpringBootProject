package com.techmojo.web.automation.base;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.http.client.utils.DateUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

/**
 * The Class PageBase.
 */
public class PageBase {

	/** The driver. */
	protected WebDriver driver;

	/** The logger. */
	protected ExtentTest logger;

	/** The wait. */
	protected WebDriverWait wait;

	/** The js. */
	protected JavascriptExecutor js;

	/**
	 * Instantiates a new page base.
	 *
	 * @param driver the driver
	 * @param logger the logger
	 */
	public PageBase(WebDriver driver, ExtentTest logger) {
		this.driver = driver;
		this.logger = logger;
		js = (JavascriptExecutor) driver;
	}

	/**
	 * **************** Open URL **********************.
	 *
	 * @param websiteURLKey the website URL key
	 * @return the connect pay login page
	 */
	public void openApplication(String websiteURLKey) {
		logger.log(Status.INFO, "Opening the WebSite");
		driver.get(websiteURLKey);
		reportPass(websiteURLKey + " Identified Successfully");
		waitForPageLoad();
	}

	/**
	 * **************** Get Page Title **********************.
	 *
	 * @param expectedTitle the expected title
	 * @return the title
	 */
	public void assertTitle(String expectedTitle) {
		try {
			Assert.assertEquals(driver.getTitle(), expectedTitle);
			reportPass("Actual Title : " + driver.getTitle() + " - equals to Expected Title : " + expectedTitle);
		} catch (Exception e) {
			reportFail(e.getMessage());
		}
	}

	/**
	 * **************** Select value From DropDown **********************.
	 *
	 * @param webElement the web element
	 * @param value      the value
	 */
	public void selectDropDownValue(WebElement webElement, String value) {
		try {
			Select select = new Select(webElement);
			select.selectByValue(value);
			logger.log(Status.PASS, "Selectd the Value : " + value);
		} catch (Exception e) {
			reportFail(e.getMessage());
		}
	}

	/**
	 * Select drop down value by index.
	 *
	 * @param webElement the web element
	 * @param index      the index
	 */
	public void selectDropDownValueByIndex(WebElement webElement, int index) {
		try {
			Select select = new Select(webElement);
			select.selectByIndex(index);
			logger.log(Status.PASS, "Selectd the index : " + index);
		} catch (Exception e) {
			reportFail(e.getMessage());
		}
	}

	/**
	 * Select drop down by partial text.
	 *
	 * @param webElement the web element
	 * @param value      the value
	 */
	public void selectDropDownByPartialText(WebElement webElement, String value) {
		try {
			Select select = new Select(webElement);
			List<WebElement> options = select.getOptions();
			for (WebElement option : options) {
				String currentOption = option.getText();
				if (currentOption.contains(value)) {
					option.click();
				}
			}
		} catch (Exception e) {
			reportFail(e.getMessage());
		}
	}

	/**
	 * **************** Verify Element is Present **********************.
	 *
	 * @param webElement the web element
	 */
	public void veriyElementIsDisplayed(WebElement webElement) {
		try {
			if (webElement.isDisplayed()) {
				reportPass("Element is Displayed");
			} else {
				reportFail("Element is not appeared");
			}
		} catch (Exception e) {
			reportFail(e.getMessage());
		}
	}

	/**
	 * **************** Get All Elements of DropDown **********************.
	 *
	 * @param webElement the web element
	 * @return the all elements of drop down
	 */
	public List<WebElement> getAllElementsOfDropDown(WebElement webElement) {
		List<WebElement> allElements = null;
		try {
			Select select = new Select(webElement);
			allElements = select.getOptions();
		} catch (Exception e) {
			reportFail(e.getMessage());
		}
		return allElements;
	}

	/**
	 * **************** Reporting Functions **********************.
	 *
	 * @param reportString the report string
	 */
	public void reportFail(String reportString) {
		logger.log(Status.FAIL, reportString);
		//takeScreenShotOnFailure();
		Assert.fail(reportString);
	}

	/**
	 * Report pass.
	 *
	 * @param reportString the report string
	 */
	public void reportPass(String reportString) {
		logger.log(Status.PASS, reportString);
	}

	/**
	 * **************** Capture Screen Shot **********************.
	 *//*
		 * public void takeScreenShotOnFailure() {
		 * 
		 * TakesScreenshot takeScreenShot = (TakesScreenshot) driver; waitLoad(2); File
		 * sourceFile = takeScreenShot.getScreenshotAs(OutputType.FILE); //File destFile
		 * = new File(System.getProperty("user.dir") + "/ScreenShots/" +
		 * DateUtils.getTimeStamp() + ".png"); try { FileUtils.copyFile(sourceFile,
		 * destFile); logger.addScreenCaptureFromPath( // System.getProperty("user.dir")
		 * + "/ScreenShots/" + DateUtils.getTimeStamp() + ".png");
		 * 
		 * } catch (IOException e) { // reportFail(e.getMessage()); // } }
		 */

	/**
	 * 
	 * Wait for element to be clickable
	 * 
	 */

	public void verifyElementisClickable(WebElement webElement, long time) {
		try {
			wait = new WebDriverWait(driver, time);
			wait.until(ExpectedConditions.elementToBeClickable(webElement));

			reportPass("Element is Clickable");
		} catch (Exception e) {
			reportFail(e.getMessage());
		}
	}
	/*
	 * wait for element to be visible
	 * 
	 */

	public void verifyElementisVisible(WebElement webElement, long time) {
		try {
			wait = new WebDriverWait(driver, time);
			wait.until(ExpectedConditions.visibilityOf(webElement));

			reportPass("Element is visible");
		} catch (Exception e) {
			reportFail(e.getMessage());
		}
	}

	/*
	 * Mouse click
	 * 
	 */
	public void mouseClickonElement(WebElement webElement) {
		Actions act = new Actions(driver);
		act.moveToElement(webElement).click().perform();
	}

	/**
	 * *************** Wait Functions in Framework ****************.
	 */
	public void waitForPageLoad() {
		JavascriptExecutor js = (JavascriptExecutor) driver;

		while (true) {
			String pageState = (String) js.executeScript("return document.readyState;");
			if (pageState.equals("complete")) {
				break;
			} else {
				waitLoad(1);
			}
		}
		waitLoad(2);
		while (true) {
			Boolean jsState = (Boolean) js.executeScript("return window.jQuery != undefined && jQuery.active == 0;");
			if (Boolean.TRUE.equals(jsState)) {
				break;
			} else {
				waitLoad(1);
			}
		}
	}

	/**
	 * Wait load.
	 *
	 * @param i the i
	 */
	public void waitLoad(int i) {
		try {
			Thread.sleep(i * 1000);
		} catch (InterruptedException e) {
			logger.error(e.getMessage());
		}
	}

	public void scrollDown() {
		js.executeScript("window.scrollBy(0,1000)");
	}
}