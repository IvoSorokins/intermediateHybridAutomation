package screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import org.testng.SkipException;
import utils.interactions;
import utils.shadowDomHelper;

import java.util.List;

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

    public WebElement removeButton;


    public void getRemoveButton(int index) {
        List<WebElement> elements = driver.findElements(By.xpath("//ion-item-option[@color='danger']"));

        // Check if the index is valid
        if (index >= 0 && index < elements.size()) {
            removeButton = elements.get(index);

        } else {
            throw new IllegalArgumentException("Invalid index: " + index);
        }
    }

    // Methods to interact with the elements
    public void isScheduleDisplayed()throws InterruptedException{
        if(platform.equals("iOS")){
            Thread.sleep(5000); // Interestignly iOs
        }

        Interactions.assertElementVisibility(scheduleTitle,"Schedule title",true);
    }

    public WebElement getDataProviderEvent(String eventName){
        WebElement eventNameElement = Interactions.findElementByTagNameAndText("h3", eventName);
        return eventNameElement;
    }

    public void swipeDownUntilElementIsVisible(String eventName){
        if(platform.equals("iOS")){
            throw new SkipException("Skipping test case as longPress and press swipes do not function properly on iOS");
        }
        else{
            Interactions.swipeUntilElementVisible(getDataProviderEvent(eventName), 4);// Swipe count matches how many swipes should be performed
        }
        Interactions.swipeUntilElementVisible(getDataProviderEvent(eventName), 4);//
    }

    public void clickEventIfDisplayed(String eventName){
            Interactions.clickElementIfDisplayed(getDataProviderEvent(eventName), "Speaker name");
    }

    public void checkIfEventIsDisplayed(String eventName)throws InterruptedException{
        Thread.sleep(1000);
        Interactions.assertElementVisibility(getDataProviderEvent(eventName), "Event widget", true);
    }

    public void clickFavouriteTabIfDisplayed() throws InterruptedException {
        Thread.sleep(500); // Sadly we need to wait for the animation to finish else it is not going to work
        Interactions.clickElementIfDisplayed(favouriteTab, "Favourite tab");
    }

    public void clickRemoveIfDisplayed(){
        Interactions.clickElementIfDisplayed(removeButton, "Remove button");
    }

    public void swipeEventLeftHorizontally(String eventName, String eventDescription){
        String elementLocator;
        if(platform.equals("iOS")){
            elementLocator = "//XCUIElementTypeLink[@name=\""+ eventName +" " +eventDescription+ " chevron forward\"]";
        }
        else{
            elementLocator = "//android.view.View[@content-desc=\""+eventName+" " +eventDescription+ "\"]";
        }
        Interactions.swipeElementHorizontally(elementLocator,"left");
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
        Interactions.assertElementVisibility(removeButton,"Remove button Pop Up",false);
    }

    public void noEventsDisplayed(String eventName){
        Interactions.assertElementVisibility(getDataProviderEvent(eventName), "Event widget", false);
        Interactions.assertElementVisibility(noEventsText, "'No Sessions Found' text", true);
    }
}
