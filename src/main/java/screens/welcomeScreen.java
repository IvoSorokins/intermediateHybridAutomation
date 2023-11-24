package screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import org.testng.Assert;
import utils.interactions;

import static utils.testProperties.platform;


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
        Interactions.assertElementVisibility(skipButton, "Skip button",true);
        Interactions.assertElementVisibility(welcomeText, "Welcome to", true);
    }

    public void skipTutorial(){
        Interactions.clickElementIfDisplayed(skipButton, "Skip button");
    }

    public void tutorial2IsDisplayed(){
        Interactions.assertElementVisibility(skipButton,"Skip button",true);
        Interactions.assertElementVisibility(whatIsIonicText,"What is Ionic?",true);
    }

    public void tutorial3IsDisplayed(){
        Interactions.assertElementVisibility(skipButton,"Skip button",true);
        Interactions.assertElementVisibility(whatIsIonicAppFlowText,"What is Ionic Appflow?",true);
    }

    public void tutorial4IsDisplayed(){
        Interactions.assertElementVisibility(readyToPlayText,  "Ready to Play?", true);
        Interactions.assertElementVisibility(continueButton,  "Continue", true);
    }

    public void swipeLeftOnce(){
        Interactions.swipe("Left",1);
    }

    public void isSkipButtonNotVisible(){
        Interactions.assertElementVisibility(skipButton, "Skip button", false);
    }

    public void clickContinueButton(){
        Interactions.clickElementIfDisplayed(continueButton,"Continue button");
    }

    public void checkIfSwiped(){
        boolean isWelcomeTextVisible = Interactions.isElementVisible(welcomeText);
        if(isWelcomeTextVisible && platform.equals("iOS")) {
            System.out.println("Screen did not swipe, expected for iOS!");
        }else if (isWelcomeTextVisible && platform == "Android") {
            Assert.fail("Screen did not swipe ,not expected on Android!");
        }else{
            Interactions.assertElementVisibility(whatIsIonicText,"What is Ionic?",true);
        }
        }
    }




