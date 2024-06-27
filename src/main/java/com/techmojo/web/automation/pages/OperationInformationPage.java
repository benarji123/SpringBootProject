package com.techmojo.web.automation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.techmojo.web.automation.base.PageBase;

public class OperationInformationPage extends PageBase {

    public OperationInformationPage(WebDriver driver, ExtentTest logger) {
        super(driver, logger);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "/html/body/app-root/cp-main/main/div/div/div/cp-initiate-payment/div/div/cp-confirm-payment/div/div/div/div[2]/div[2]/button")
    private WebElement confirmButton;

    @FindBy(id = "smsCodePassword")
    private WebElement otpInput;

    @FindBy(xpath = "/html/body/app-root/cp-main/main/div/div/div/cp-initiate-payment/div/div/cp-confirm-payment/div/div/div[2]/div/form/div[2]/div[1]/button")
    private WebElement otpConfirm;

    public void selectConfirmButton() {
        waitLoad(2);
        scrollDown();
        waitLoad(2);
        this.confirmButton.click();
        logger.log(Status.PASS, "Clicked the Confirm Button");
    }

    public void enterOtp(String otp) {
        waitLoad(2);
        this.otpInput.sendKeys(otp);
        logger.log(Status.PASS, "Entered the OTP " + otp);
    }

    public void confirmOtp() {
        waitLoad(3);
        scrollDown();
        this.otpConfirm.click();
        logger.log(Status.PASS, "Clicked the OTP confirm Button");
    }

}
