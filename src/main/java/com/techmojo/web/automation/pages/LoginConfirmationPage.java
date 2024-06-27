package com.techmojo.web.automation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.techmojo.web.automation.base.PageBase;

public class LoginConfirmationPage extends PageBase {
	@FindBy(xpath = "//a[text()='Use a different login method']")
	private WebElement loginConfirmationLink;

	public LoginConfirmationPage(WebDriver driver, ExtentTest logger) {
		super(driver, logger);
		PageFactory.initElements(driver, this);
	}

	public void clickOnLoginConfirmationLink() {
		
		verifyElementisVisible(loginConfirmationLink, 5);
		loginConfirmationLink.click();
		reportPass("Click on the Use a different login method link" );
	}

}