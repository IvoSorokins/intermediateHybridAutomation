package testCases;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.*;
import utils.testParameters;


import java.io.IOException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import static utils.testProperties.*;
import static utils.testParameters.*;



public class allTests {

    // Create an instance of AndroidDriver with the desired capabilities
    private AndroidDriver driver;

    // Page Objects


    @BeforeClass(description = "Starts the session", groups = "StartUp")
    public void setUpClass() throws IOException {
        // Close System.err to prevent any error messages from being printed to the console
        System.err.close();

        // Set System.err to the default output stream to allow any error messages to be printed to the console again
        System.setErr(System.out);

        // Set the desired capabilities for Appium
        URL remoteUrl = new URL("http://127.0.0.1:4723/");

        // Create a new AndroidDriver instance with the desired capabilities and remote URL
        //noinspection rawtypes
        driver = new AndroidDriver(remoteUrl,desiredCapabilities);

        // Set the implicit wait timeout to 15 seconds
        driver.manage().timeouts().implicitlyWait(simplicityWaitForElement, TimeUnit.SECONDS);

        // Initialize the page objects for the different screens of the app

    }

    @BeforeGroups(description = "Resets the app before each test group", alwaysRun = true)
    public void setUpMethodGroups(){
        // Reset the app to the initial state before each test
        driver.resetApp();
    }
    @BeforeMethod(description = "Resets the app before each test", alwaysRun = true)
    public void setUpMethod() {
        // Reset the app to the initial state before each test
        driver.resetApp();
    }
    @AfterClass(description="Runs after each test",
            groups=("CleanUp"),
            alwaysRun = true)
    public void cleanUp() {
        driver.quit();
    }

}
