package com.techmojo.web.automation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.SendKeysAction;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.techmojo.web.automation.base.PageBase;

public class AccountStatementPage extends PageBase {
	@FindBy(xpath = "//select[@name='account-filter']")
	private WebElement accountFilter;

	@FindBy(xpath = "//h2[text()='Account statement']")
	private WebElement accountStatementText;

	@FindBy(xpath = "//button[text()=' Search ']")
	private WebElement searchButton;
	
	@FindBy(xpath = "//a[contains(text(),'PAYMENTS')]/i")
	private WebElement paymentDropDown;

	@FindBy(xpath = "//a[contains(text(),'Payments')][1]")
	private WebElement paymentLink;
	
	@FindBy(xpath = "//span[contains(text(),'Statements')]")
	private WebElement statementButton;
	
	
	@FindBy(xpath = "//span[contains(text(),'This week')]")
			private WebElement thisWeekButton;
	
	@FindBy(xpath = "//a[@title='Venu Stage']")
	private WebElement thisTitle;
	
	@FindBy(xpath = "//*[@id=\"updateStatementBtn\"]")
	private WebElement generateStatement;
	
	@FindBy(xpath = "/html/body/app-root/cp-main/nav[4]/div/ul/li[1]/ul/li[3]/a")
	private WebElement bulkPayment;
	
	@FindBy(xpath = "//body/app-root[1]/cp-main[1]/nav[4]/div[1]/ul[1]/li[1]/ul[1]/li[4]/a[1]")
	private WebElement Messages;
	
	
	@FindBy(xpath = "//span[contains(text(),'Inbox')]")
	private WebElement gotoInbox;
	
	@FindBy(xpath = "//span[contains(text(),'Sent')]")
	private WebElement Sent;
	
	@FindBy(xpath = "//body/app-root[1]/cp-main[1]")
	private WebElement writeSubject;
	
	@FindBy(xpath = "//a[contains(text(),'My profile')]")
	private WebElement checkMyProfile;
	
	 
	@FindBy(xpath = "//span[contains(text(),'Manage Devices')]")
	private WebElement checkManageDevice;
	
	
	@FindBy(xpath = "//span[contains(text(),'Password And Security')]")
	private WebElement checkPasswordAndSecurity;
	
	
		
	public AccountStatementPage(WebDriver driver, ExtentTest logger) {
		super(driver, logger);
		PageFactory.initElements(driver, this);
	}

	public void selectStatement() throws InterruptedException {
		Thread.sleep(2000);
		verifyElementisVisible(accountStatementText, 2);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			System.out.println("Failed to wait" +e.getMessage());
		}
		selectDropDownValueByIndex(accountFilter, 2);
	}

	public void clickOnSearchButton() throws InterruptedException {
		searchButton.click();
		searchButton.sendKeys("Venu Stage");
		scrollDown();
		Thread.sleep(6000);
	}
	
	public void clickWriteSubject1() throws InterruptedException {
		writeSubject.click();
		writeSubject.sendKeys("Sample message writting");
		Thread.sleep(6000);
	}
	
	public void clickPaymentDropDown()
	{
		verifyElementisVisible(paymentDropDown, 5);
		mouseClickonElement(paymentDropDown);
		verifyElementisVisible(paymentLink, 5);
		mouseClickonElement(paymentLink);
		 
	}
	
	public void clickstatementButton()
	{
		verifyElementisVisible(statementButton, 5);
		mouseClickonElement(statementButton);
		reportPass("Selected Statement");
	}
	
	public void clickthisWeekButton()
	{
		verifyElementisVisible(thisWeekButton, 5);
		mouseClickonElement(thisWeekButton);
		reportPass("Selected ThisWeek");
	}
	

	public void clickthisTitle()
	{
		verifyElementisVisible(thisTitle, 5);
		mouseClickonElement(thisTitle);
		reportPass("Selected Title");
	}
	
	
	public void clickGenerateStatement()
	{
		verifyElementisVisible(generateStatement, 10);
		mouseClickonElement(generateStatement);
		reportPass("Generate Statement");
	}
	
	public void clickBulkPayment()
	{
		verifyElementisVisible(bulkPayment, 10);
		mouseClickonElement(bulkPayment);
		reportPass("Bulk Statement");
	}
		
	public void clickMessages()
	{
		verifyElementisVisible(Messages, 10);
		mouseClickonElement(Messages);
		reportPass("Messages");
	}
	
	public void clickgotoInbox()
	{
		verifyElementisVisible(gotoInbox, 10);
		mouseClickonElement(gotoInbox);
		reportPass("Inbox Messages");
	}
	
	public void clickSent()
	{
		verifyElementisVisible(Sent, 10);
		mouseClickonElement(Sent);
		reportPass("Sent Messages");
	}
	
	public void clickWriteSubject()
	{
		verifyElementisVisible(writeSubject, 10);
		mouseClickonElement(writeSubject);
		reportPass("Write Subject Message");
	}
	
	public void clickcheckMyProfile()
	{
		verifyElementisVisible(checkMyProfile, 10);
		mouseClickonElement(checkMyProfile);
		reportPass("Verify my profile");
	}
	
	public void clickcheckManageDevice()
	{
		verifyElementisVisible(checkManageDevice, 10);
		mouseClickonElement(checkManageDevice);
		reportPass("Verify Manage Device");
	}
	
	public void clickcheckPasswordAndSecurity()
	{
		verifyElementisVisible(checkPasswordAndSecurity, 10);
		mouseClickonElement(checkPasswordAndSecurity);
		reportPass("Verify Manage Device");
	}
	
}