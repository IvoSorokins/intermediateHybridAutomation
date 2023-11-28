package screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import utils.interactions;

import java.util.List;


public class speakerAboutScreen {

    private final AppiumDriver driver;
    public interactions Interactions;

    public speakerAboutScreen(AppiumDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(this.driver), this);
        Interactions = new interactions(driver);
    }


    @FindBy(css="ion-chip.ion-color-twitter")
    private WebElement twitterButton;

    @FindBy(css="ion-chip.ion-color.ion-color-dark")
    private WebElement githubButton;

    @FindBy(css="ion-chip.ion-color.ion-color-instagram")
    private WebElement instagramButton;

    @FindBy(css="ion-buttons:nth-child(2) > ion-button:nth-child(1)")
    private WebElement phoneButton;

    @FindBy(css="ion-buttons:nth-child(2) > ion-button:nth-child(2)")
    private WebElement shareButton;

    @FindBy(xpath = "//ion-back-button")
    private WebElement backButton;

    @FindBy(xpath="//span[text()=\"Cancel\"]")
    private WebElement cancelButtonPopUp;


    public WebElement getDataProviderElement(String userName, String tagName){
        WebElement speakerElement = Interactions.findElementByTagNameAndText(tagName, userName);
        return speakerElement;
    }
    public void verifyAllButtons(){
        Interactions.assertElementVisibility(twitterButton,"Twitter button",true);
        Interactions.assertElementVisibility(githubButton,"Github button",true);
        Interactions.assertElementVisibility(instagramButton,"Instagram button",true);
        Interactions.assertElementVisibility(shareButton,"Share button",true);
        Interactions.assertElementVisibility(backButton,"Back button",true);
    }
    public void verifySpeakerAboutDisplayed(String userName,String description){
        WebElement speakerImg = driver.findElement(By.xpath(String.format("//img[@alt=\"%s\"]",userName)));
        WebElement speakerDesc = getDataProviderElement(description ,"p");

        List<WebElement> elements = driver.findElements(By.xpath((String.format("//h2[text()=\"%s\"]",userName))));
        WebElement speakerName = elements.get(1);

        Interactions.assertElementVisibility(speakerImg,"Speaker image",true);
        Interactions.assertElementVisibility(speakerDesc,"Speaker description",true);
        Interactions.assertElementVisibility(speakerName,"Speaker name",true);

    }

    public void verifyContactInfo(String email,String phone){
        WebElement speakerEmail = getDataProviderElement("Email ( " + email + " )","span");
        WebElement speakerPhone = getDataProviderElement("Call ( " + phone + " )","span");

        Interactions.assertElementVisibility(speakerEmail,"Speaker email",true);
        Interactions.assertElementVisibility(speakerPhone,"Speaker phone",true);
    }

    public void clickOnPhoneButtonIfDisplayed(){
        Interactions.clickElementIfDisplayed(phoneButton,"Phone button");
    }

    public void clickOnCancelButtonOnPopUpIfDisplayed(){
        Interactions.clickElementIfDisplayed(cancelButtonPopUp,"Cancel button");
    }

    public void validateMediaButtonColor(WebElement element,String expectedColor) {
        String color = element.getCssValue("color");
        Assert.assertEquals(color, expectedColor, "Media button color does not match");
    }
    public void clickOnMedia(String media){
        if (media.equals("Twitter")) {
            validateMediaButtonColor(twitterButton,"rgba(26, 142, 215, 1)");
            Interactions.clickElementIfDisplayed(twitterButton,"Twitter button");
        }
        else if (media.equals("GitHub")) {
            validateMediaButtonColor(githubButton,"rgba(30, 32, 35, 1)");
            Interactions.clickElementIfDisplayed(githubButton,"Github button");
        }
        else if (media.equals("Instagram")) {
            validateMediaButtonColor(instagramButton,"rgba(131, 48, 165, 1)");
            Interactions.clickElementIfDisplayed(instagramButton,"Instagram button");
        }
    }

}
