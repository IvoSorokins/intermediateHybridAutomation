package pages;


import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static java.lang.Thread.sleep;

public class welcomeScreen {
    private WebDriver driver;

    // Locators to used find elements on screen
    @FindBy(xpath = "//ion-button[@id=\"skip_tutorial_btn\"]")
    private WebElement skipButton;

    @FindBy(xpath = "//h2[text()=' Welcome to ']")
    private WebElement title;

    public welcomeScreen(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(this.driver), this);
    }

    public void isDisplayed() throws InterruptedException {
        sleep(5000);
        skipButton.isDisplayed();
        title.isDisplayed();
    }

}

