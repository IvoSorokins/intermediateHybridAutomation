package screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import org.testng.SkipException;
import utils.interactions;
import utils.shadowDomHelper;

import java.util.List;

import static utils.testProperties.platform;


public class speakerAboutScreen {

    private final AppiumDriver driver;
    public interactions Interactions;
    public shadowDomHelper ShadowDomHelper;


    public speakerAboutScreen(AppiumDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(this.driver), this);

        Interactions = new interactions(driver);
        ShadowDomHelper = new shadowDomHelper(driver);
    }

    // Locators for the elements
    @FindBy(css="ion-chip.ion-color-twitter")
    private WebElement twitterButton;

    @FindBy(css="ion-chip.ion-color-dark")
    private WebElement githubButton;

    @FindBy(css="ion-chip.ion-color-instagram")
    private WebElement instagramButton;

    @FindBy(xpath = "//ion-back-button")
    private WebElement backButton;

    @FindBy(xpath="//span[text()=\"Cancel\"]")
    private WebElement cancelButtonPopUp;

    // Locators for shadow Dom elements (can't verify if element is displayed on the screen or not)
    public WebElement phoneButton ;
    public void getPhoneButton(){
        phoneButton = ShadowDomHelper.getShadowRootElement("ion-buttons:nth-child(2) > ion-button:nth-child(1)","button");
    }

    public WebElement shareButton;
    public void getShareButton(){
        shareButton = ShadowDomHelper.getShadowRootElement("ion-buttons:nth-child(2) > ion-button:nth-child(2)","button");
    }

    // Methods to interact with the elements
    public WebElement getDataProviderElement(String userName, String tagName){
        WebElement speakerElement = Interactions.findElementByTagNameAndText(tagName, userName);
        return speakerElement;
    }

    public void verifyAllButtons(){
        getPhoneButton(); // Using ShadowDomHelper to get Phone button
        getShareButton(); // Using ShadowDomHelper to get Share button
        Interactions.assertElementVisibility(twitterButton,"Twitter button",true);
        Interactions.assertElementVisibility(githubButton,"Github button",true);
        Interactions.assertElementVisibility(instagramButton,"Instagram button",true);
        Interactions.assertElementVisibility(shareButton,"Share button",false);
        Interactions.assertElementVisibility(phoneButton,"Phone button",false);
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

    public void clickOnPhoneButton(){
        phoneButton.click();
//        Interactions.clickElementIfDisplayed(phoneButton,"Phone button"); - will not work for shadow Dom Elements
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
        if (!platform.equals("iOS")) {
            System.out.println("It's expected that pages are not working on Android");
            throw new SkipException("Skipping this test as pages are not working on Android");
        }
    }
    public void verifyMediaButtonColorTheme(){
        String twitterColor = twitterButton.getCssValue("background-color");
        String githubColor = githubButton.getCssValue("background-color");
        String instagramColor = instagramButton.getCssValue("background-color");

        Assert.assertEquals(twitterColor, "rgba(29, 161, 244, 0.08)", "Twitter button color does not match");
        Assert.assertEquals(githubColor, "rgba(34, 36, 40, 0.08)", "GitHub button color does not match");
        Assert.assertEquals(instagramColor, "rgba(149, 55, 188, 0.08)", "Instagram button color does not match");
    }
}
