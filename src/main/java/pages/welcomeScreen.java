package pages;


import io.appium.java_client.AppiumBy;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;


import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class welcomeScreen {
    private final WebDriver driver;
// Print the current context


    public welcomeScreen(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(this.driver), this);

    }

    // Locators to used find elements on screen

//    @AndroidFindBy(xpath = "//ion-button[@id=\"skip_tutorial_btn\"]")
//    @iOSXCUITFindBy(xpath = "//ion-button[@id=\"skip_tutorial_btn\"]")
//    private WebElement skipButton;

    public void isDisplayed() throws Exception {
//        skipButton.click();
        WebElement skipButton = (WebElement) new WebDriverWait(driver, Duration.ofSeconds(30)).until(
            ExpectedConditions.elementToBeClickable(AppiumBy.xpath("//ion-button[@id=\"skip_tutorial_btn\"]")));
        }
//        skipButton.isDisplayed();
//        title.isDisplayed();ß

    }


