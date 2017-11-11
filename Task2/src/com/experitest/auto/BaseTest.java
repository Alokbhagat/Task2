package com.experitest.auto;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import org.openqa.selenium.remote.DesiredCapabilities;


//This is base class - device configuration can be done in this 
public class BaseTest {

	// Getting all properties enviornment variables from jenkins 
	public static String buildId = System.getenv("BUILD_NUMBER");
	public static String accessKey =System.getenv("access.key");
	public static String deviceQuery=System.getenv("device.query");
			
	
	// setting up the capabilities - this are general capabilities 
	protected DesiredCapabilities dc = new DesiredCapabilities();
	protected Properties cloudProperties = new Properties();
	public void init(String deviceQuery) throws Exception {
		initCloudProperties();
		
		// getting device from testNg param
		dc.setCapability("deviceQuery", adhocDevice(deviceQuery));							
		dc.setCapability("reportDirectory", "reports");	
		//getting properties from property file 
		dc.setCapability("user", getProperty("username", cloudProperties));
		dc.setCapability("password", getProperty("password", cloudProperties));
		// In case your user is assign to a single project leave empty,
		// otherwise please specify the project name
		dc.setCapability("project", getProperty("project", cloudProperties));
		//this is reporter stream name
		dc.setCapability("stream", "ReporterTask_alok");
		//getting the build id from jenkins 		
		dc.setCapability("build", getBuild());
		
	}

	public synchronized static String getBuild() {
		if(buildId == null) {
			buildId = "-1";
		}
		return buildId;
	}
	
	//getting the properties from cloud.properties file 
	protected String getProperty(String property, Properties props) throws FileNotFoundException, IOException {
		if (System.getProperty(property) != null) {
			return System.getProperty(property);
		} else if (System.getenv().containsKey(property)) {
			return System.getenv(property);
		} else if (props != null) {
			return props.getProperty(property);
		}
		return null;
	}

	private void initCloudProperties() throws FileNotFoundException, IOException {
		FileReader fr = new FileReader("cloud.properties");
		cloudProperties.load(fr);
		fr.close();
	}
//getting jar location to get devices 
	private static synchronized String adhocDevice(String deviceQuery) {
		try {
			File jarLocation = (System.getProperty("os.name").toUpperCase().contains("WIN"))
					? new File(System.getenv("APPDATA"), ".mobiledata")
					: new File(System.getProperty("user.home") + "/Library/Application " + "Support", ".mobiledata");
			File adhocProperties = new File(jarLocation, "adhoc.properties");
			if (adhocProperties.exists()) {
				Properties prop = new Properties();
				FileReader reader = new FileReader(adhocProperties);
				try {
					prop.load(reader);
				} finally {
					reader.close();
				}
				adhocProperties.delete();
				return "@serialnumber='" + prop.getProperty("serial") + "'";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return deviceQuery;
	}

}
