package com.experitest.auto;

import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.ScreenOrientation;
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
import com.experitest.manager.api.ManagerPublisher;
import com.experitest.manager.client.PManager;
import com.experitest.manager.testng.ManagerTestNGListener;
import org.testng.annotations.*;
 
import java.io.File;
import java.lang.reflect.Method;
 
@Listeners(ManagerTestNGListener.class)
public class AndroidDemoTest extends BaseTest {
	protected AndroidDriver<AndroidElement> driver = null;
	
	@BeforeMethod
	@Parameters("deviceQuery")
	public void setUp(@Optional("@os='android'") String deviceQuery,Method method) throws Exception{
		init(deviceQuery);
		 managerPublisher = PManager.createManagerPublisher(method.getName());
		// Init application / device capabilities
		//dc.setCapability(MobileCapabilityType.APP, "cloud:com.experitest.ExperiBank/.LoginActivity");
		//dc.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "com.experitest.ExperiBank");
		//dc.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, ".LoginActivity");
		//dc.setCapability(AndroidMobileCapabilityType.CHROME_OPTIONS, "");
		dc.setCapability("testName", "AndroidDemoTest");
		driver = new AndroidDriver<>(new URL(getProperty("url",cloudProperties) + "/wd/hub"), dc);
	}
	
	@Test
	public void webTest(){
		 managerPublisher.addProperty("team", "AloksTask2");		
		// driver.executeScript("client:client.setDevice(\"adb:HTC One A9\")");
		
		  //driver.startActivity("Chrome:http://Experitest.com", "experitest.com");
		  driver.get("http://experitest.com");		
		  try{
			  Thread.sleep(2000);
			  } catch(Exception ignore){}
		  driver.context("WEBVIEW_1");
		  new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='free trial']")));
		  driver.findElement(By.xpath("//*[@text='free trial']")).click();
		  driver.findElement(By.xpath("//*[@nodeName='BUTTON' and ./parent::*[@text]]")).click();
		  driver.findElement(By.xpath("//*[@text='Capabilities']")).click();
		  new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='Test Development']")));
		  driver.findElement(By.xpath("//*[@text='Test Development']")).click();		  
		  new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='Appium Studio']")));
		  driver.findElement(By.xpath("//*[@text='Appium Studio']")).click();
		  driver.executeScript("client:client.swipe(\"Down\", 300, 500)");
		 }
	
	@Test
	public void nativeTest(){	
		 managerPublisher.addProperty("team", "AloksTask2");		
		 // driver.executeScript("client:client.setDevice(\"adb:HTC One A9\")");
		  //driver.rotate(ScreenOrientation.PORTRAIT);
		  driver.unlockDevice();
		  driver.installApp("cloud:com.experitest.ExperiBank/.LoginActivity");
		  try{
			  Thread.sleep(2000);
			  } catch(Exception ignore){}
		  try{
		  driver.startActivity("com.experitest.ExperiBank", ".LoginActivity");
		  
		  }catch(Exception ex){
			  
			  System.out.println(ex.getMessage());
		  }
		  driver.context("NATIVE_APP");
		  new WebDriverWait(driver, 100).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='usernameTextField']")));
		  driver.findElement(By.xpath("//*[@id='usernameTextField']")).sendKeys("company");
		  new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='passwordTextField']")));
		  driver.findElement(By.xpath("//*[@id='passwordTextField']")).sendKeys("company");
		  driver.findElement(By.xpath("//*[@id='loginButton']")).click();
		  new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='makePaymentButton']")));
		  driver.findElement(By.xpath("//*[@id='makePaymentButton']")).click();
		  new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='phoneTextField']")));
		  driver.findElement(By.xpath("//*[@id='phoneTextField']")).sendKeys("9923123450");
		  driver.findElement(By.xpath("//*[@id='nameTextField']")).sendKeys("Alok");
		  driver.findElement(By.xpath("//*[@id='amountTextField']")).sendKeys("20");
		  driver.findElement(By.xpath("//*[@id='countryButton']")).click();
		  new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='rowTextView']")));
		 // driver.findElement(By.xpath("//*[@id='countryButton']"));
		  int height = driver.findElement(By.xpath("//*[@id='countryList']")).getSize().getHeight();
		  //int width = driver.findElement(By.xpath("//*[@id='countryList']")).getSize().getWidth();	  
		 // int size = height * width;	
		  //System.out.print(height/3);
		
		  //String str1 = (String)driver.executeScript("client:client.swipeWhileNotFound(\"Down\", 100, 2000, \"//*[@text='Spain']\", 1000, 5, false)");
		  driver.findElement(By.xpath("//*[@id='countryList']//*[4]")).click();

		  //driver.findElement(By.xpath("//*[@text='Spain']")).click();

		  new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='sendPaymentButton']")));
		  driver.findElement(By.xpath("//*[@id='sendPaymentButton']")).click();
		  new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='alertTitle']")));
		  driver.findElement(By.xpath("//*[@id='button1' and @text='Yes']")).click();
		  new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='logoutButton']")));
		  driver.findElement(By.xpath("//*[@id='logoutButton']")).click();
		  driver.pressKeyCode(AndroidKeyCode.HOME);

		 }
		

	@AfterMethod
	public void tearDown(){
		managerPublisher.addReportFolder(new File("c:\\Users\\alok.bhagat\\git\\Task2"));
		driver.quit();
	}
	
}
