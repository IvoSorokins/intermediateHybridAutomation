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
    interactions jsScripts = new interactions();

    public welcomeScreen(AppiumDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(this.driver), this);
    }

    // Locators to used find elements on screen
    // MobileElement has been replaced by WebElement  https://github.com/appium/java-client/blob/master/docs/v7-to-v8-migration-guide.md#mobileelement
    @FindBy(xpath = "//ion-button[@id=\"skip_tutorial_btn\"]")
    private  WebElement skipButtonWeb;

    @FindBy(xpath = "//h2[text()=' Welcome to ']")
    private WebElement welcomeTextWeb;

    @FindBy(xpath = "//h2[text()='What is Ionic?']")
    private WebElement whatIsIonicTextWeb;

    @FindBy(xpath = "//h2[text()='What is Ionic Appflow?']")
    private WebElement whatIsIonicAppFlowTextWeb;

    @FindBy(xpath = "//h2[text()='Ready to Play?']")
    private WebElement readyToPlayTextWeb;

    @FindBy (xpath = "//ion-button[text()=\" Continue \"]")
    private WebElement continueButtonWeb;

    // Methods to interact with the elements

    public void tutorial1IsDisplayed()throws InterruptedException {
        boolean isSkipButtonVisible = jsScripts.isElementVisibleInView(skipButtonWeb, driver);
        boolean isWelcomeTextVisible = jsScripts.isElementVisibleInView(welcomeTextWeb, driver);

        Assert.assertTrue(isSkipButtonVisible, "Skip button is not visible.");
        Assert.assertTrue(isWelcomeTextVisible, "Welcome text is not visible.");
        sleep(1000);
    }

    public void tutorialSkip()throws InterruptedException{
        skipButtonWeb.click();
        sleep(1000);
    }

    public boolean isTutorial1Displayed(){ // Will return true or false
        return jsScripts.isElementVisibleInView(skipButtonWeb, driver)
                && jsScripts.isElementVisibleInView(welcomeTextWeb, driver);
    }

    public void tutorial2IsDisplayed()throws InterruptedException{
        boolean isSkipButtonVisible = jsScripts.isElementVisibleInView(skipButtonWeb, driver);
        boolean isWelcomeTextVisible = jsScripts.isElementVisibleInView(whatIsIonicTextWeb, driver);

        Assert.assertTrue(isSkipButtonVisible, "Skip button is not visible.");
        Assert.assertTrue(isWelcomeTextVisible, "Welcome text is not visible.");
        sleep(1000);
    }

    public void tutorial3IsDisplayed()throws InterruptedException{
        boolean isSkipButtonVisible = jsScripts.isElementVisibleInView(skipButtonWeb, driver);
        boolean isWelcomeTextVisible = jsScripts.isElementVisibleInView(whatIsIonicAppFlowTextWeb, driver);

        Assert.assertTrue(isSkipButtonVisible, "Skip button is not visible.");
        Assert.assertTrue(isWelcomeTextVisible, "Welcome text is not visible.");
        sleep(1000);
    }

    public void tutorial4IsDisplayed()throws InterruptedException{

        boolean isWelcomeTextVisible = jsScripts.isElementVisibleInView(readyToPlayTextWeb, driver);
        boolean isContinueButtonVisible = jsScripts.isElementVisibleInView(continueButtonWeb, driver);


        Assert.assertTrue(isWelcomeTextVisible, "Welcome text is not visible.");
        Assert.assertTrue(isContinueButtonVisible, "Continue button is not visible.");
        sleep(1000);
    }

    public void isSkipButtonVisible()throws InterruptedException{
        boolean isSkipButtonVisible = jsScripts.isElementVisibleInView(skipButtonWeb, driver);
        Assert.assertFalse(isSkipButtonVisible, "Skip button is visible.");// Should not be visible
        sleep(1000);
    }
    public void clickContinueButton()throws InterruptedException{
        continueButtonWeb.click();
        sleep(1000);
    }
    public boolean isTutorial1DisplayedAfterSwipeLeft() throws InterruptedException {
        interactions.swipe("Left",1, driver);
        if (isTutorial1Displayed()){
            return false;
        }else {
            return true;
        }
    }
    public void continueSwipingTutorial(boolean isTutorial1Displayed) throws InterruptedException {
        if (isTutorial1Displayed) {
            System.out.print("Tutorial screen swiped");
            tutorial2IsDisplayed();
            interactions.swipe("Left", 1, driver);
            tutorial3IsDisplayed();
            interactions.swipe("Left", 1, driver);
            tutorial4IsDisplayed();

            // Swipe only reacts to string "Left" so it could also be left empty to swipe right
            interactions.swipe("Right", 1, driver);
            tutorial3IsDisplayed();
            interactions.swipe("Right", 1, driver);
            tutorial2IsDisplayed();
            interactions.swipe("Right", 1, driver);
            isTutorial1Displayed();
        }
        else {System.out.println("Tutorial screen did not swipe (Most likely duo to a iOS simulator bug - expected behavior)");}
    }




}


