package com.GBHSR.stepDefinition;
 
import java.io.File;
import java.io.IOException;
import java.util.Date;
 
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
 
import com.GBHSR.BaseFactory.baseClass;
import com.GBHSR.plugins.ScenarioCounter;
import com.GBHSR.utilites.ExtentManager;
import com.GBHSR.utilites.Log;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
 
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.junit.Assert;
 
public class hooks {
 
    public static ExtentReports extent;
    public static ExtentTest test;
 
    private static WebDriver driver;
 
    public static ExtentTest getTest() {
        return test;
    }
 
    public static WebDriver getDriver() {
        return driver;
    }
 
    private static Scenario scenario;
 
    public ScenarioCounter scenarioCount = new ScenarioCounter();
 
    public static Scenario getScenario() {
        return scenario;
    }
 
    public static boolean abortScenario = false;
 
    @Before
    public void setup(Scenario scenario) {
 
        if (extent == null) {
            extent = ExtentManager.getInstance(scenario.getName());
        }
        test = extent.createTest(scenario.getName());
        hooks.scenario = scenario;
    }
 
    @AfterStep
    public void addScreenshot(Scenario scenario) throws IOException {
     
        // Capture logs BEFORE clearing
        String logs = Log.getLogs();
     
        // If step failed
        if (scenario.isFailed()) {
     
            // // Failure reason
            // if (failureReason != null) {
     
            //     test.fail(
            //         "<b>Failure Reason :</b><br>" +
            //         failureReason
            //     );
            // }
     
            // Failed step logs
            if (logs != null && !logs.trim().isEmpty()) {
     
                test.fail(
                    "<details><summary>Failed Step Logs</summary>"
                    + "<pre>" + logs + "</pre>"
                    + "</details>"
                );
            }
     
            // Screenshot
            String base64Screenshot = captureBase64Screenshot();
     
            test.fail(
                "Failure Screenshot",
                MediaEntityBuilder
                    .createScreenCaptureFromBase64String(base64Screenshot)
                    .build()
            );
     
            // Physical screenshot
            String screenshotPath =
                    captureScreenshot(scenario.getName());
     
            test.fail("Step Failed")
                .addScreenCaptureFromPath(screenshotPath);
     
        } else {
     
            // Passed step logs
            if (logs != null && !logs.trim().isEmpty()) {
     
                test.info(
                    "<details><summary>Step Logs</summary>"
                    + "<pre>" + logs + "</pre>"
                    + "</details>"
                );
            }
        }
     
        // Clear only AFTER everything added
        Log.clearLogs();
     
        // Reset failure reason
        // failureReason = null;
    }
    
    @After
    public void teardown(Scenario scenario) {
 
        try {
       
        if (test != null) {
            if (scenario.isFailed()) {
                test.fail("Scenario failed");
            } else {
                test.pass("Scenario passed");
            }
        }
 
        if (baseClass.driver != null) {
            baseClass.driver.quit();
        }
        if (extent != null) {
            extent.flush();
        }
       
           
        } catch (Exception e) {
            Log.addLog(e.getMessage());
        }
    }
 
    public String captureScreenshot(String scenarioName) throws IOException {
 
        TakesScreenshot ts = (TakesScreenshot) baseClass.driver;
        File src = ts.getScreenshotAs(OutputType.FILE);
        String path = "screenshots/" + scenarioName + "_" + new Date().getTime() + ".png";
        File dest = new File(path);
        FileUtils.copyFile(src, dest);
        return path;
    }
 
    public String captureBase64Screenshot() {
 
        TakesScreenshot ts = (TakesScreenshot) baseClass.driver;
 
        return ts.getScreenshotAs(OutputType.BASE64);
    }
 
    public static String failureReason;

// public static void failStep(String message, Exception e) {
 
//     failureReason =
//             message
//             + "<br><br><b>Exception :</b> "
//             + e.getClass().getSimpleName()
//             + "<br><br><b>Error Message :</b> "
//             + e.getMessage();
 
//     Log.addLog("FAILED : " + failureReason);
 
//     Assert.fail(failureReason);
// }

public static void failStep(String message) {
 
    failureReason = message;
 
    Log.addLog("FAILED : " + failureReason);
 
    Assert.fail(failureReason);
}
 
}