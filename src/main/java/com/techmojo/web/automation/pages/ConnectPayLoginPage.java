package com.techmojo.web.automation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.techmojo.web.automation.base.PageBase;

public class ConnectPayLoginPage extends PageBase {

	@FindBy(id = "userName")
	private WebElement userName;

	@FindBy(id = "password")
	private WebElement password;

	@FindBy(xpath = "//button[@id='btn-submit']")
	private WebElement loginButton;
	
	
	@FindBy(xpath = "//span[text()='Please check your User ID or Password and try again.']")
	private WebElement failureMessage;

	@FindBy(id = "Your account is locked")
	private WebElement accountLocked;

	public ConnectPayLoginPage(WebDriver driver, ExtentTest logger) {
		super(driver, logger);
		PageFactory.initElements(driver, this);
	}

	/**
	 * Do login.
	 *
	 * @param userName the user name
	 * @param password the password
	 */
	public void doLogin(String userName, String password) {
		this.userName.sendKeys(userName);
		logger.log(Status.PASS, "Entered the UserName : " + userName);
		this.password.sendKeys(password);
		logger.log(Status.PASS, "Entered the Password : " + password);
		this.loginButton.click();
		logger.log(Status.PASS, "Clicked the Submit Login Button");
		waitForPageLoad();
	}

	/**
	 * Enter user.
	 *
	 * @param userName the user name
	 */
	public void enterUser(String userName) {
		this.userName.sendKeys(userName);
		logger.log(Status.PASS, "Entered the UserName : " + userName);
	}

	public void clearUsernamePassword() {
		userName.clear();
		password.clear();
	}

	/**
	 * Enter password.
	 *
	 * @param password the password
	 */
	public void enterPassword(String password) {
		this.password.sendKeys(password);
		logger.log(Status.PASS, "Entered the Password : " + password);
	}

	public void submitLogin() {
		verifyElementisClickable(loginButton, 6);
		this.loginButton.click();
		logger.log(Status.PASS, "Clicked the Submit Login Button");
	}

	public void verifyFailureMessage() {
		verifyElementisVisible(failureMessage, 5);
		logger.log(Status.PASS, "User not able to login with Wrong username and password" + failureMessage.getText());
	}

	public void verifyAccountLock() {
		veriyElementIsDisplayed(accountLocked);
	}

	public void verifyLoginPage() {
		veriyElementIsDisplayed(userName);
	}

	public void verifyPasswordField() {
		veriyElementIsDisplayed(password);
	}

	public boolean isLoginPage() {
		try {
			return userName.isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}

}