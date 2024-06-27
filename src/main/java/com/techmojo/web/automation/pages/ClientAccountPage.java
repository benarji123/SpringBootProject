package com.techmojo.web.automation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.techmojo.web.automation.base.PageBase;

public class ClientAccountPage extends PageBase {

	@FindBy(xpath = "//span[text()='Account']/following::a[1]")
	private WebElement accountLink;

//	@FindBy(xpath = "//a[contains(text(),'ACCOUNTS')]/i")
//	private WebElement ;
	
	@FindBy(id = "accountsDropdownMenuButton")
    private WebElement accountDropDown;
	
	@FindBy(xpath = "//a[text()='Statement']")
	private WebElement statementLinkText;
	
	
	@FindBy(xpath ="//span[@class='person_name']/following::div[@class='clients_chevron']")
	private WebElement userDropDown;

	@FindBy(xpath = "//h4[text()='Period']/following ::select[@name='account-filter'][1]")
	private WebElement accountSelectDropDown;

	@FindBy(xpath = "//h4[text()='Period']/following ::option[contains(text(),' LT203740010000000089 (7189.80 CNY) ')]")
	private WebElement accountSelectDropDownOption;

	public ClientAccountPage(WebDriver driver, ExtentTest logger) {
		super(driver, logger);
		PageFactory.initElements(driver, this);
	}
	

	public void clickUserDropDown() {
		verifyElementisVisible(userDropDown, 2);
		this.userDropDown.click();
		logger.log(Status.PASS, "Clicked the User DropDown link");
		}


	public void clickOnAccountLink() {
		verifyElementisVisible(accountLink, 2);
		this.accountLink.click();
		logger.log(Status.PASS, "Clicked the Logout link");
	}

	public void clickonAccountDropDown() throws InterruptedException {
		verifyElementisVisible(accountDropDown, 10);
		mouseClickonElement(accountDropDown);
		Thread.sleep(20000);
		verifyElementisVisible(statementLinkText, 10);
		mouseClickonElement(statementLinkText);
		logger.log(Status.PASS, "Selected drop down");
	}

	public void clickStatementDropDown() {
		verifyElementisVisible(accountSelectDropDown, 4);
		mouseClickonElement(accountSelectDropDown);

		verifyElementisVisible(accountSelectDropDownOption, 10);
		mouseClickonElement(accountSelectDropDownOption);
		logger.log(Status.PASS, "Selected drop down");
	}

}