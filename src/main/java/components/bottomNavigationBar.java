package components;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.interactions;


public class bottomNavigationBar {

    private final AppiumDriver driver;
    public interactions Interactions;

    public bottomNavigationBar(AppiumDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(this.driver), this);
        Interactions = new interactions(driver);
    }

    @FindBy(xpath = "//ion-tab-button[@id=\"tab-button-schedule\"]")
    private WebElement scheduleButton;

    @FindBy(xpath = "//ion-tab-button[@id='tab-button-speakers']")
    private WebElement speakersButton;

    @FindBy(xpath = "//ion-tab-button[@id=\"tab-button-map\"]")
    private WebElement mapButton;

    @FindBy(xpath = "//ion-tab-button[@id=\"tab-button-about\"]")
    private WebElement aboutButton;

    public void clickScheduleButtonIfDisplayed(){
        Interactions.clickElementIfDisplayed(scheduleButton, "Schedule button");
    }

    public void clickSpeakersButtonIfDisplayed(){
        Interactions.clickElementIfDisplayed(speakersButton, "Speakers button");
    }
}
