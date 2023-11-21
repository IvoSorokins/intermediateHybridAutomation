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

    public void checkEachSpeakerDisplayed(int index,String userName,String profession)throws InterruptedException{
        WebElement speakerElement = driver.findElement(By.xpath("//ion-col["+ index +"]//h2[text()='" + userName + "']"));
        WebElement speakerAboutElement = driver.findElement(By.xpath("//h3[text()='About " + userName + "']"));
        WebElement speakerProfessionElement = driver.findElement(By.xpath("//ion-col["+ index +"]//p[text()='"+ profession +"']"));

        boolean isSpeakerDisplayed = jsScripts.isElementVisibleInView(speakerElement, driver);
        boolean isSpeakerAboutElementDisplayed = jsScripts.isElementVisibleInView(speakerAboutElement, driver);
        boolean isSpeakerProfessionElementDisplayed = jsScripts.isElementVisibleInView(speakerProfessionElement, driver);

        if (isSpeakerDisplayed == false){
            interactions.swipeIntoView(speakerAboutElement,driver);
            sleep(5000);
            isSpeakerDisplayed = jsScripts.isElementVisibleInView(speakerElement, driver);
            isSpeakerAboutElementDisplayed = jsScripts.isElementVisibleInView(speakerAboutElement, driver);
            isSpeakerProfessionElementDisplayed = jsScripts.isElementVisibleInView(speakerProfessionElement, driver);

        }

        Assert.assertTrue(isSpeakerDisplayed, "Speakers title is not visible.");
        Assert.assertTrue(isSpeakerAboutElementDisplayed, "Speakers About button is not visible.");
        Assert.assertTrue(isSpeakerProfessionElementDisplayed, "Speakers profession is not visible.");
        sleep(1000);
    }

    public void clickSpeakerProfile(int index,String userName)throws InterruptedException{
        WebElement speakerElement = driver.findElement(By.xpath("//ion-col["+ index +"]//h2[text()='" + userName + "']"));

        boolean isSpeakerDisplayed = jsScripts.isElementVisibleInView(speakerElement, driver);

        if (isSpeakerDisplayed == false){
            interactions.swipeIntoView(speakerElement,driver);
            sleep(5000);
        }
        speakerElement.click();
        sleep(1000);
    }
}
