package com.experitest.auto;

import java.net.URL;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.remote.IOSMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;

import com.experitest.manager.api.ManagerPublisher;
import com.experitest.manager.client.PManager;
import com.experitest.manager.testng.ManagerTestNGListener;
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
		
		dc.setCapability("testName", "iosWebTest");
		// Enter the test code

	}

	@AfterMethod
	public void tearDown() {
	
		driver.quit();
	}

}
