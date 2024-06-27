package com.techmojo.web.automation.test.cases;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.junit.BeforeClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.techmojo.web.automation.constants.BrowserEnums;
import com.techmojo.web.automation.pages.AccountStatementPage;
import com.techmojo.web.automation.pages.ClientAccountPage;
import com.techmojo.web.automation.pages.ConnectPayLoginPage;
import com.techmojo.web.automation.pages.LoginConfirmationPage;
import com.techmojo.web.automation.pages.OtpPage;
import com.techmojo.web.automation.pages.PaymentsPage;
import com.techmojo.web.automation.pages.SelectClientPage;
import com.techmojo.web.automation.test.base.TestBase;
import com.techmojo.web.automation.util.TestDataProvider;

public class OpenBankingTest extends TestBase {
	private ConnectPayLoginPage loginPage;
	private OtpPage otpPage;
	private SelectClientPage selectclientPage;
	private LoginConfirmationPage loginconfirmationPage;
	private ClientAccountPage clientaccountPage;
	private AccountStatementPage accountStatementPage;
	private PaymentsPage paymentsPage;


	
	@BeforeMethod(alwaysRun = true)
	public void init(Method method) {
		setBrowser(BrowserEnums.FIREFOX.getBrowserName());
		setTestName("Open Bank Test:" +method.getName());
		loginPage = new ConnectPayLoginPage(driver, logger);
		otpPage = new OtpPage(driver, logger);
		loginconfirmationPage = new LoginConfirmationPage(driver, logger);
		selectclientPage = new SelectClientPage(driver, logger);
		clientaccountPage = new ClientAccountPage(driver, logger);
		accountStatementPage = new AccountStatementPage(driver, logger);
		paymentsPage = new PaymentsPage(driver, logger);
		//driver.navigate().to(properties.getUrl());

	}

	@DataProvider
	public Object[][] getStandardPaymentData() {
		return TestDataProvider.getTestData("testData.xlsx", "Feature 2", "Standard Payment Test");
	}

	@DataProvider
	public Object[][] getSuccessLoginData() {
		return TestDataProvider.getTestData("testData.xlsx", "Feature 2", "Login Success Test");
	}

	@DataProvider
	public Object[][] getFailureloginData() {
		return TestDataProvider.getTestData("testData.xlsx", "Feature 2", "Login Failure Test");
	}

	@DataProvider
	public Object[][] getSuccessOtp() {
		return TestDataProvider.getTestData("testData.xlsx", "Feature 2", "Otp Success Test");
	}

	@DataProvider
	public Object[][] getFailureOtp() {
		return TestDataProvider.getTestData("testData.xlsx", "Feature 2", "Otp Failure Test");
	}

// Successful login

