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

    public void checkEachSpeakerDisplayed(int index,String userName,String profession){

        WebElement speakerName = Interactions.findElementByTagNameAndText("h2",userName);
        WebElement speakerAboutElement = Interactions.findElementByTagNameAndText("h3","About " + userName);
        WebElement speakerProfessionElement = Interactions.findElementByTagNameAndText("ion-col["+ index +"]//p",profession);

        boolean isSpeakerDisplayed = Interactions.isElementVisibleInView(speakerName);

        while (isSpeakerDisplayed == false){
            Interactions.swipe("Up",1);
            isSpeakerDisplayed = Interactions.isElementVisibleInView(speakerName);
        }

        Interactions.assertElementVisibility(speakerName,"Speaker name",true);
        Interactions.assertElementVisibility(speakerAboutElement,"Speaker about section",true);
        Interactions.assertElementVisibility(speakerProfessionElement,"Speaker profession",true);
    }

    public void clickSpeakerProfile(int index,String userName){
        WebElement speakerElement = driver.findElement(By.xpath("//ion-col["+ index +"]//h2[text()='" + userName + "']"));

        boolean isSpeakerDisplayed = Interactions.isElementVisibleInView(speakerElement);

        if (isSpeakerDisplayed == false){
            Interactions.swipe("Up",1);
            isSpeakerDisplayed = Interactions.isElementVisibleInView(speakerElement);
        }
        speakerElement.click();
    }

    public void clickSpeakerAbout(String userName){
        WebElement speakerAboutElement = driver.findElement(By.xpath("//h3[text()='About " + userName + "']"));

        boolean isSpeakerDisplayed = Interactions.isElementVisibleInView(speakerAboutElement);

        if (isSpeakerDisplayed == false){
            Interactions.swipe("Up",1);
            isSpeakerDisplayed = Interactions.isElementVisibleInView(speakerAboutElement);
        }
        speakerAboutElement.click();

    }
    public void isSpeakersButtonDisplayed()throws InterruptedException{
        BottomNavigationBar.isSpeakersButtonDisplayed();
    }

    public void clickSpeakersButton()throws InterruptedException{
        BottomNavigationBar.clickSpeakersButton();
    }


}
