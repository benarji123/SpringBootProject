package com.techmojo.web.automation.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.aventstack.extentreports.ExtentReports;
import com.techmojo.web.automation.util.ExtentReportManager;

@Configuration
public class SessionConfiguration {
    @Bean
    public ExtentReports getExtentReporter() {
        return ExtentReportManager.getReportInstance();
    }
}
