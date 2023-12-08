package screens;

// Appium , TestNG imports
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

import org.openqa.selenium.support.PageFactory;

 import static org.testng.AssertJUnit.assertEquals;


public class ionicAccountScreen {

    private final AppiumDriver driver;


    public ionicAccountScreen(AppiumDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(this.driver), this);
    }

    // Method to interact with screen
    public void switchToNewWindow(){
        for (String handle : driver.getWindowHandles()) {
            driver.switchTo().window(handle);
        }
    }

    public String getCurrentURL() {
        return driver.getCurrentUrl();
    }

    public void verifyUrl(String expectedUrl){
        // Switch to the new window
        switchToNewWindow();

        // Get the current URL
        String currentUrl = getCurrentURL();
        System.out.print("Current url: "+ currentUrl +"\n Expected url: "+ expectedUrl);

        // Assert that the current URL equals the expected URL
        assertEquals("URL is not expected", expectedUrl, currentUrl);
    }
}
