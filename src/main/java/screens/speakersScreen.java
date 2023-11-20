package screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import utils.interactions;

import static java.lang.Thread.sleep;

public class speakersScreen {
    private final AppiumDriver driver;
    interactions jsScripts = new interactions();

    public speakersScreen(AppiumDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(this.driver), this);
    }

    @FindBy(xpath = "//ion-title[text()=\"Speakers\"][1]")
    private WebElement speakersTitle;

    public void isSpeakersTitleDisplayed()throws InterruptedException{
        boolean isSpeakersTitle = jsScripts.isElementVisibleInView(speakersTitle, driver);
        Assert.assertTrue(isSpeakersTitle, "Speakers title is not visible.");
        sleep(1000);
    }

    public void speakersAboutSectionDisplayed()throws InterruptedException{

    }
}
