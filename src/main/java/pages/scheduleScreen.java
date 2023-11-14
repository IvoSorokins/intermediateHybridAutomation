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

}
