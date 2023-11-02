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

    // Create a Properties object to hold the loaded properties
    private static final Properties props = new Properties();

    // Set the path to the config.properties file
    private static final String propsPath = "src/main/resources/config.properties";

    // Create a DesiredCapabilities object to hold the desired capabilities for Appium
    private static final DesiredCapabilities desiredCapabilities = new DesiredCapabilities();

    /**
     * Loads test properties from a config.properties file located at resources
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
     * Sets desired capabilities for Appium based on properties loaded from config.properties file.
     *
     * @return DesiredCapabilities object containing desired capabilities for Appium.
     */
    public static DesiredCapabilities setDesiredCapabilities() {
        // Load the properties from the config.properties file
        loadProperties();

        // Set the desired capabilities based on the loaded properties
        desiredCapabilities.setCapability("platformName", props.getProperty("platformName"));
        desiredCapabilities.setCapability("appium:automationName", props.getProperty("automationName"));
        desiredCapabilities.setCapability("appium:appPackage", props.getProperty("appPackage"));
        desiredCapabilities.setCapability("appium:appActivity", props.getProperty("appActivity"));
        desiredCapabilities.setCapability("appium:noReset", props.getProperty("noReset"));

        // Return the DesiredCapabilities object
        return desiredCapabilities;
    }


}
