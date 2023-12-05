package screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.interactions;
import utils.shadowDomHelper;

import static utils.testProperties.platform;


public class scheduleScreen {

    private final AppiumDriver driver;
    public interactions Interactions;
    public shadowDomHelper ShadowDomHelper;


    public scheduleScreen(AppiumDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(this.driver), this);
        ShadowDomHelper = new shadowDomHelper(driver);
        Interactions = new interactions(driver);
    }

    // Locators to used find elements on screen
    @FindBy(xpath = "//ion-title[text()=\"Schedule\"]")
    private WebElement scheduleTitle;

    @FindBy(xpath ="//ion-segment-button[text()=\" Favorites \"]")
    private WebElement favouriteTab;

    @FindBy(xpath = "//h2[text()=\"Remove Favorite\"]")
    private WebElement removePopUp;

    @FindBy (xpath = "//span[text()=\"Remove\"]")
    private WebElement removeButtonPopUp;

    @FindBy(xpath = "//span[text()=\"Cancel\"]")
    private WebElement cancelButtonPopUp;

    @FindBy(xpath = "//ion-list-header[text()=\" No Sessions Found \"]")
    private WebElement noEventsText;


    public WebElement getRemoveButton(){
        WebElement RemoveButton;
        if(platform.equals("iOS")){
            RemoveButton = driver.findElement(By.xpath("//XCUIElementTypeButton[@name=\"Remove\"]"));
        }
        else{
            RemoveButton = driver.findElement(By.xpath("//android.widget.Button[@text=\"REMOVE\"]"));
        }
        return RemoveButton;
    }
    // Methods to interact with the elements
    public void isScheduleDisplayed(){
        Interactions.assertElementVisibility(scheduleTitle,"Schedule title",true);
    }

    public WebElement getDataProviderEvent(String eventName){
        WebElement eventNameElement = Interactions.findElementByTagNameAndText("h3", eventName);
        return eventNameElement;
    }

    public void swipeDownUntilElementIsVisible(String eventName){
        Interactions.swipeUntilElementVisible(getDataProviderEvent(eventName), 4);
    }

    public void clickEventIfDisplayed(String eventName){
            Interactions.clickElementIfDisplayed(getDataProviderEvent(eventName), "Speaker name");
    }

    public void checkIfEventIsDisplayed(String eventName){
        Interactions.assertElementVisibility(getDataProviderEvent(eventName), "Event widget", true);
    }

    public void clickFavouriteTabIfDisplayed(){
        Interactions.clickElementIfDisplayed(favouriteTab, "Favourite tab");
    }

    public void clickRemoveIfDisplayed(){
        WebElement element = getRemoveButton();
        Interactions.clickElementIfDisplayed(element, "Remove button");
    }

    public void swipeEventHorizontally(String eventName, String eventDescription){
        String elementLocator;
        if(platform.equals("iOS")){
            elementLocator = "//XCUIElementTypeLink[@name=\""+ eventName +" " +eventDescription+ " chevron forward\"]";
        }
        else{
            elementLocator = "//android.view.View[@content-desc=\""+eventName+" " +eventDescription+ "\"]";
        }
        Interactions.swipeElementHorizontally(elementLocator);
    }

    public void isRemovePopUpDisplayed(){
        Interactions.assertElementVisibility(removePopUp,"Remove Pop Up",true);
    }

    public void clickRemoveButtonOnPopUp(){
        Interactions.clickElementIfDisplayed(removeButtonPopUp, "Remove button Pop Up");
    }

    public void isRemoveButtonOnPopUpDisplayed(){
        Interactions.assertElementVisibility(removeButtonPopUp,"Remove button Pop Up",true);
    }
    public void isCancelButtonOnPopUpDisplayed(){
        Interactions.assertElementVisibility(cancelButtonPopUp,"Cancel button Pop Up",true);
    }
    public void clickCancelButtonOnPopUp(){
        Interactions.clickElementIfDisplayed(cancelButtonPopUp, "Cancel button Pop Up");
    }

    public void removeButtonIsNotDisplayed(){
        Interactions.assertElementVisibility(getRemoveButton(),"Remove button Pop Up",false);

    }

    public void noEventsDisplayed(String eventName){
        Interactions.assertElementVisibility(getDataProviderEvent(eventName), "Event widget", false);
        Interactions.assertElementVisibility(noEventsText, "'No Sessions Found' text", true);
    }

}
