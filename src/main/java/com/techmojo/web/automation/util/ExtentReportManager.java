package com.techmojo.web.automation.util;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

public class ExtentReportManager {

	private ExtentReportManager() {
	}

	private static ExtentReports report;

	public static ExtentReports getReportInstance() {
		if (report == null) {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
			LocalDateTime now = LocalDateTime.now();
			String currentDate = dtf.format(now);
			String reportName = "OnlineBanking_" + currentDate + ".html";
			ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(
					System.getProperty("user.dir") + "/test-output/" + reportName);
			report = new ExtentReports();
			report.attachReporter(htmlReporter);
			report.setSystemInfo("OS", "Windows 10");
			report.setSystemInfo("Environment", "UAT");
			report.setSystemInfo("Build Number", "19041.450");
			report.setSystemInfo("Browser", "Chrome");
			htmlReporter.config().setDocumentTitle("UAT UI Automation Results");
			htmlReporter.config().setReportName("All Headlines UI Test Report");
			htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
			htmlReporter.config().setTimeStampFormat("MMM dd, yyyy HH:mm:ss");
		}

		return report;
	}
}