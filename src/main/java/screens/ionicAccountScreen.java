package screens;

// Appium , TestNG imports
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

// utils imports
import utils.interactions;

// Java imports
import java.time.Duration;
import static java.lang.Thread.sleep;


public class ionicAccountScreen {
    private final AppiumDriver driver;
    public interactions Interactions; // Declare field name


    public ionicAccountScreen(AppiumDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(this.driver), this);
        Interactions = new interactions(driver); // Class instance
    }

    public void verifyWebpageNotAvailableDisplayed()throws InterruptedException{
        sleep(10000);

        // Wait for the new page to load
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.urlContains("https://twitter.com/ionicframework"));

        // Get and print the current URL
        String currentUrl = driver.getCurrentUrl();
        System.out.println("Current URL: " + currentUrl);

        // Verify that the current URL is the Twitter page
        Assert.assertTrue(currentUrl.contains("https://twitter.com/ionicframework"), "The current URL does not contain 'twitter.com'");
        sleep(1000);
    }
}
