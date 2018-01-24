package com.experitest.auto;

import java.net.URL;
import java.util.List;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.remote.MobileBrowserType;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait; 
import java.lang.reflect.Method;
 

//Ios test cases 
public class IOSDemoTest extends BaseTest {
	//create the driver 
	protected IOSDriver<IOSElement> driver = null;
	@BeforeMethod
	@Parameters("iosdeviceQuery")
	public void setUp(@Optional("@os='ios'") String deviceQuery,Method method) throws Exception {
		init(deviceQuery);		
		//setting up the desired capabilities - you can set more properties in side this 
		dc.setCapability("testName", method.getName());	
		dc.setCapability("project", getProperty("project",cloudProperties));
		dc.setBrowserName(MobileBrowserType.SAFARI);
		//From cloud.properties all setting are getting populated 
		driver = new IOSDriver<>(new URL(getProperty("url",cloudProperties) + "/wd/hub"), dc);	
	}

	//Test case to get customer list from experitest.com
	@Test
	public void getCustomersTest() {		
		int i = 0;		
		String companyName;
        driver.get("http://experitest.com/customers/");		
        driver.context("WEBVIEW_1");
        //getting all logo web element in side the list 
		List<IOSElement> customers = driver.findElements(By.xpath("//*[@id='div']/img"));      
		//reading the web element info from list 
		for (WebElement webElement : customers) {
			i++;
			// Since there are lot of customers so restricted to print only 10 customers
			 if(i > 10){				
				break;
			}
			 // spliting up the string img src to short names
		     companyName = webElement.getAttribute("src");		     
		     String[] words=companyName.split("/");		     
			System.out.println("Customer Name:  " + words[6]);
			}	
	}
	
	//Test case to open experitest and click on online guide 
	@Test
	public void webLoginApplicationTest() {		
		  driver.get("http://experitest.com");
		  driver.context("WEBVIEW_1");
		  try{Thread.sleep(4000);} catch(Exception ignore){}
		  new WebDriverWait(driver, 100).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@nodeName='BUTTON' and @css='BUTTON.navbar-toggle']")));
		  driver.findElement(By.xpath("//*[@nodeName='BUTTON' and @css='BUTTON.navbar-toggle']")).click();
		  new WebDriverWait(driver, 30).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='Capabilities']")));
		  driver.findElement(By.xpath("//*[@text='Support' and @css=concat('A[href=', \"'\", '#', \"'\", ']')]")).click();
		  new WebDriverWait(driver, 30).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='Online Guide']")));
		  driver.findElement(By.xpath("//*[@text='Online Guide' and @css=concat('A[href=', \"'\", '#', \"'\", ']')]")).click();

	}
	//this is teardown - once execution is done - driver will be released - you can add other code in this to clean the enviornment
	@AfterMethod
	public void tearDown() {	
		driver.quit();
	}

}
