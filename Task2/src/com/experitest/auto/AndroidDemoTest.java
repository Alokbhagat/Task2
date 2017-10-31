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
		
		dc.setCapability(MobileCapabilityType.APP, "cloud:com.experitest.ExperiBank/.LoginActivity");
		dc.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "com.experitest.ExperiBank");
		dc.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, ".LoginActivity");
		dc.setCapability(MobileCapabilityType.ORIENTATION, "PORTRAIT");
				
		dc.setCapability("TestName", method.getName());
        
		//dc.setCapability("build", 16);		
		driver = new AndroidDriver<>(new URL(getProperty("url",cloudProperties) + "/wd/hub"), dc);
		System.out.println("runing : " + method.getName());
	}
			
	@Test
	public void LaunchApplicationTest(){	
		
		 //driver.startActivity("cloud:com.experitest.ExperiBank", ".LoginActivity");

				
		  driver.context("NATIVE_APP");
		 
		  try{
			  Thread.sleep(2000);
			  } catch(Exception ignore){}
		 // driver.startActivity("cloud:com.experitest.ExperiBank", ".LoginActivity");
		  driver.context("NATIVE_APP");
		  driver.findElement(By.xpath("//*[@id='usernameTextField']")).sendKeys("company");
		  driver.findElement(By.xpath("//*[@id='passwordTextField']")).sendKeys("company");
		  driver.findElement(By.xpath("//*[@id='loginButton']")).click();

		  
	 }
	
	
	@Test
	public void LoginApplicationTest(){	
		 //driver.startActivity("cloud:com.experitest.ExperiBank", ".LoginActivity");

		
		  driver.context("NATIVE_APP");
		 
		  try{
			  Thread.sleep(2000);
			  } catch(Exception ignore){}
		 // new WebDriverWait(driver, 100).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='usernameTextField']")));
		  driver.findElement(By.xpath("//*[@id='usernameTextField']")).sendKeys("company");
		 // new WebDriverWait(driver, 100).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='passwordTextField']")));
		  driver.findElement(By.xpath("//*[@id='passwordTextField']")).sendKeys("company");
		  driver.findElement(By.xpath("//*[@id='loginButton']")).click();
		  
	 }	
	
	@Test
	public void MakePayMentTest(){	
		 		
		  driver.context("NATIVE_APP");
		
		  try{
			  Thread.sleep(2000);
			  } catch(Exception ignore){}
		 // driver.startActivity("cloud:com.experitest.ExperiBank", ".LoginActivity");
		  driver.context("NATIVE_APP");
		  driver.findElement(By.xpath("//*[@id='usernameTextField']")).sendKeys("company");
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
		  new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='alertTitle']")));
		  driver.findElement(By.xpath("//*[@id='button1' and @text='Yes']")).click();
		  new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='logoutButton']")));
		  driver.findElement(By.xpath("//*[@id='logoutButton']")).click();

	 }
	
	@Test
	public void LogoutApplicationTest(){	
		 //driver.startActivity("cloud:com.experitest.ExperiBank", ".LoginActivity");

		
		  driver.context("NATIVE_APP");
		
		  try{
			  Thread.sleep(2000);
			  } catch(Exception ignore){}
		 // new WebDriverWait(driver, 100).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='usernameTextField']")));
		  driver.findElement(By.xpath("//*[@id='usernameTextField']")).sendKeys("company");
		 // new WebDriverWait(driver, 100).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='passwordTextField']")));
		  driver.findElement(By.xpath("//*[@id='passwordTextField']")).sendKeys("company");
		  driver.findElement(By.xpath("//*[@id='loginButton']")).click();
		  new WebDriverWait(driver, 60).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='logoutButton']")));
		  driver.findElement(By.xpath("//*[@id='logoutButton']")).click();
		  
	 }		  
	
	@Test
	public void WebApplication(){
		
		driver.get("http://experitest.com/customers/");		
		
		List<AndroidElement> customers = driver.findElements(By.xpath("//*[@id='div']/img"));	       
		System.out.println("Total Number of  Customers = " + customers.size());
		String companyName;
        int i = 0;
		
		for (WebElement webElement : customers) {
			i++;
			if (i > 20){
				break;
			}			
			companyName = webElement.getAttribute("class");
			System.out.println("Customer Name:  " + companyName);
		}
		
		
	}

	@AfterMethod
	public void tearDown(){
		System.out.println("tear down");
		driver.quit();
	}
	
	
	
	
}
