package screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import org.testng.Assert;
import utils.interactions;

import static java.lang.Thread.sleep;


public class welcomeScreen {
    private final AppiumDriver driver;

    public interactions Interactions;
    public welcomeScreen(AppiumDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(this.driver), this);
        // Using Selenium's PageFactory to initialize the elements of the welcomeScreen class.
        // It's using AppiumFieldDecorator to support Appium's additional locator strategies.

        Interactions = new interactions(driver);
    }

    // Locators for the elements
    @FindBy(xpath = "//ion-button[@id=\"skip_tutorial_btn\"]")
    private  WebElement skipButton;

    @FindBy(xpath = "//h2[text()=' Welcome to ']")
    private WebElement welcomeText;

    @FindBy(xpath = "//h2[text()='What is Ionic?']")
    private WebElement whatIsIonicText;

    @FindBy(xpath = "//h2[text()='What is Ionic Appflow?']")
    private WebElement whatIsIonicAppFlowText;

    @FindBy(xpath = "//h2[text()='Ready to Play?']")
    private WebElement readyToPlayText;

    @FindBy (xpath = "//ion-button[text()=\" Continue \"]")
    private WebElement continueButton;

    // Methods to interact with the elements

    public void isTutorial1IsDisplayed(){
        Interactions.assertElementVisibility(skipButton, 5,"Skip button",true);
        Interactions.assertElementVisibility(welcomeText,5, "Welcome to", true);
    }

    public void skipTutorial(){
        Interactions.clickElementIfDisplayed(skipButton,5, "Skip button");
    }

    public void tutorial2IsDisplayed(){
        Interactions.assertElementVisibility(skipButton,5,"Skip button",true);
        Interactions.assertElementVisibility(whatIsIonicText,5,"What is Ionic?",true);
    }

    public void tutorial3IsDisplayed()throws InterruptedException{
        Interactions.assertElementVisibility(skipButton,5,"Skip button",true);
        Interactions.assertElementVisibility(whatIsIonicAppFlowText,5,"What is Ionic Appflow?",true);
    }

    public void tutorial4IsDisplayed()throws InterruptedException {
        Interactions.assertElementVisibility(readyToPlayText, 5, "Ready to Play?", true);
        Interactions.assertElementVisibility(continueButton, 5, "Continue", true);
    }

    public void swipeThroughTutorial()throws InterruptedException{
        Interactions.swipe("Left",2);
    }

    public void isSkipButtonNotVisible()throws InterruptedException{
        boolean isSkipButtonVisible = Interactions.isElementVisibleInView(skipButton);
        Assert.assertFalse(isSkipButtonVisible, "Skip button is visible.");// Should not be visible
        sleep(1000);
    }
    public void clickContinueButton()throws InterruptedException{
        continueButton.click();
        sleep(1000);
    }

    public void checkIfTutorialSwiped(){

    }

}