	@Test(priority = 1, enabled = false, dataProvider = "getSuccessLoginData")
	public void testLoginSucess(HashMap<String, String> data) {
		try {
			
			//Thread.sleep(6000);
			driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
			loginPage.openApplication(properties.getUrl());
			//loginPage.openApplication("https://onlinebanking-stage.connectpay.com/");
			loginPage.enterUser(data.get("username"));
			System.out.println("******"+data.get("password"));
			loginPage.enterPassword(data.get("password"));
			loginPage.submitLogin();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	

// Failed Login 

	@Test(priority = 2, enabled = false, dataProvider = "getFailureloginData")
	public void testLoginUsernameFail(HashMap<String, String> data) throws InterruptedException {
		//setTestName("Login Failure Test");
		driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
		loginPage.openApplication(properties.getUrl());
		loginPage.enterUser(data.get("username"));
		loginPage.enterPassword(data.get("password"));
		loginPage.submitLogin();
		driver.manage().timeouts().implicitlyWait(9, TimeUnit.SECONDS);
	}

	
// Passing invalid user name   // user blocking
	@Test(enabled = false, dataProvider = "getFailureloginData")
	public void testLoginUsernameFail1(HashMap<String, String> data) throws InterruptedException {
		setTestName("User Blocking Test");
		driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
		loginPage.openApplication(properties.getUrl());
		loginPage.enterUser(data.get("username"));
		loginPage.enterPassword(data.get("password"));
		loginPage.submitLogin();
		loginPage.clearUsernamePassword();
		loginPage.enterUser(data.get("username1"));
		loginPage.enterPassword(data.get("password1"));
		loginPage.submitLogin();
		loginPage.clearUsernamePassword();
		loginPage.enterUser(data.get("username2"));
		loginPage.enterPassword(data.get("password2"));
		loginPage.submitLogin();
		loginPage.clearUsernamePassword();
		loginPage.enterUser(data.get("username3"));
		loginPage.enterPassword(data.get("password3"));
		loginPage.submitLogin();
		otpPage.blockUserVerify();

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	// Passing empty user name
	@Test(priority = 3, enabled = false, dataProvider = "getFailureloginData")
	public void testLoginUsernameEmpty(HashMap<String, String> data) throws InterruptedException {
		setTestName("Empty Username Test");
		driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
		loginPage.openApplication(properties.getUrl());
		loginPage.enterUser(data.get("username4"));
		loginPage.enterPassword(data.get("password4"));
		loginPage.submitLogin();
		driver.manage().timeouts().implicitlyWait(9, TimeUnit.SECONDS);
	}

	// Passing invalid password
	@Test(priority = 4, enabled = false, dataProvider = "getFailureloginData")
	public void testLoginPasswordInvalid(HashMap<String, String> data) throws InterruptedException {
		setTestName("Invalid Password Test");
		driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
		loginPage.openApplication(properties.getUrl());
		loginPage.enterUser(data.get("username5"));
		loginPage.enterPassword(data.get("password5"));
		loginPage.submitLogin();
		driver.manage().timeouts().implicitlyWait(9, TimeUnit.SECONDS);
	}

	// Passing empty password

	@Test(priority = 5, enabled = false)
	public void testLoginPasswordEmpty() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		loginPage.openApplication(properties.getUrl());
		loginPage.enterUser(properties.getUsername());
		loginPage.enterPassword("");
		loginPage.submitLogin();
		loginPage.verifyFailureMessage();

	}

	// otp success flow

	@Test(priority = 6, enabled = false, dataProvider = "getSuccessOtp")
	public void testOtpSuccess(HashMap<String, String> data) throws InterruptedException {
		setTestName("otp Success Test");
		driver.manage().timeouts().implicitlyWait(9, TimeUnit.SECONDS);
		loginPage.openApplication(properties.getUrl());
		loginPage.enterUser(data.get("username"));
		loginPage.enterPassword(data.get("password"));
		loginPage.submitLogin();
		loginconfirmationPage.clickOnLoginConfirmationLink();
		otpPage.enterOtp(data.get("otpcode"));
		driver.manage().timeouts().implicitlyWait(22, TimeUnit.SECONDS);

	}

	// invalid otp flow
	@Test(priority = 7, enabled = false, dataProvider = "getFailureOtp")
	public void testOtpFailure(HashMap<String, String> data) throws InterruptedException {
		setTestName("Invalid otp Test");
		driver.manage().timeouts().implicitlyWait(9, TimeUnit.SECONDS);
		loginPage.openApplication(properties.getUrl());
		loginPage.enterUser(data.get("username"));
		loginPage.enterPassword(data.get("password"));
		loginPage.submitLogin();
		loginconfirmationPage.clickOnLoginConfirmationLink();
		otpPage.enterOtp(data.get("otpcode"));
		driver.manage().timeouts().implicitlyWait(22, TimeUnit.SECONDS);

	}

	// otp expiry flow
	@Test(priority = 16, enabled = false, dataProvider = "getFailureOtp")
	public void testOtpExpiry(HashMap<String, String> data) throws InterruptedException {
		setTestName("otp Expiry Test");
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		loginPage.openApplication(properties.getUrl());
		loginPage.enterUser(data.get("username"));
		loginPage.enterPassword(data.get("password"));
		loginPage.submitLogin();
		loginconfirmationPage.clickOnLoginConfirmationLink();
		otpPage.enterOtp(data.get("otpcode"));
		Thread.sleep(5 * 60 * 1000);

	}

	// Account statement
	@Test(priority = 9, enabled = false)
	public void testStatement() throws InterruptedException {
		setTestName("Account Statement Test");
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		loginPage.openApplication(properties.getUrl());
		loginPage.enterUser(properties.getUsername());
		loginPage.enterPassword(properties.getPassword());
		loginPage.submitLogin();
		loginconfirmationPage.clickOnLoginConfirmationLink();
		otpPage.enterOtp(properties.getOtp());
		Thread.sleep(6000);
		selectclientPage.clickontestCustomerValidStageslinkText();
		Thread.sleep(6000);
		 //clientaccountPage.clickUserDropDown();
		 //clientaccountPage.clickonAccountDropDown();
		 //clientaccountPage.clickStatementDropDown();
		 //accountStatementPage.selectStatement();
		 //accountStatementPage.clickOnSearchButton();
		
		
		accountStatementPage.clickstatementButton();
		 
		accountStatementPage.clickthisWeekButton();
		Thread.sleep(6000);
		accountStatementPage.clickGenerateStatement();
		Thread.sleep(6000);
	    
	}

	// Payment flow
	
	@Test(priority = 10, enabled = false)
	public void testStatement1() throws InterruptedException {
		setTestName("Account Statement Test");
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		loginPage.openApplication(properties.getUrl());
		loginPage.enterUser(properties.getUsername());
		loginPage.enterPassword(properties.getPassword());
		loginPage.submitLogin();
		loginconfirmationPage.clickOnLoginConfirmationLink();
		otpPage.enterOtp(properties.getOtp());
		selectclientPage.clickontestCustomerValidStageslinkText();
		accountStatementPage.clickOnSearchButton();
		Thread.sleep(6000);
		accountStatementPage.clickthisTitle();
		}
	

	@Test(priority = 11, enabled = false)
	public void testBulkPayment() throws InterruptedException {
		setTestName("Account Statement Test");
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		loginPage.openApplication(properties.getUrl());
		loginPage.enterUser(properties.getUsername());
		loginPage.enterPassword(properties.getPassword());
		loginPage.submitLogin();
		loginconfirmationPage.clickOnLoginConfirmationLink();
		otpPage.enterOtp(properties.getOtp());
		selectclientPage.clickontestCustomerValidStageslinkText();
		Thread.sleep(6000);
		accountStatementPage.clickBulkPayment();
		Thread.sleep(6000);
		}
	
	
	
	@Test(priority = 12, enabled = true)
	public void testMessages() throws InterruptedException {
		setTestName("Account Statement Test");
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		loginPage.openApplication(properties.getUrl());
		loginPage.enterUser(properties.getUsername());
		loginPage.enterPassword(properties.getPassword());
		loginPage.submitLogin();
		loginconfirmationPage.clickOnLoginConfirmationLink();
		otpPage.enterOtp(properties.getOtp());
		selectclientPage.clickontestCustomerValidStageslinkText();
		Thread.sleep(6000);
		accountStatementPage.clickMessages();
		Thread.sleep(6000);
		accountStatementPage.clickgotoInbox();
		Thread.sleep(6000);
		accountStatementPage.clickSent();
		Thread.sleep(6000);
		/*
		 * accountStatementPage.clickWriteSubject(); Thread.sleep(10000);
		 * accountStatementPage.clickWriteSubject1(); Thread.sleep(10000);
		 */
			
		}
	
	
	@Test(priority = 13, enabled = false)
	public void testMyProfile() throws InterruptedException {
		setTestName("Account Statement Test");
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		loginPage.openApplication(properties.getUrl());
		loginPage.enterUser(properties.getUsername());
		loginPage.enterPassword(properties.getPassword());
		loginPage.submitLogin();
		loginconfirmationPage.clickOnLoginConfirmationLink();
		otpPage.enterOtp(properties.getOtp());
		selectclientPage.clickontestCustomerValidStageslinkText();
		Thread.sleep(6000);
		accountStatementPage.clickcheckMyProfile();
		Thread.sleep(6000);
		accountStatementPage.clickcheckManageDevice();
		Thread.sleep(6000);
	//	accountStatementPage.clickcheckPasswordAndSecurity();
	//	Thread.sleep(6000);;
				
		}
	
	
	
	///End to End Flow

	@Test(priority = 16, enabled = false)
	public void testStatementE2E() throws InterruptedException {
		setTestName("Account Statement Test");
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		loginPage.openApplication(properties.getUrl());
		loginPage.enterUser(properties.getUsername());
		loginPage.enterPassword(properties.getPassword());
		loginPage.submitLogin();
		loginconfirmationPage.clickOnLoginConfirmationLink();
		otpPage.enterOtp(properties.getOtp());
		Thread.sleep(6000);
		selectclientPage.clickontestCustomerValidStageslinkText();
		Thread.sleep(6000);
		 //clientaccountPage.clickUserDropDown();
		 //clientaccountPage.clickonAccountDropDown();
		 //clientaccountPage.clickStatementDropDown();
		 //accountStatementPage.selectStatement();
		 //accountStatementPage.clickOnSearchButton();
				
		accountStatementPage.clickstatementButton();
		 
		accountStatementPage.clickthisWeekButton();
		Thread.sleep(6000);
		accountStatementPage.clickGenerateStatement();
		Thread.sleep(6000);
		
		//Payment flow
		accountStatementPage.clickOnSearchButton();
		Thread.sleep(6000);
		accountStatementPage.clickthisTitle();
		//Bulk Payments
		accountStatementPage.clickBulkPayment();
		Thread.sleep(6000);
		// Messages
		accountStatementPage.clickMessages();
		Thread.sleep(6000);
		accountStatementPage.clickgotoInbox();
		Thread.sleep(6000);
		accountStatementPage.clickSent();
		Thread.sleep(6000);
		
		//Verify Profile
		accountStatementPage.clickcheckMyProfile();
		Thread.sleep(6000);
		accountStatementPage.clickcheckManageDevice();
		Thread.sleep(6000);
		accountStatementPage.clickcheckPasswordAndSecurity();
		Thread.sleep(6000);;
		
		//
	    
	}


	
	
	

	@AfterMethod(alwaysRun = true)
	public void close() {
		flushReports();
	}
}
