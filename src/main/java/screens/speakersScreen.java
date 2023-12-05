package screens;

import components.bottomNavigationBar;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.interactions;


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

    @FindBy(xpath = "//ion-title[text()=\"Speakers\"]")
    private WebElement speakersTitle;

    public WebElement getDataProviderElement(String userName, String tagName){
        WebElement speakerElement = Interactions.findElementByTagNameAndText(tagName, userName);
        return speakerElement;
    }
    public void swipeDownUntilElementIsVisible(String eventName,String tagName){
        Interactions.swipeUntilElementVisible(getDataProviderElement(eventName,tagName),7);
    }
    public void isSpeakersTitleDisplayed(){
        Interactions.assertElementVisibility(speakersTitle,"Speakers title",true);
    }

    public void checkEachSpeakerDisplayed(int index,String userName,String profession){
        swipeDownUntilElementIsVisible("About " +userName, "h3");

        WebElement speakerName = getDataProviderElement(userName,"h2");
        WebElement speakerAboutElement = getDataProviderElement("About " + userName,"h3");
        WebElement speakerProfessionElement = getDataProviderElement(profession,"ion-col["+ index +"]//p");

        Interactions.assertElementVisibility(speakerName,"Speaker name",true);
        Interactions.assertElementVisibility(speakerAboutElement,"Speaker about section",true);
        Interactions.assertElementVisibility(speakerProfessionElement,"Speaker profession",true);
    }

    public void clickSpeakerProfile(int index,String userName){
        WebElement speakerElement = getDataProviderElement(userName,"ion-col["+ index +"]//h2");
        Interactions.swipeUntilElementVisible(speakerElement, 7);
        Interactions.clickElementIfDisplayed(speakerElement, "Speaker name");
    }

    public void clickSpeakerAbout(String userName){
        WebElement speakerAboutElement = getDataProviderElement("About " + userName,"h3");
        Interactions.swipeUntilElementVisible(speakerAboutElement,7);

        Interactions.clickElementIfDisplayed(speakerAboutElement, "Speaker about section");
    }

    public void clickSpeakersButtonIfDisplayed(){
        BottomNavigationBar.clickSpeakersButtonIfDisplayed();
    }


}
