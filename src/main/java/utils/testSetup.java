package utils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.SupportsContextSwitching;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;


public class testSetup {
    private static AppiumDriver driver;

    // iOs or else Android caps will be used
    public static AppiumDriver startServer(String platform) {

        DesiredCapabilities capabilities = testProperties.setDesiredCapabilities(platform);
        String serverUrlString = testProperties.getProperty("appiumURL");
        URL serverUrl;

        try {
            serverUrl = new URL(serverUrlString);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            throw new RuntimeException("The Appium server URL is malformed. Please check your configuration.");
        }

        try {
            if (platform.equalsIgnoreCase("iOS")) {
                driver = new IOSDriver(serverUrl, capabilities);
            } else {
                driver = new AndroidDriver(serverUrl, capabilities);

            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to start the Appium server. Please check your configuration and the Appium server logs.");
        }




        // Print the current context
        System.out.println("Current Context: " + ((SupportsContextSwitching) driver).getContext()); // get current context

        // Set the implicit wait timeout to 30 seconds
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        return driver;


    }

}
