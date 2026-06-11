package com.GBHSR.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.GBHSR.BaseFactory.basePage;

public class loginPage extends basePage {

//WebDriver driver;

public loginPage(WebDriver driver) 
{
    super(driver);
}

@FindBy(xpath="//img[@id=\"logo\"]") private WebElement appianLogo;
@FindBy(xpath="//input[@id=\"un\"]")private WebElement unTxtBx;
@FindBy(xpath="//input[@id=\"pw\"]")private WebElement pwdTxtBx;
@FindBy(xpath="//label[@for=\"remember\"]")private WebElement rememberMeChkBx;
@FindBy(xpath="//a[@id=\"forgotPasswordLink\"]")private WebElement forgotPwdLink;
@FindBy(xpath="//input[@id=\"jsLoginButton\"]")private WebElement signinBtn;
@FindBy(xpath="//div[@id=\"errorMsg\"]") public  WebElement errorMsg;

public WebElement appianLogoIsDisplayed()
{
     return appianLogo;
}

public WebElement getUsername()
{
    return unTxtBx;
}

public void setUsername(WebElement unTxtBx)
{
    this.unTxtBx = unTxtBx;
}

public WebElement getPassword()
{
    return pwdTxtBx;
}

public void setPassword(WebElement pwdTxtBx)
{
    this.pwdTxtBx = pwdTxtBx;
}

public void enterCredentials(String username, String password)
{
    unTxtBx.sendKeys(username);
    pwdTxtBx.sendKeys(password);
}

public WebElement rememeberMeCheckBox()
{
    return rememberMeChkBx;
}

public void clickRememeberMeCheckBox()
{
     rememberMeChkBx.click();
}

public void clickForgotPasswordLink()
{
    forgotPwdLink.click();
}

public void clickSignInButton()
{
    signinBtn.click();
}

public boolean isErrorMsgDisplayed() {
 
    try {
 
        return errorMsg.isDisplayed();
 
    } catch (Exception e) {
 
        return false;
    }
}
 

}
