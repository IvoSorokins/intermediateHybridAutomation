package components;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import utils.interactions;

import static java.lang.Thread.sleep;

public class bottomNavigationBar {

    private final AppiumDriver driver;
    interactions jsScripts = new interactions();

    public bottomNavigationBar(AppiumDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(this.driver), this);
    }

    @FindBy(xpath = "//ion-tab-button[@id=\"tab-button-schedule\"]")
    private WebElement scheduleButton;

    @FindBy(xpath = "//ion-tab-button[@id='tab-button-speakers']")
    private WebElement speakersButton;

    @FindBy(xpath = "//ion-tab-button[@id=\"tab-button-map\"]")
    private WebElement mapButton;

    @FindBy(xpath = "//ion-tab-button[@id=\"tab-button-about\"]")
    private WebElement aboutButton;

    public void isScheduleButtonDisplayed()throws InterruptedException{
        boolean isScheduleButton = jsScripts.isElementVisibleInView(scheduleButton, driver);
        Assert.assertTrue(isScheduleButton, "Schedule button is not visible.");
        sleep(1000);
    }
    public void clickScheduleButton()throws InterruptedException{
        scheduleButton.click();
        sleep(1000);
    }
    public void isSpeakersButtonDisplayed()throws InterruptedException{
        boolean isSpeakersButton = jsScripts.isElementVisibleInView(speakersButton, driver);
        Assert.assertTrue(isSpeakersButton, "Speakers button is not visible.");
        sleep(1000);
    }
    public void clickSpeakersButton()throws InterruptedException{
        speakersButton.click();
        sleep(1000);
    }
}
