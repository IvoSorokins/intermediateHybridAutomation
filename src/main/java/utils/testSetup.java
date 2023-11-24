package utils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.net.MalformedURLException;
import java.net.URL;



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

        // Set the implicit wait timeout to 30 seconds
//        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        return driver;

    }

}
