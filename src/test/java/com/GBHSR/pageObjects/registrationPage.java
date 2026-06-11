package com.GBHSR.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.GBHSR.BaseFactory.basePage;

public class registrationPage extends basePage {

public registrationPage(WebDriver driver) 
{
    super(driver);
}
    
@FindBy(xpath="//button[@type='button']/span[text()='New Declaration']") private WebElement newDeclarationBtn;

public WebElement newDeclarationButton()
{
    return newDeclarationBtn;
}

 
}
