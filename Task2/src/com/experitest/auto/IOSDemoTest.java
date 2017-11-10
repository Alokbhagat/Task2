package com.experitest.auto;

import java.net.URL;
import java.util.List;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.remote.IOSMobileCapabilityType;
import io.appium.java_client.remote.MobileBrowserType;
import io.appium.java_client.remote.MobileCapabilityType;

import com.experitest.manager.api.ManagerPublisher;
import com.experitest.manager.client.PManager;
import com.experitest.manager.testng.ManagerTestNGListener;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
 
import java.io.File;
import java.lang.reflect.Method;
 

public class IOSDemoTest extends BaseTest {
	protected IOSDriver<IOSElement> driver = null;

	@BeforeMethod
	@Parameters("iosdeviceQuery")
	public void setUp(@Optional("@os='ios'") String deviceQuery,Method method) throws Exception {
		init(deviceQuery);
		
		// Init application / device capabilities
		//dc.setCapability(MobileCapabilityType.APP, "cloud:com.experitest.ExperiBank");
		//dc.setCapability(IOSMobileCapabilityType.BUNDLE_ID, "com.experitest.ExperiBank");
		dc.setCapability("testName", method.getName());
	
		dc.setCapability("project", getProperty("project",cloudProperties));
		dc.setBrowserName(MobileBrowserType.SAFARI);
		driver = new IOSDriver<>(new URL(getProperty("url",cloudProperties) + "/wd/hub"), dc);
		System.out.println("runing : " + method.getName());
		System.out.println("runing : " + deviceQuery);
	}

	@Test
	public void getCustomersTest() {
		
        driver.get("http://experitest.com/customers/");		
        driver.context("WEBVIEW_1");
		List<IOSElement> customers = driver.findElements(By.xpath("//*[@id='div']/img"));	       
		System.out.println("Total Number of  Customers = " + customers.size());
		String companyName;
	      int i = 0;
		
		for (WebElement webElement : customers) {
			i++;
			 if(i > 10){
				
				break;
			}
		     companyName = webElement.getAttribute("src");		     
		     String[] words=companyName.split("/");		     
			System.out.println("Customer Name:  " + words[6]);
		}
		
	}
	
	@Test
	public void webLoginApplicationTest() {
		
		  driver.get("experitest.com");
		  
		  driver.context("WEBVIEW_1");
		  try{Thread.sleep(4000);} catch(Exception ignore){}

		  new WebDriverWait(driver, 20).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@nodeName='BUTTON' and @css='BUTTON.navbar-toggle']")));
		  driver.findElement(By.xpath("//*[@nodeName='BUTTON' and @css='BUTTON.navbar-toggle']")).click();
		  new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='Capabilities']")));
		  driver.findElement(By.xpath("//*[@text='Support' and @css=concat('A[href=', \"'\", '#', \"'\", ']')]")).click();
		  new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='Online Guide']")));
		  driver.findElement(By.xpath("//*[@text='Online Guide' and @css=concat('A[href=', \"'\", '#', \"'\", ']')]")).click();


	}

	@AfterMethod
	public void tearDown() {
	
		driver.quit();
	}

}
