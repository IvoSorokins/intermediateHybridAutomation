package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.TouchAction;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.remote.SupportsContextSwitching;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class scheduleScreen {
    private final AppiumDriver driver;

    public scheduleScreen(AppiumDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(this.driver), this);
    }

    // Locators to used find elements on screen
    @FindBy(xpath = "//ion-header/ion-toolbar/ion-title[text()='Schedule'][1]")
    private WebElement scheduleTitle;


    // Methods to interact with the elements
    public void isDisplayed(){
        scheduleTitle.isDisplayed();
    }
    public void swipeRight(){
        ((SupportsContextSwitching) driver).context("NATIVE_APP");
        // Get the size of the screen
        Dimension size = driver.manage().window().getSize();

        // Calculate start and end points for the swipe
        int startX = (int) (size.width * 0.8);
        int endX = (int) (size.width * 0.2);
        int startY = size.height / 2;

        // Perform the swipe
        new TouchAction((PerformsTouchActions) driver)
                .press(PointOption.point(startX, startY))
                .moveTo(PointOption.point(endX, startY))
                .release()
                .perform();
        List<String> contexts = (List<String>) ((SupportsContextSwitching) driver).getContextHandles();
        // Switch back to WebView context
        ((SupportsContextSwitching) driver).context(String.valueOf(contexts.size()-1));
        // Print the current context
        System.out.println("Current Context: " + ((SupportsContextSwitching) driver).getContext()); // get current context

    }
}
