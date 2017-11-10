package com.experitest.auto;

import java.net.URL;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.AndroidKeyCode;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;

import com.experitest.manager.api.ManagerPublisher;
import com.experitest.manager.client.PManager;
import com.experitest.manager.testng.ManagerTestNGListener;
import org.testng.annotations.*;
 
import java.io.File;
import java.lang.reflect.Method;
 

public class AndroidDemoTest extends BaseTest {
	
	protected AndroidDriver<AndroidElement> driver = null;
	
	@BeforeMethod
	@Parameters("deviceQuery")
	public void setUp(@Optional("@os='android'") String deviceQuery,Method method) throws Exception{
		init(deviceQuery);
		dc.setCapability(MobileCapabilityType.NO_RESET, true);
		dc.setCapability("instrumentApp", true);
		dc.setCapability(MobileCapabilityType.APP, "cloud:com.experitest.ExperiBank/.LoginActivity");
		dc.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "com.experitest.ExperiBank");
		dc.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, ".LoginActivity");
		dc.setCapability(MobileCapabilityType.ORIENTATION, "PORTRAIT");
		dc.setCapability("testName", method.getName());
		dc.setCapability("projectName", "Task2");
		driver = new AndroidDriver<>(new URL(getProperty("url",cloudProperties) + "/wd/hub"), dc);
		System.out.println("runing : " + method.getName());
		if(driver.isAppInstalled("com.experitest.ExperiBank") != true){
			
			driver.installApp("cloud:com.experitest.ExperiBank/.LoginActivity");
		}
		
	}
	
	
	// test comment 
	@Test
	public void LaunchApplicationTest(){			 
		
		
		
		  driver.context("NATIVE_APP");
		  new WebDriverWait(driver, 20).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='usernameTextField']")));
	 
		  driver.findElement(By.xpath("//*[@id='usernameTextField']")).sendKeys("company");
		  new WebDriverWait(driver, 20).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='passwordTextField']")));
			 
		  driver.findElement(By.xpath("//*[@id='passwordTextField']")).sendKeys("company");
		  new WebDriverWait(driver, 20).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='loginButton']")));
		  driver.findElement(By.xpath("//*[@id='loginButton']")).click();
		  
		  
	 }
	
	
	@Test
	public void LoginApplicationTest(){	
		 //driver.startActivity("cloud:com.experitest.ExperiBank", ".LoginActivity");
		
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
	
	/*@Test
	public void MakePayMentTest(){	
		 		

		  driver.context("NATIVE_APP");	
		  
		  driver.findElement(By.xpath("//*[@text='Username']")).sendKeys("company");
		 // new WebDriverWait(driver, 100).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='passwordTextField']")));
		  driver.findElement(By.xpath("//*[@id='passwordTextField']")).sendKeys("company");
		  driver.findElement(By.xpath("//*[@id='loginButton']")).click();
		  new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='makePaymentButton']")));
		  driver.findElement(By.xpath("//*[@text='Make Payment']")).click();
		  
		  new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='phoneTextField']")));
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
	
	@Test
	public void  WebApplicationTest(){	
		 //driver.startActivity("cloud:com.experitest.ExperiBank", ".LoginActivity");
	

		 driver.get("http://experitest.com");
		 driver.context("WEBVIEW_1");
		  new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@nodeName='BUTTON' and @css='BUTTON.navbar-toggle']")));
		  driver.findElement(By.xpath("//*[@nodeName='BUTTON' and @css='BUTTON.navbar-toggle']")).click();
		  new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='Support']")));
		  driver.findElement(By.xpath("//*[@text='Support']")).click();
		  new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='Online Guide']")));
		  driver.findElement(By.xpath("//*[@text='Online Guide']")).click();
		  driver.pressKeyCode(AndroidKeyCode.HOME);
		  
	 }		  
	
	
	
	@Test
	public void  WebApplication2Test(){	
		 //driver.startActivity("cloud:com.experitest.ExperiBank", ".LoginActivity");
	 	 driver.get("http://experitest.com");
		 driver.context("WEBVIEW_1");
		  new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='LOGIN']")));
		  driver.findElement(By.xpath("//*[@text='LOGIN']")).click();
		  new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='Sign up        ']")));
		  driver.findElement(By.xpath("//*[@text='Sign up        ']")).click();
		  new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='Download']")));
		  driver.findElement(By.xpath("//*[@text='Download']")).click();
		  driver.pressKeyCode(AndroidKeyCode.HOME);

		  
	 }*/

	@AfterMethod
	public void tearDown(){
		System.out.println("tear down");
		driver.quit();
	}
	
	
	
	
}
