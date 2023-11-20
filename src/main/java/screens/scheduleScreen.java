package screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import utils.interactions;


import static java.lang.Thread.sleep;
import static utils.interactions.swipeElement;


public class scheduleScreen {
    private final AppiumDriver driver;
    interactions jsScripts = new interactions();

    public scheduleScreen(AppiumDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(this.driver), this);
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
    public void isScheduleDisplayed()throws InterruptedException{
         boolean isSchedueleTitle = jsScripts.isElementVisibleInView(scheduleTitle,driver);
         Assert.assertTrue(isSchedueleTitle,"Scheduele title is not visible");
         sleep(1000);
    }

    public void isEventDisplayed(String name)throws InterruptedException{
        boolean isEvent = jsScripts.isElementVisibleInView(driver.findElement(By.xpath(String.format("//h3[text()=\"%s\"]", name))), driver);
        Assert.assertTrue(isEvent, "Event widget is not visible.");
        sleep(1000);
    }

    public void clickEvent(String name)throws InterruptedException{
        WebElement event = driver.findElement(By.xpath(String.format("//h3[text()='%s']", name)));
        jsScripts.isElementVisibleInView(event, driver);
        event.click();
        sleep(1000);
    }

    public void isFavouriteTabDisplayed()throws InterruptedException{
        boolean isFavouriteTab = jsScripts.isElementVisibleInView(favouriteTab,driver);
        Assert.assertTrue(isFavouriteTab, "Favourite tab is not visible.");
        sleep(1000);
    }

    public void clickFavouriteTab()throws InterruptedException{
        favouriteTab.click();
        sleep(1000);
    }


    public void clickRemoveButton()throws InterruptedException{
        // Find the "Remove" button, when it becomes visible
//        WebElement removeButton = driver.findElement(By.cssSelector("ion-item-sliding.md.hydrated.item-sliding-active-slide.item-sliding-active-options-end"));

        boolean isRemoveButton = jsScripts.isElementVisibleInView(removeButton,driver);
        Assert.assertTrue(isRemoveButton, "Remove button is not visible.");
        sleep(1000);

        // Click the "Remove" button
        removeButton.click();

        sleep(1000);
    }

    public void swipeEvent(String eventName,String platform)throws InterruptedException{
        swipeElement(eventName,"left" ,platform, driver);
        sleep(3000);
    }

    public void isRemovePopUpDisplayed()throws InterruptedException{
        boolean isRemovePopUp= jsScripts.isElementVisibleInView(removePopUp,driver);
        Assert.assertTrue(isRemovePopUp, "Remove Pop Up is not visible.");
        sleep(1000);
    }

    public void isRemoveButtonOnPopUpDisplayed()throws InterruptedException{
        boolean isRemoveButtonPopUp= jsScripts.isElementVisibleInView(removeButtonPopUp,driver);
        Assert.assertTrue(isRemoveButtonPopUp, "Remove button Pop Up is not visible.");
        sleep(1000);
    }

    public void clickRemoveButtonOnPopUp()throws InterruptedException{
        removeButtonPopUp.click();
        sleep(1000);
    }

    public void isCancelButtonOnPopUpDisplayed()throws InterruptedException{
        boolean isCancelButtonPopUp= jsScripts.isElementVisibleInView(cancelButtonPopUp,driver);
        Assert.assertTrue(isCancelButtonPopUp, "Cancel button Pop Up is not visible.");
        sleep(1000);
    }

    public void clickCancelButtonOnPopUp()throws InterruptedException{
        cancelButtonPopUp.click();
        sleep(1000);
    }

    public void removeButtonIsNotDisplayed()throws InterruptedException{
        boolean isRemoveButton = jsScripts.isElementVisibleInView(removeButton,driver);
        Assert.assertFalse(isRemoveButton, "Remove button is visible.");
        sleep(1000);
    }
    public void noEventsDisplayed(String name)throws InterruptedException{
        boolean isEvent = jsScripts.isElementVisibleInView(driver.findElement(By.xpath(String.format("//h3[text()=\"%s\"]", name))), driver);
        boolean isNoEventsText = jsScripts.isElementVisibleInView(noEventsText,driver);
        Assert.assertFalse(isEvent, "Event widget is visible.");
        Assert.assertTrue(isNoEventsText,"No events text is not visible");


        sleep(1000);
    }

}
