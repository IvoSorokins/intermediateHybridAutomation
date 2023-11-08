package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.net.MalformedURLException;
import java.net.URL;


public class testSetup {
    private static WebDriver driver;

    // iOs or else Android caps will be used
    public static void startServer(String platform) {

        DesiredCapabilities capabilities = testProperties.setDesiredCapabilities(platform);
        String serverUrlString = testProperties.getProperty("appiumURL");
        System.out.println(serverUrlString);
        URL serverUrl;

        try {
            serverUrl = new URL(serverUrlString);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            throw new RuntimeException("The Appium server URL is malformed. Please check your configuration.");
        }

        try {
            driver = new RemoteWebDriver(serverUrl, capabilities);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to start the Appium server. Please check your configuration and the Appium server logs.");
        }
    }

    // This method is used in tearDownClass() to close the AndroidDriver instance

}
