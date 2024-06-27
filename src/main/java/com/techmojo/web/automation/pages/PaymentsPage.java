package com.techmojo.web.automation.pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.techmojo.web.automation.base.PageBase;
import com.techmojo.web.automation.pojo.PaymentDetails;

public class PaymentsPage extends PageBase {

	@FindBy(xpath = "//h2[text()='Payments']/following::select[3]")
	WebElement payerAccountDropdown;

	@FindBy(id = "BeneficiaryName")
	WebElement benefeciaryNameInputBox;

	@FindBy(id = "BeneficiaryAccount")
	WebElement beneficiaryAccount;

	@FindBy(id = "BeneficiaryBankCode")
	WebElement beneficiaryBankCode;

	@FindBy(id = "BeneficiaryBankName")
	WebElement beneficiaryBankName;

	@FindBy(id = "BeneficiaryBankAddress")
	WebElement beneficiaryBankAddress;

	@FindBy(id = "PayerAmount")
	WebElement payerAmount;

	@FindBy(id = "UnstrPayContainer")
	WebElement paymentDetailsTextBox;

	@FindBy(xpath = "//button[text()='Continue']")
	WebElement submitButton;

	public PaymentsPage(WebDriver driver, ExtentTest logger) {
		super(driver, logger);
		PageFactory.initElements(driver, this);
	}

	public void fillTheForm(String payertAccountDetails, String benfeciaryname, String benifeciaryaccount,
			String benifeciarybankCode, String benifeciarybankName, String amount,String paymentdetailsText) {
		System.out.println("***" +payertAccountDetails);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		//selectDropDownByPartialText(payerAccountDropdown, payertAccountDetails);
		selectDropDownValueByIndex(payerAccountDropdown, 2);
		logger.log(Status.PASS, "Select the Payer Account DropDown" + payertAccountDetails);
		System.out.println("***" +benfeciaryname);
		benefeciaryNameInputBox.sendKeys(benfeciaryname);
		logger.log(Status.PASS, "Select the Payer Account DropDown" + benfeciaryname);
		beneficiaryAccount.sendKeys(benifeciaryaccount);

		beneficiaryBankCode.sendKeys(benifeciarybankCode);
		logger.log(Status.PASS, "Enter the bankCode" + benifeciarybankCode);
		
		scrollDown();
		waitLoad(1);

		js.executeScript("window.scrollBy(0,1000)");
		beneficiaryBankName.sendKeys(benifeciarybankName);
		logger.log(Status.PASS, "Enter the Benifeciar Bank Name" +benifeciarybankName);
		
		payerAmount.sendKeys(amount);
		logger.log(Status.PASS, "Enter the payer Amount" +amount);
		
		paymentDetailsTextBox.sendKeys(paymentdetailsText);
		logger.log(Status.PASS, "Enter the PaymentDetails" +paymentdetailsText);
		
	}

	public void clickSubmit() {
		this.submitButton.click();
		logger.log(Status.PASS, "Clicked the Submit Button");
	}

}
