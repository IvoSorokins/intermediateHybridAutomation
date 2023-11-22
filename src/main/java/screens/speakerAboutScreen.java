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

public class speakerAboutScreen {
    private final AppiumDriver driver;
    interactions jsScripts = new interactions();

    public speakerAboutScreen(AppiumDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(this.driver), this);
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

    @FindBy(css="div.speaker-background > h2")
    private WebElement speakerName;

    @FindBy(xpath="//span[text()=\"Cancel\"]")
    private WebElement cancelButtonPopUp;

    public void verifyAllButtons()throws InterruptedException{
        boolean isTwitterButton = jsScripts.isElementVisibleInView(twitterButton, driver);
        boolean isGithubButton = jsScripts.isElementVisibleInView(githubButton, driver);
        boolean isInstagramButton = jsScripts.isElementVisibleInView(instagramButton, driver);
        boolean isPhoneButton = jsScripts.isElementVisibleInView(phoneButton, driver);
        boolean isShareButton = jsScripts.isElementVisibleInView(shareButton, driver);
        boolean isBackButton = jsScripts.isElementVisibleInView(backButton, driver);


        Assert.assertTrue(isTwitterButton, "Twitter button is not visible.");
        Assert.assertTrue(isGithubButton, "Github button is not visible.");
        Assert.assertTrue(isInstagramButton, "Instagram button is not visible.");
        Assert.assertTrue(isPhoneButton, "Phone button is not visible.");
        Assert.assertTrue(isShareButton, "Share button is not visible.");
        Assert.assertTrue(isBackButton, "Back button is not visible.");
        sleep(1000);
    }
    public void verifySpeakerAboutDisplayed(String userName,String about)throws InterruptedException{
        WebElement speakerImg = driver.findElement(By.xpath("//img[@alt=\""+ userName +"\"]"));
        WebElement speakerDesc = driver.findElement(By.xpath("//p[text()=\""+ about +"\"]"));

        boolean isSpeakerImg = jsScripts.isElementVisibleInView(speakerImg, driver);
        boolean isSpeakerDesc = jsScripts.isElementVisibleInView(speakerDesc, driver);
        boolean isSpeakerName = jsScripts.isElementVisibleInView(speakerName, driver);

        Assert.assertTrue(isSpeakerImg, "Speaker image is not visible.");
        Assert.assertTrue(isSpeakerDesc, "Speaker description is not visible.");
        Assert.assertTrue(isSpeakerName, "Speaker name is not visible.");

        String speakerNameText = speakerName.getText();
        Assert.assertEquals(speakerNameText, userName, "Speaker name is not correct.");

        sleep(1000);
    }

    public void verifyContactInfo(String email,String phone)throws InterruptedException{
        WebElement speakerEmail = driver.findElement(By.xpath("//span[text()=\"Email ( "+email+" )\"]"));
        WebElement speakerPhone = driver.findElement(By.xpath("//span[text()=\"Call ( "+phone+" )\"]"));

        boolean isSpeakerEmail = jsScripts.isElementVisibleInView(speakerEmail, driver);
        boolean isSpeakerPhone = jsScripts.isElementVisibleInView(speakerPhone, driver);

        Assert.assertTrue(isSpeakerEmail, "Speaker email is not visible.");
        Assert.assertTrue(isSpeakerPhone, "Speaker phone is not visible.");
        sleep(1000);
    }

    public void clickOnPhoneButton()throws InterruptedException{
        phoneButton.click();
        sleep(1000);
    }

    public void clickOnCancelButton()throws InterruptedException{
        cancelButtonPopUp.click();
        sleep(1000);
    }

    public void validateMediaButtonColor(WebElement element,String expectedColor) {
        String color = element.getCssValue("color");
        Assert.assertEquals(color, expectedColor, "Media button color does not match");
    }

    public void clickOnMedia(String media)throws InterruptedException{
        if (media=="Twitter") {
            validateMediaButtonColor(twitterButton,"rgba(26, 142, 215, 1)");
            twitterButton.click();
            sleep(1000);
        }
        else if (media=="GitHub") {
            validateMediaButtonColor(githubButton,"rgba(30, 32, 35, 1)");
            githubButton.click();
            sleep(1000);
        }
        else if (media=="Instagram") {
            validateMediaButtonColor(instagramButton,"rgba(131, 48, 165, 1)");
            instagramButton.click();
            sleep(1000);
        }
    }


}
