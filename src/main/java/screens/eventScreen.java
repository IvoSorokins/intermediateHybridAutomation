package screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import utils.interactions;

import static java.lang.Thread.sleep;


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


    public void isEventNameDisplayed(String name)throws InterruptedException{
        boolean isEvent = Interactions.isElementVisibleInView(driver.findElement(By.xpath(String.format("//h1[text()='%s']", name))));
        Assert.assertTrue(isEvent, name+ " is not visible.");
        sleep(1000);
    }

    public void isStarButtonDisplayed()throws InterruptedException{
        boolean isEvent = Interactions.isElementVisibleInView(starButton);
        Assert.assertTrue(isEvent,  " is not visible.");
        sleep(1000);
    }

    public void clickStarButton(int times)throws InterruptedException{
        for (int i = 0; i < times; i++) {
            starButton.click();
            sleep(1000);
        }
    }

    public void verifyStarButtonColorWhite()throws InterruptedException{
        String initialColor = starButton.getCssValue("color");
        Assert.assertNotEquals(initialColor, "rgb(255, 255, 255)"); // Assuming that white is rgb(0, 0, 0)(255, 255, 255)
        sleep(1000);
    }

    public void verifyStarButtonColorBlack()throws InterruptedException{

        // Check the initial color of the star button
        String initialColor = starButton.getCssValue("color");
        Assert.assertNotEquals(initialColor, "rgb(0, 0, 0)"); // Assuming that black is rgb(0, 0, 0)
        sleep(1000);
    }
    public void verifyStarButtonLocation()throws InterruptedException{
        Dimension size = driver.manage().window().getSize(); // Get Screen size of the device

        int quarterX = size.width / 4;
        int quarterY = size.height / 4;

       // Check the position of the star button
        Point position = starButton.getLocation();

        // Verify that the star button is in the top right quarter of the screen
        // The X coordinate should be greater than three times the quarter width (meaning it's in the rightmost quarter)
        // The Y coordinate should be less than the quarter height (meaning it's in the topmost quarter)
        Assert.assertTrue(position.getX() > 3 * quarterX && position.getY() < quarterY);
        sleep(1000);
    }

    public void navigateBackToScheduleScreen()throws InterruptedException{
        Interactions.navigateBack(); // Navigate back
        sleep(1000);
    }


}
