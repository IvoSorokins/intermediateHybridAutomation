package screens;

// Appium , TestNG imports
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

// utils imports
import utils.context;
import utils.interactions;


public class ionicAccountScreen {
    private final AppiumDriver driver;
    public interactions Interactions;
    public context Context;

    @FindBy(xpath="//h1[@id=\"modal-header\"]")
    private WebElement twitterHeader;

    @FindBy(xpath="//div[@id=\"repository-container-header\"]")
    private WebElement githubHeader;

    @FindBy(xpath="//page-speaker-detail/ion-content")
    private WebElement speakerDetailContent;


    public ionicAccountScreen(AppiumDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(this.driver), this);
        Interactions = new interactions(driver);
        Context = new context(driver);
    }

    public void verifyWebpageIsDisplayed(){
        String secondWebViewContext = Context.getSecondWebViewContext();
        System.out.println("Second webview context: " + secondWebViewContext);
        Context.switchToWebView(secondWebViewContext);
    }
}
