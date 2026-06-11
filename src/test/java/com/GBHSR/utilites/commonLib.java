package com.GBHSR.utilites;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.GBHSR.BaseFactory.baseClass;
import com.GBHSR.stepDefinition.hooks;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;

public class commonLib extends baseClass {

	WebDriver driver;

	public static final int DISPLAYED = 1;
	public static final int ENABLED = 2;
	public static final int SELECTED = 3;

	public void backNavigation() {
		driver.navigate().back();
	}

	public void frontnavigation() {
		driver.navigate().forward();
	}

	public void pageRefresh() {
		driver.navigate().refresh();
	}

	public static void elementStatus(int checkType, WebElement element, String elementName) {
		switch (checkType) {
		case DISPLAYED:
    try {
 
        if (element.isDisplayed()) {
 
            hooks.test.pass(MarkupHelper.createLabel("✅ " + elementName + " is DISPLAYED",ExtentColor.GREEN));
 
        } else {
 
            hooks.failStep(elementName + " is NOT DISPLAYED");
        }
 
    } 
    catch (Exception e) {
 
        hooks.test.fail(MarkupHelper.createLabel("❌ Unable to verify DISPLAYED status for "+ elementName+ "<br><br>Error : " + e.getMessage(),ExtentColor.RED));
    	Assert.fail("Unable to verify DISPLAYED status for "+ elementName+ " : "+ e.getMessage());
	}
 
    break;
 
		case ENABLED:
 
			try {
 
				if (element.isEnabled()) {
 
					hooks.test.pass(MarkupHelper.createLabel("✅ " + elementName + " is ENABLED", ExtentColor.GREEN));
 
				} else {
 
					hooks.test.fail(MarkupHelper.createLabel("❌ " + elementName + " is DISABLED", ExtentColor.RED));
				}
 
			} catch (Exception e) {
 
				hooks.test.fail(MarkupHelper.createLabel("❌ Unable to verify ENABLED status for : " + elementName + "<br><br>Error : " + e.getMessage(),ExtentColor.RED));
				Assert.fail("Unable to verify ENABLED status for "+ elementName+ " : "+ e.getMessage());
			}
 
			break;
 
		case SELECTED:
 
			try {
 
				if (element.isSelected()) {
 
					hooks.test.pass(MarkupHelper.createLabel("✅ " + elementName + " is SELECTED", ExtentColor.TEAL));
 
				} else {
 
					// hooks.test.warning(MarkupHelper.createLabel("⚠️ " + elementName + " is NOT SELECTED", ExtentColor.RED));
					hooks.test.info(MarkupHelper.createLabel("ℹ️ " + elementName + " is NOT SELECTED",ExtentColor.ORANGE));
				}
 
			} catch (Exception e) {
 
				hooks.test.fail(MarkupHelper.createLabel("❌ Unable to verify SELECTED status for : " + elementName + "<br><br>Error : " + e.getMessage(),ExtentColor.RED));
				Assert.fail("Unable to verify SELECTED status for "+ elementName+ " : "+ e.getMessage());
			}
 
			break;
 
		default:
 
			hooks.test.warning(MarkupHelper.createLabel("⚠️ Invalid check type provided", ExtentColor.YELLOW));
		}
	}


	// public static void pageTitleVerification(String actual, String expected, String pagetitle) {
	// 	Assert.assertEquals(actual, expected);

	// 	if (actual.equals(expected)) {
	// 		Assert.assertEquals(pagetitle + " is displayed, passed", true);
	// 	} else {
	// 		Assert.assertEquals(pagetitle + " is not displayed, failed", false);
	// 	}
	// }

	public static void pageTitleVerification(String actual, String expected, String pageTitle)
	{
		Assert.assertEquals(actual, expected, pageTitle + " title verification failed");
		hooks.test.pass(pageTitle + " is displayed");
	}

	public void webdrvierwait(By element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	}

	public void staticDropDownVisibleTxt(WebElement element, String text) {
		Select sel = new Select(element);
		sel.selectByVisibleText(text);
	}

	public void staticDropDownByIndex(WebElement element, String text, int number) {
		Select sel = new Select(element);
		sel.selectByIndex(number);
	}

	public void staticDropDownByValue(String value, WebElement element) {
		Select sel = new Select(element);
		sel.selectByValue(value);
	}

	public void staticDropDownDeSelectAll(WebElement element) {
		Select sel = new Select(element);
		sel.deselectAll();
	}

	public void staticDropDownByIndex(int index, WebElement element) {
		Select sel = new Select(element);
		sel.deselectByIndex(index);
	}

	public void ActionsClass(WebElement element) {
		Actions action = new Actions(driver);
		action.moveToElement(element).click().perform();
	}

	public Robot robotclassEnter() throws AWTException {
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_ENTER);
		return robot;
	}

	public void robotclassDown() throws AWTException {
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_DOWN);
	}

	public void robotclassUp() throws AWTException {
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_UP);
	}

	public void robotclassRight() throws AWTException {
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_RIGHT);
	}

	public void robotclassLeft() throws AWTException {
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_LEFT);
	}

	public void getpageScreenshot(String string) {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		String dest = "./screenshots/" + new Date().getTime() + ".png";
		File destination = new File(dest);
		try {
			FileUtils.copyFile(src, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

//		public void javaScript(JavascriptExecutor JavascriptExecutor)
//		{
//			JavascriptExecutor js = JavascriptExecutor;
//			js.executeScript(null, null)
//		}

}
