package com.techmojo.web.automation.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.techmojo.web.automation.base.PageBase;

import ch.qos.logback.core.joran.action.Action;

public class SelectClientPage extends PageBase {
	@FindBy(linkText = "BBQ")
	private WebElement bbqLinkText;

	//@FindBy(xpath = "//div[contains(text(),'Test-CustomerValidStages')]")
	
	@FindBy(xpath = "/html/body/app-root/cp-clients/div/div[2]/div/div[2]/div[3]/div[1]/div[11]")
	
	private WebElement testCustomerValidStageslinkText;

	@FindBy(xpath = "//button[@id='clientSubmitBtn']")
	private WebElement submit;
	
	public SelectClientPage(WebDriver driver, ExtentTest logger) {
		super(driver, logger);
		PageFactory.initElements(driver, this);
	}

	public void clickontestCustomerValidStageslinkText() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,1000)");
		js.executeScript("arguments[0].scrollIntoView();",testCustomerValidStageslinkText );
		verifyElementisVisible(testCustomerValidStageslinkText, 10);
		
		 Actions builder = new Actions(driver);
         builder.moveToElement(testCustomerValidStageslinkText).build().perform();
		
		//this.testCustomerValidStageslinkText.click();
		logger.log(Status.PASS, "testCustomerValidStageslinkText");
		verifyElementisVisible(submit, 10);
		
		verifyElementisClickable(submit, 2);
		this.submit.click();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {

			System.out.println("Page load failed" + e.getMessage());
		}

	}

	public void clickBBQLink() {
		this.bbqLinkText.click();
		logger.log(Status.PASS, "Clicked the BBQ link text");
		waitForPageLoad();
	}

	public void verifySelectClientPage() {
		veriyElementIsDisplayed(bbqLinkText);
	}

}