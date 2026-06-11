package com.GBHSR.stepDefinition;

import java.util.Properties;
import org.openqa.selenium.WebDriver;

import com.GBHSR.BaseFactory.baseClass;
import com.GBHSR.pageObjects.loginPage;
import com.GBHSR.pageObjects.registrationPage;
import com.GBHSR.utilites.EncryptionUtil;
import com.GBHSR.utilites.ExcelUtility;
import com.GBHSR.utilites.Log;
import com.GBHSR.utilites.SecretKeyManager;
import com.GBHSR.utilites.commonLib;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class stepDef {

    WebDriver driver;

    loginPage loginpage;
    registrationPage registrationpage;
    ExcelUtility exlUtil = new ExcelUtility();

    baseClass baseclass = new baseClass();
    public static Properties prop = new Properties();

    @Given("I Launch the application")
    public void i_launch_the_application() {

        driver = baseclass.initilizeBrowser();

        loginpage = new loginPage(driver);
        registrationpage = new registrationPage(driver);
        Log.addLog("Application launched successfully");
        
        
    }

    @When("I should be on the Appian ODPP page")
    public void i_should_be_on_the_appian_odpp_page() {

        // Assert.assertTrue(loginpage.appianLogoIsDisplayed());

        commonLib.pageTitleVerification(driver.getTitle(),baseClass.prop.getProperty("loginpageTitle"),"Login page titile is displayed as expected");
        
        commonLib.elementStatus(commonLib.DISPLAYED, loginpage.appianLogoIsDisplayed(), "appian logo");
        Log.addLog("Appian Logo is Displayed");
    }
      
      @When("I enter the credentials")
      public void i_enter_the_credentials() {

          String encryptedUsername = baseClass.prop.getProperty("username").trim();
          String encryptedPassword = baseClass.prop.getProperty("password").trim();
          String decryptedUsername = EncryptionUtil.decrypt(encryptedUsername,SecretKeyManager.SECRET_KEY);
          String decryptedPassword = EncryptionUtil.decrypt(encryptedPassword, SecretKeyManager.SECRET_KEY);
 
          if(loginpage.getUsername().isDisplayed() && loginpage.getPassword().isDisplayed())
          {
            commonLib.elementStatus(commonLib.ENABLED, loginpage.getUsername(), "username TextBox");
            commonLib.elementStatus(commonLib.ENABLED, loginpage.getPassword(), "password TextBox");
            loginpage.enterCredentials(decryptedUsername,decryptedPassword);
          }
          else {
            hooks.failStep(
                    "Username or Password textbox is not displayed");
        }
 
          Log.addLog("Credentials entered");
      }
      

   @When("I click on Remember me checkbox")
    public void i_click_on_remember_me_checkbox() {
 
    try {
 
        if (!loginpage.rememeberMeCheckBox().isSelected()) {
 
            Log.addLog("Remember Me checkbox already been selected by default");
            loginpage.clickRememeberMeCheckBox();
            Log.addLog("Remember Me checkbox un selected successfully");
            commonLib.elementStatus(commonLib.SELECTED,loginpage.rememeberMeCheckBox(),"Remember Me Checkbox");
 
        }
 
    } catch (Exception e) {
 
        hooks.failStep("Unable to interact with Remember Me checkbox : "+ e.getMessage());
    }
}

    @When("I cick on Signin")
    public void i_cick_on_signin() {

        // loginpage.clickSignInButton();
        // Log.addLog("clicked on Siginin button");
        
        try {
            loginpage.clickSignInButton();
            Log.addLog("Clicked on Signin button");
        } catch (Exception e) {
            hooks.failStep(
                "Unable to click Signin button");
        }
    }


    @Then("I should see the registration page")
    public void i_should_see_the_registration_page() {
      
        if(!loginpage.isErrorMsgDisplayed())
        {
            commonLib.pageTitleVerification(driver.getTitle(),baseClass.prop.getProperty("homepageTitle"),"home page titile is displayed as expected");
            commonLib.elementStatus(commonLib.DISPLAYED, registrationpage.newDeclarationButton(), "newDeclaration Button");
            // Assert.assertTrue(registrationpage.newDeclarationButtonIsDisplayed());
            Log.addLog("Logged in to the application and regestration page is launched");
           
        }else{
            commonLib.elementStatus(commonLib.DISPLAYED, loginpage.errorMsg, "error Message");
            hooks.failStep("Login Failed not able to go to Registration page");
        }
        
        
    }
}