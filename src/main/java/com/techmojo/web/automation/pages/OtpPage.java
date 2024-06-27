package com.techmojo.web.automation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.techmojo.web.automation.base.PageBase;

public class OtpPage extends PageBase {
    @FindBy(xpath = "//div[@class='form-group pb-0']/input")
    private WebElement otp;
    @FindBy(xpath = "//*[@class='btn btn-block btn-success']")
    private WebElement submitOtpBtn;

    @FindBy(xpath = "//span[contains(text(),'OTP code has expired')]")
    private WebElement otpExpireMessage;
    
    
    @FindBy(xpath = "//strong[contains( text(),'Your account is locked')]")
    private WebElement blockUserMessage;
    
  
  
    public OtpPage(WebDriver driver, ExtentTest logger) {
        super(driver, logger);
        PageFactory.initElements(driver, this);
    }

    public void enterOtp(String otpCode) {
        this.otp.sendKeys(otpCode);
        logger.log(Status.PASS, "Entered the otp : " + otpCode);
    }

    public void submitOtp() {
        this.submitOtpBtn.click();
        logger.log(Status.PASS, "Clicked the ENTER Button");
        waitForPageLoad();

    }

    public void verifyOtpPage() {
        verifyElementisVisible(otpExpireMessage, 2);
        logger.log(Status.PASS, "OTP code has expired.");
    }
    
    
    public void blockUserVerify() {
        verifyElementisVisible(blockUserMessage, 2);
        logger.log(Status.PASS, "Your Account is Blocked.");
    }
    
    
    
}