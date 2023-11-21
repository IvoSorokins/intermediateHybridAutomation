package screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.interactions;

public class speakerAboutScreen {
    private final AppiumDriver driver;
    interactions jsScripts = new interactions();

    public speakerAboutScreen(AppiumDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(this.driver), this);
    }

    @FindBy(css="ion-chip.ion-color.ion-color-twitter.md.ion-activatable.hydrated")
    private WebElement twitterButton;

    public void verifySpeakerAboutDisplayed(String userName,String email, String phone, String about)throws InterruptedException{
        WebElement speakerImg = driver.findElement(By.xpath("//img[@alt='"+ userName +"']"));
        WebElement speakerDesc = driver.findElement(By.xpath("//p[text()='"+ about +"']"));
        WebElement speakerUserName = driver.findElement(By.xpath("//h2[text()='"+ userName +"']"));
    }
}
