package com.GBHSR.BaseFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.logging.Logger;

import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeDriverService;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.GBHSR.utilites.ExcelUtility;
import com.GBHSR.utilites.Log;
import com.GBHSR.utilites.constants;

import io.github.bonigarcia.wdm.WebDriverManager;


public class baseClass implements constants{
    
   public static WebDriver driver;
   public static Properties prop = new Properties();
   FileInputStream fis;
   static ExcelUtility ExlUtl = new ExcelUtility();
   public static Logger log;

    public baseClass()
	{
		 prop = new Properties();
		try {
			FileInputStream fis = new FileInputStream(config);
				prop.load(fis);
		    }catch (FileNotFoundException e) {
		        e.printStackTrace();	
			} catch (IOException e) {
				e.printStackTrace();
			}
	} 		
    
    public WebDriver initilizeBrowser()
	{
		PropertyConfigurator.configure(log4jPath);
		String browserName = prop.getProperty("browser");
		if(browserName.equals("chrome"))
		{
			WebDriverManager.chromedriver().setup();
			driver= new ChromeDriver();
			Log.addLog("Chrome browser opened");
		}else if(browserName.equals("firefox"))
		{
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			Log.addLog("Edge browser opened");
			
		}else{
//			WebDriverManager.edgedriver().setup();
//			driver = new EdgeDriver();
			
			EdgeOptions edgeOptions = new EdgeOptions();
			edgeOptions.addArguments("--start-maximized");
			
			EdgeDriverService edgeService = new EdgeDriverService.Builder()
					.usingDriverExecutable(new File(System.getProperty("user.dir")+ "\\src\\test\\resources\\drivers\\msedgedriver.exe"))
					.usingAnyFreePort().build();
			driver = new EdgeDriver(edgeService, edgeOptions);
			
			Log.addLog("Edge browser opened");
			
		}
		
		driver.manage().deleteAllCookies();
		Log.addLog("browser maximised");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
		String appUrl = prop.getProperty("url");
		driver.get(appUrl);
		Log.addLog("URL is Launched");
		return driver;
		
	}
    
    
    
    
    
}
