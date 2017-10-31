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
import io.appium.java_client.remote.MobileCapabilityType;

import com.experitest.manager.api.ManagerPublisher;
import com.experitest.manager.client.PManager;
import com.experitest.manager.testng.ManagerTestNGListener;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.*;
 
import java.io.File;
import java.lang.reflect.Method;
 

public class IOSDemoTest extends BaseTest {
	protected IOSDriver<IOSElement> driver = null;

	@BeforeMethod
	@Parameters("deviceQuery")
	public void setUp(@Optional("@os='ios'") String deviceQuery,Method method) throws Exception {
		init(deviceQuery);
		
		// Init application / device capabilities
		//dc.setCapability(MobileCapabilityType.APP, "cloud:com.experitest.ExperiBank");
		//dc.setCapability(IOSMobileCapabilityType.BUNDLE_ID, "com.experitest.ExperiBank");
		dc.setCapability("build", 16);
		driver = new IOSDriver<>(new URL(getProperty("url",cloudProperties) + "/wd/hub"), dc);
	}

	@Test
	public void test() {
		
     driver.get("http://experitest.com/customers/");		
		
		List<IOSElement> customers = driver.findElements(By.xpath("//*[@id='div']/img"));	       
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
	public void tearDown() {
	
		driver.quit();
	}

}
