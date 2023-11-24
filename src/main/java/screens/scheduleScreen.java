package screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import utils.interactions;


import java.time.Duration;

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

    public void isEventDisplayed(String name)throws InterruptedException{
        boolean isEvent = Interactions.isElementVisibleInView(driver.findElement(By.xpath(String.format("//h3[text()=\"%s\"]", name))));
        Assert.assertTrue(isEvent, "Event widget is not visible.");
        sleep(1000);
    }

    public void clickEvent(String name)throws InterruptedException{
        WebElement event = driver.findElement(By.xpath(String.format("//h3[text()='%s']", name)));
        Interactions.isElementVisibleInView(event);
        event.click();
        sleep(1000);
    }

    public void isFavouriteTabDisplayed()throws InterruptedException{
        boolean isFavouriteTab = Interactions.isElementVisibleInView(favouriteTab);
        Assert.assertTrue(isFavouriteTab, "Favourite tab is not visible.");
        sleep(1000);
    }

    public void clickFavouriteTab()throws InterruptedException{
        favouriteTab.click();
        sleep(1000);
    }


    public void clickRemoveButton()throws InterruptedException{
        boolean isRemoveButton = Interactions.isElementVisibleInView(removeButton);
        Assert.assertTrue(isRemoveButton, "Remove button is not visible.");
        sleep(1000);

        // Click the "Remove" button
        removeButton.click();

        sleep(1000);
    }

    public void swipeEvent(String eventName,String platform)throws InterruptedException{
        Interactions.swipeElement(eventName,"left" ,platform);
        sleep(3000);
    }

    public void isRemovePopUpDisplayed()throws InterruptedException{
        boolean isRemovePopUp= Interactions.isElementVisibleInView(removePopUp);
        Assert.assertTrue(isRemovePopUp, "Remove Pop Up is not visible.");
        sleep(1000);
    }

    public void isRemoveButtonOnPopUpDisplayed()throws InterruptedException{
        boolean isRemoveButtonPopUp= Interactions.isElementVisibleInView(removeButtonPopUp);
        Assert.assertTrue(isRemoveButtonPopUp, "Remove button Pop Up is not visible.");
        sleep(1000);
    }

    public void clickRemoveButtonOnPopUp()throws InterruptedException{
        removeButtonPopUp.click();
        sleep(1000);
    }

    public void isCancelButtonOnPopUpDisplayed()throws InterruptedException{
        boolean isCancelButtonPopUp= Interactions.isElementVisibleInView(cancelButtonPopUp);
        Assert.assertTrue(isCancelButtonPopUp, "Cancel button Pop Up is not visible.");
        sleep(1000);
    }

    public void clickCancelButtonOnPopUp()throws InterruptedException{
        cancelButtonPopUp.click();
        sleep(1000);
    }

    public void removeButtonIsNotDisplayed()throws InterruptedException{
        boolean isRemoveButton = Interactions.isElementVisibleInView(removeButton);
        Assert.assertFalse(isRemoveButton, "Remove button is visible.");
        sleep(1000);
    }
    public void noEventsDisplayed(String name)throws InterruptedException{
        boolean isEvent = Interactions.isElementVisibleInView(driver.findElement(By.xpath(String.format("//h3[text()=\"%s\"]", name))));
        boolean isNoEventsText = Interactions.isElementVisibleInView(noEventsText);
        Assert.assertFalse(isEvent, "Event widget is visible.");
        Assert.assertTrue(isNoEventsText,"No events text is not visible");


        sleep(1000);
    }

}
