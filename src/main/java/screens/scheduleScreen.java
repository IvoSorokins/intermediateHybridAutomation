package screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.interactions;

import static java.lang.Thread.sleep;


public class scheduleScreen {
    private final AppiumDriver driver;
    public interactions Interactions;

    public scheduleScreen(AppiumDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(this.driver), this);

        Interactions = new interactions(driver);
    }

    // Locators to used find elements on screen
    @FindBy(xpath = "//ion-title[text()=\"Schedule\"]")
    private WebElement scheduleTitle;

    @FindBy(xpath ="//ion-segment-button[text()=\" Favorites \"]")
    private WebElement favouriteTab;

    @FindBy(css="ion-item-sliding.md.hydrated.item-sliding-active-slide.item-sliding-active-options-end")
    private WebElement removeButton;

    @FindBy(xpath = "//h2[text()=\"Remove Favorite\"]")
    private WebElement removePopUp;

    @FindBy (xpath = "//span[text()=\"Remove\"]")
    private WebElement removeButtonPopUp;

    @FindBy(xpath = "//span[text()=\"Cancel\"]")
    private WebElement cancelButtonPopUp;

    @FindBy(xpath = "//ion-list-header[text()=\" No Sessions Found \"]")
    private WebElement noEventsText;


    // Methods to interact with the elements
    public void isScheduleDisplayed(){
        Interactions.assertElementVisibility(scheduleTitle,"Schedule title",true);
    }

    public WebElement getDataProvider(String eventName){
        WebElement eventNameElement = Interactions.findElementByTagNameAndText("h3", eventName);
        return eventNameElement;
    }

    public void swipeDownUntilElementIsVisible(String eventName){
        Interactions.swipeUntilElementVisible(getDataProvider(eventName));
    }

    public void clickEventIfDisplayed(String eventName){
            Interactions.clickElementIfDisplayed(getDataProvider(eventName), "Speaker name");
    }

    public void clickFavouriteTabIfDisplayed(){
        Interactions.clickElementIfDisplayed(favouriteTab, "Favourite tab");
    }

    public void clickRemoveIfDisplayed(){
        Interactions.clickElementIfDisplayed(removeButton, "Remove button");
    }

    public void swipeEvent(String eventName,String platform){
        Interactions.swipeElement(eventName,"left" ,platform);
    }

    public void isRemovePopUpDisplayed(){
        Interactions.assertElementVisibility(removePopUp,"Remove Pop Up",true);
    }

    public void clickRemoveButtonOnPopUp(){
        Interactions.clickElementIfDisplayed(removeButtonPopUp, "Remove button Pop Up");
    }

    public void clickCancelButtonOnPopUp(){
        Interactions.clickElementIfDisplayed(cancelButtonPopUp, "Cancel button Pop Up");
    }

    public void removeButtonIsNotDisplayed(){
        Interactions.assertElementVisibility(removeButton,"Remove button",false);
    }

    public void noEventsDisplayed(String eventName){
        Interactions.assertElementVisibility(getDataProvider(eventName), "Event widget", false);
        Interactions.assertElementVisibility(noEventsText, "'No Sessions Found' text", true);
    }

}
