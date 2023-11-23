package screens;

import components.bottomNavigationBar;
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
    public interactions Interactions;
    public bottomNavigationBar BottomNavigationBar;


    public speakersScreen(AppiumDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(this.driver), this);
        Interactions = new interactions(driver);
        BottomNavigationBar = new bottomNavigationBar(driver);
    }

    @FindBy(xpath = "//ion-title[text()=\"Speakers\"][1]")
    private WebElement speakersTitle;


    public void isSpeakersTitleDisplayed()throws InterruptedException{
        boolean isSpeakersTitle = Interactions.isElementVisibleInView(speakersTitle);
        Assert.assertTrue(isSpeakersTitle, "Speakers title is not visible.");
        sleep(1000);
    }

    public void checkEachSpeakerDisplayed(int index,String userName,String profession)throws InterruptedException{
        WebElement speakerElement = driver.findElement(By.xpath("//ion-col["+ index +"]//h2[text()='" + userName + "']"));
        WebElement speakerAboutElement = driver.findElement(By.xpath("//h3[text()='About " + userName + "']"));
        WebElement speakerProfessionElement = driver.findElement(By.xpath("//ion-col["+ index +"]//p[text()='"+ profession +"']"));

        boolean isSpeakerDisplayed = Interactions.isElementVisibleInView(speakerElement);
        boolean isSpeakerAboutElementDisplayed = Interactions.isElementVisibleInView(speakerAboutElement);
        boolean isSpeakerProfessionElementDisplayed = Interactions.isElementVisibleInView(speakerProfessionElement);

        if (isSpeakerDisplayed == false){
            Interactions.swipeIntoView(speakerAboutElement,driver);
            sleep(5000);
            isSpeakerDisplayed = Interactions.isElementVisibleInView(speakerElement);
            isSpeakerAboutElementDisplayed = Interactions.isElementVisibleInView(speakerAboutElement);
            isSpeakerProfessionElementDisplayed = Interactions.isElementVisibleInView(speakerProfessionElement);

        }

        Assert.assertTrue(isSpeakerDisplayed, "Speakers title is not visible.");
        Assert.assertTrue(isSpeakerAboutElementDisplayed, "Speakers About button is not visible.");
        Assert.assertTrue(isSpeakerProfessionElementDisplayed, "Speakers profession is not visible.");
        sleep(1000);
    }

    public void clickSpeakerProfile(int index,String userName)throws InterruptedException{
        WebElement speakerElement = driver.findElement(By.xpath("//ion-col["+ index +"]//h2[text()='" + userName + "']"));

        boolean isSpeakerDisplayed = Interactions.isElementVisibleInView(speakerElement);

        if (isSpeakerDisplayed == false){
            Interactions.swipeIntoView(speakerElement,driver);
            sleep(5000);
        }
        speakerElement.click();
        sleep(1000);
    }

    public void clickSpeakerAbout(String userName)throws InterruptedException{
        WebElement speakerAboutElement = driver.findElement(By.xpath("//h3[text()='About " + userName + "']"));

        boolean isSpeakerDisplayed = Interactions.isElementVisibleInView(speakerAboutElement);

        if (isSpeakerDisplayed == false){
            Interactions.swipeIntoView(speakerAboutElement,driver);
            sleep(5000);
        }
        speakerAboutElement.click();
        sleep(1000);
    }
    public void isSpeakersButtonDisplayed()throws InterruptedException{
        BottomNavigationBar.isSpeakersButtonDisplayed();
    }

    public void clickSpeakersButton()throws InterruptedException{
        BottomNavigationBar.clickSpeakersButton();
    }


}
