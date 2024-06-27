package com.techmojo.web.automation.config;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class DriverConfiguration {

	@Bean("chrome")
	@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
	public WebDriver getChromeDriver() {
		try {
			System.setProperty("webdriver.chrome.driver",
					"src\\test\\resources\\drivers\\chromedriver_win.exe");
		} catch (Exception e) {

			e.printStackTrace();
		}
		return new ChromeDriver();
	}

	@Bean(name = "firefox")
	@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
	public WebDriver getFirefoxDriver() {
		System.setProperty("webdriver.gecko.driver", getClass().getResource("/drivers/geckodriver_win.exe").getFile());
		return new FirefoxDriver();
	}

	@Bean(name = "internetExplorer")
	@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
	public WebDriver getInternetExplorerDriver() {
		System.setProperty("webdriver.ie.driver", getClass().getResource("/drivers/IEDriverServer.exe").getFile());
		return new InternetExplorerDriver();
	}

	@Bean(name = "opera")
	@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
	public WebDriver getOperaDriver() {
		System.setProperty("webdriver.opera.driver", getClass().getResource("/drivers/operadriver.exe").getFile());
		return new FirefoxDriver();
	}

}
