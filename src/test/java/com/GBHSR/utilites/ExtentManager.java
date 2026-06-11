
package com.GBHSR.utilites;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager {
	

	public static ExtentReports extent;
	
	 
	    public static ExtentReports getInstance(String scenarioName) {
	        if (extent == null) {
	        	
	        	 String timestamp = LocalDateTime.now()
	                     .format(DateTimeFormatter.ofPattern("ddMMyyyy_HHmmss"));
	        	 String reportPath = "Reports/"+ scenarioName + "_" + timestamp + "_Report.html";
	            ExtentSparkReporter reporter = new ExtentSparkReporter(reportPath);
	 
	            extent = new ExtentReports();
	            extent.attachReporter(reporter);
	            reporter.config().setTheme(Theme.DARK);
			     reporter.config().setDocumentTitle("GBHSR Automation "+scenarioName+" Report");
			     reporter.config().setReportName("GBHSR Automation "+scenarioName+" Report");
			     reporter.config().setEncoding("UTF-8");
			     extent.setSystemInfo("Browser", "Edge");
	              extent.setSystemInfo("Tester", System.getProperty("user.name"));
	            extent.setSystemInfo("Environment", "QA");
	        }
	        return extent;
	    }
}
