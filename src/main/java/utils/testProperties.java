package utils;

import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


/**
 This class provides methods for loading test properties from a config.properties file and
 setting desired capabilities for Appium.
 */
public class testProperties {

    /** Before running any tests, let's set testProperties */

    //  Wait until visibility of the element
    public static final long waitTimeInSeconds = 5;

    // Set the platform on which the tests will run
    public static final String platform = "iO";

    // Set the path to the config.properties file
    private static final String propsPath = "src/main/resources/config.properties";



    // Create a Properties object to hold the loaded properties
    private static final Properties props = new Properties();


    // Create a DesiredCapabilities object to hold the desired capabilities for Appium
    private static final DesiredCapabilities desiredCapabilities = new DesiredCapabilities();

    /**
     * This method loads properties from a file located at the path specified by the 'propsPath' variable.
     * The properties are loaded into the 'props' Properties object.
     * If an IOException occurs during the operation, the stack trace is printed.
     */
    private static void loadProperties() {
        // Use try-with-resources to automatically close the input stream
        try ( InputStream input = new FileInputStream(propsPath)){
            // Load the properties from the input stream
            props.load(input);
        } catch (IOException ex) {
            // Print the stack trace if an exception occurs while loading the properties
            ex.printStackTrace();
        }
    }

    /**
     * This method sets the desired capabilities for Appium based on the platform and properties loaded from the config file.
     * @param platform The platform for which to set the capabilities ("iOS" or "Android").
     * @return The DesiredCapabilities object with the set capabilities.
     */
    public static DesiredCapabilities setDesiredCapabilities(String platform) {
        // Load the properties from the config.properties file
        loadProperties();

        // Common Caps
        desiredCapabilities.setCapability("appium:autoWebview", props.getProperty("autoWebview"));
        desiredCapabilities.setCapability("appium:fullContextList", props.getProperty("fullContextList"));
        desiredCapabilities.setCapability("appium:simplicityWaitForElement", props.getProperty("simplicityWaitForElement"));

        // Set the desired capabilities based on the loaded properties
        if (platform.equals("iOS")) {
            desiredCapabilities.setCapability("appium:bundleId", props.getProperty("bundleId"));
            desiredCapabilities.setCapability("appium:automationName", props.getProperty("iOSAutomationName"));
            desiredCapabilities.setCapability("appium:udid", props.getProperty("iOSUdId"));
            desiredCapabilities.setCapability("appium:platformName", props.getProperty("iOSPlatformName"));
            desiredCapabilities.setCapability("appium:deviceName", props.getProperty("iOSDeviceName"));
            desiredCapabilities.setCapability("appium:nativeWebTap", props.getProperty("nativeWebTap"));
            desiredCapabilities.setCapability("appium:includeSafariInWebviews", props.getProperty("includeSafariInWebviews"));
            desiredCapabilities.setCapability("appium:waitForQuiescence", props.getProperty("waitForQuiescence"));
            desiredCapabilities.setCapability("appium:platformVersion", props.getProperty("iOSPlatformVersion"));
        } else {
            desiredCapabilities.setCapability("appium:appActivity", props.getProperty("appActivity"));
            desiredCapabilities.setCapability("appium:appPackage", props.getProperty("appPackage"));
            desiredCapabilities.setCapability("appium:automationName", props.getProperty("androidAutomationName"));
            desiredCapabilities.setCapability("appium:platformName", props.getProperty("androidPlatformName"));
            desiredCapabilities.setCapability("appium:deviceName", props.getProperty("androidDeviceName"));
            desiredCapabilities.setCapability("appium:udId", props.getProperty("androidUdId"));
            desiredCapabilities.setCapability("appium:broswerName", props.getProperty("broswerName"));
            desiredCapabilities.setCapability("appium:nativeWebScreenshot", props.getProperty("nativeWebScreenshot"));
            desiredCapabilities.setCapability("appium:newCommandTimeout", props.getProperty("newCommandTimeout"));
            desiredCapabilities.setCapability("appium:platformVersion", props.getProperty("AndroidPlatformVersion"));
            desiredCapabilities.setCapability("appium:ignoreUnimportantViews", props.getProperty("ignoreUnimportantViews"));
        }
        // Return the DesiredCapabilities object
        return desiredCapabilities;
    }

    /**
     * This method returns the value of the specified property.
     * @param name The name of the property.
     * @return The value of the property.
     * @throws IllegalArgumentException If the name parameter is null.
     */
    public static String getProperty(String name){
        // Throw an exception if the name parameter is null
        if (name == null) {
            throw new IllegalArgumentException("Name parameter cannot be null");
        }
        // Return the value of the specified property
        return props.getProperty(name);
    }

}
