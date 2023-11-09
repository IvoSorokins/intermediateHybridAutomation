package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class welcomeScreen {
    private final AppiumDriver driver;

    public welcomeScreen(AppiumDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(this.driver), this);
    }

    // Locators to used find elements on screen
    @FindBy(xpath = "//ion-button[@id=\"skip_tutorial_btn\"]")
    private  WebElement skipButton;

    @FindBy(xpath = "//h2[text()=' Welcome to ']")
    private WebElement welcomeText;

    // Methods to interact with the elements
    public void tutorialIsDisplayed() {
    skipButton.isDisplayed();
    welcomeText.isDisplayed();
    }
    public void tutorialSkipped(){
        skipButton.click();
    }

}


