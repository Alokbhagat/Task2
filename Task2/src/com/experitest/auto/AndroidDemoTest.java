package com.experitest.auto;

import java.net.URL;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import java.lang.reflect.Method;
 


// for android test cases 
public class AndroidDemoTest extends BaseTest {
	
	protected AndroidDriver<AndroidElement> driver = null;
	// this parameters are passed from testNg xml 
	@BeforeMethod
	@Parameters("deviceQuery")
	public void setUp(@Optional("@os='android'") String deviceQuery,Method method) throws Exception{
		init(deviceQuery);
		
		// this are test specific capabiliites
		dc.setCapability(MobileCapabilityType.NO_RESET, true);
		dc.setCapability("instrumentApp", true);
		dc.setCapability(MobileCapabilityType.APP, "cloud:com.experitest.ExperiBank/.LoginActivity");
		dc.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "com.experitest.ExperiBank");
		dc.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, ".LoginActivity");
		dc.setCapability(MobileCapabilityType.ORIENTATION, "PORTRAIT");
		// for reporter setting up the test names etc
		dc.setCapability("testName", method.getName());
		dc.setCapability("project", getProperty("project",cloudProperties));
		driver = new AndroidDriver<>(new URL(getProperty("url",cloudProperties) + "/wd/hub"), dc);
		// just making sure app is installed on the device 
		if(driver.isAppInstalled("com.experitest.ExperiBank") != true){			
			driver.installApp("cloud:com.experitest.ExperiBank/.LoginActivity");
			}		
	}	
	
	// Test case to launch the application and login into application
	@Test
	public void launchApplicationTest(){
		  driver.context("NATIVE_APP");
		  new WebDriverWait(driver, 20).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='usernameTextField']")));
	 	  driver.findElement(By.xpath("//*[@id='usernameTextField']")).sendKeys("company");
		  new WebDriverWait(driver, 20).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='passwordTextField']")));
		  driver.findElement(By.xpath("//*[@id='passwordTextField']")).sendKeys("company");
		  new WebDriverWait(driver, 20).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='loginButton']")));
	      driver.findElement(By.xpath("//*[@id='loginButton']")).click();		  
	 }	
	
	//test case to login into application - making payments 
	@Test
	public void loginApplicationTest(){	
		  driver.context("NATIVE_APP");
		  new WebDriverWait(driver, 20).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='usernameTextField']")));
	 	  driver.findElement(By.xpath("//*[@id='usernameTextField']")).sendKeys("company");
	 	  new WebDriverWait(driver, 20).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='passwordTextField']")));
		  driver.findElement(By.xpath("//*[@id='passwordTextField']")).sendKeys("company");
		  new WebDriverWait(driver, 20).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='loginButton']")));
		  driver.findElement(By.xpath("//*[@id='loginButton']")).click();		  
		  new WebDriverWait(driver, 20).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='makePaymentButton']")));
		  driver.findElement(By.xpath("//*[@text='Make Payment']")).click();		  
		  new WebDriverWait(driver, 20).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='phoneTextField']")));
		  driver.findElement(By.xpath("//*[@id='phoneTextField']")).sendKeys("9923123450");
		  driver.findElement(By.xpath("//*[@id='nameTextField']")).sendKeys("Alok");
		  driver.findElement(By.xpath("//*[@id='amountTextField']")).sendKeys("20");
		  driver.findElement(By.xpath("//*[@id='countryButton']")).click();
		  new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='rowTextView']")));
		  int height = driver.findElement(By.xpath("//*[@id='countryList']")).getSize().getHeight();
		  driver.findElement(By.xpath("//*[@id='countryList']//*[4]")).click();
 		  new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='sendPaymentButton']")));
		  driver.findElement(By.xpath("//*[@id='sendPaymentButton']")).click();
		  driver.findElement(By.xpath("//*[@id='button1' and @text='Yes']")).click();
		  new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='logoutButton']")));
		  driver.findElement(By.xpath("//*[@id='logoutButton']")).click();	  
	 }			
//this is teardown - once execution is done - driver will be released - you can add other code in this to clean the enviornment
	@AfterMethod
	public void tearDown(){
			driver.quit();
	}
	
	
	
	
}
