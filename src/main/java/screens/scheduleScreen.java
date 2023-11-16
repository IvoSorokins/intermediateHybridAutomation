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


public class scheduleScreen {
    private final AppiumDriver driver;
    interactions jsScripts = new interactions();

    public scheduleScreen(AppiumDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(this.driver), this);
    }

    // Locators to used find elements on screen
    @FindBy(xpath = "//ion-header/ion-toolbar/ion-title[position()=1][text()='Schedule']")
    private WebElement scheduleTitle;


    // Methods to interact with the elements
    public void isDisplayed()throws InterruptedException{
        boolean isScheduleTitle = jsScripts.isElementVisibleInView(scheduleTitle, driver);
        Assert.assertTrue(isScheduleTitle, " is not visible.");
        sleep(1000);
    }

    public void isEventDisplayed(String name)throws InterruptedException{
        boolean isEvent = jsScripts.isElementVisibleInView(driver.findElement(By.xpath(String.format("//h3[text()=\"%s\"]", name))), driver);
        Assert.assertTrue(isEvent, " is not visible.");
        sleep(1000);
    }

    public void clickEvent(String name)throws InterruptedException{
        WebElement event = driver.findElement(By.xpath(String.format("//h3[text()='%s']", name)));
        jsScripts.isElementVisibleInView(event, driver);
        event.click();
        sleep(1000);
    }
    public void getSchedueleTitle(){
        // get the driver instance WebDriver driver = …;

        // get the shadow host element using the selector WebElement shadowHost = driver.findElement(By.cssSelector(“#main-content > ng-component > ion-tabs > div > ion-router-outlet > page-schedule > ion-header > ion-toolbar.in-toolbar.md.toolbar-title-default.hydrated > ion-title”));

        // get the shadow root element using JavascriptExecutor WebElement shadowRoot = (WebElement) ((JavascriptExecutor) driver).executeScript(“return arguments[0].shadowRoot”, shadowHost);

        // get the target element using the xpath WebElement targetElement = shadowRoot.findElement(By.xpath(“//div[text()=‘Schedule’]”));

        // click on the target element targetElement.click();
    }

}
