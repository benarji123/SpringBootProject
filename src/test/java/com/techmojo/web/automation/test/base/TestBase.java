package com.techmojo.web.automation.test.base;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.techmojo.web.automation.AutomationApplication;
import com.techmojo.web.automation.config.CustomProperties;
import com.techmojo.web.automation.exceptions.DriverNotFoundException;

@SpringBootTest(classes = AutomationApplication.class)
public class TestBase extends AbstractTestNGSpringContextTests {

    @Autowired
    private ApplicationContext applicationContext;
    protected WebDriver driver;
    protected ExtentTest logger;
    protected Properties prop;

    @Autowired
    protected CustomProperties properties;

    @Autowired
    protected ExtentReports report;

    public void setBrowser(String browserName) {
        switch (browserName) {
        case "CHROME":
            driver = applicationContext.getBean("chrome", WebDriver.class);
            break;
        case "FIREFOX":
            driver = applicationContext.getBean("firefox", WebDriver.class);
            break;
        case "INTERNET_EXPLORER":
            driver = applicationContext.getBean("internetExplorer", WebDriver.class);
            break;
        case "OPERA":
            driver = applicationContext.getBean("opera", WebDriver.class);
            break;
        default:
            throw new DriverNotFoundException();
        }
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    public void setTestName(String testName) {
        this.logger = report.createTest(testName);
    }
    

    public void flushReports() {
        report.flush();
        driver.close();
    }
}