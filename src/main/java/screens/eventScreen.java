package screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import utils.interactions;


public class eventScreen {

    private final AppiumDriver driver;
    public interactions Interactions;

    public eventScreen(AppiumDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(this.driver), this);
        Interactions = new interactions(driver);
    }

    @FindBy(css = "ion-button.toggle_favorite_btn")
    private WebElement starButton;


    public void checkIfEventNameIsDisplayed(String eventName){
        WebElement eventWidget = Interactions.findElementByTagNameAndText("h1",eventName);
        Interactions.assertElementVisibility(eventWidget, "Event name", true);
    }

    public void clickStarButtonIfDisplayed(int times){
        for (int i = 0; i < times; i++) {
            Interactions.clickElementIfDisplayed(starButton, "Star button");
        }
    }

    public void verifyStarButtonColorWhite(){
        String initialColor = starButton.getCssValue("color");
        Assert.assertEquals(initialColor, "rgb(255, 255, 255)"); // Assuming that white is rgb(0, 0, 0)(255, 255, 255)
    }

    public void verifyStarButtonColorBlack(){
        // Check the initial color of the star button
        String initialColor = starButton.getCssValue("color");
        System.out.println("Verify star button color is black: " + initialColor);
        Assert.assertEquals(initialColor, "rgb(0, 0, 0)"); // Assuming that black is rgb(0, 0, 0)
    }
    public void verifyStarButtonLocation(){
        Dimension size = driver.manage().window().getSize(); // Get Screen size of the device

        int quarterX = size.width / 4;
        int quarterY = size.height / 4;

       // Check the position of the star button
        Point position = starButton.getLocation();

        // Verify that the star button is in the top right quarter of the screen
        // The X coordinate should be greater than three times the quarter width (meaning it's in the rightmost quarter)
        // The Y coordinate should be less than the quarter height (meaning it's in the topmost quarter)
        Assert.assertTrue(position.getX() > 3 * quarterX && position.getY() < quarterY);
    }

    public void navigateBackToScheduleScreen(){
        Interactions.navigateBack(); // Navigate back
    }

}
