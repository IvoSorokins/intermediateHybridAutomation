package components;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.interactions;

/**
 * This class represents the bottom navigation bar component
 */
public class bottomNavigationBar {

    private final AppiumDriver driver;
    public interactions Interactions;

    /**
     * Constructor for the BottomNavigationBar class.
     * @param driver The AppiumDriver instance used to interact with the mobile application.
     */
    public bottomNavigationBar(AppiumDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(this.driver), this);
        Interactions = new interactions(driver);
    }

    // Locators for the elements in the bottom navigation bar
    @FindBy(xpath = "//ion-tab-button[@id=\"tab-button-schedule\"]")
    private WebElement scheduleButton;

    @FindBy(xpath = "//ion-tab-button[@id='tab-button-speakers']")
    private WebElement speakersButton;

    @FindBy(xpath = "//ion-tab-button[@id=\"tab-button-map\"]")
    private WebElement mapButton;

    @FindBy(xpath = "//ion-tab-button[@id=\"tab-button-about\"]")
    private WebElement aboutButton;

    // Method to interact with the elements in the bottom navigation bar

    /**
     * This method clicks the Schedule button if it is displayed.
     */
    public void clickScheduleButtonIfDisplayed(){
        Interactions.clickElementIfDisplayed(scheduleButton, "Schedule button");
    }

    /**
     * This method clicks the Speakers button if it is displayed.
     */
    public void clickSpeakersButtonIfDisplayed(){
        Interactions.clickElementIfDisplayed(speakersButton, "Speakers button");
    }

    /**
     * This method clicks the Map button if it is displayed.
     */
    public void clickMapButtonIfDisplayed() {
        Interactions.clickElementIfDisplayed(mapButton, "Map button");
    }

    /**
     * This method clicks the About button if it is displayed.
     */
    public void clickAboutButtonIfDisplayed() {
        Interactions.clickElementIfDisplayed(aboutButton, "About button");
    }
}
