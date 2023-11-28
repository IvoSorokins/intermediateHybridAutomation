package utils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static utils.testProperties.waitTimeInSeconds;

public class shadowDomHelper {

    private static AppiumDriver driver;

    public shadowDomHelper(AppiumDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(this.driver), this);

    }

    // Code From : https://www.headspin.io/blog/working-with-web-components-shadow-dom
    public WebElement getNestedShadowRootElement(List<String> hostTagNames, String selector) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(waitTimeInSeconds));
        WebElement shadowHost = null;
        WebElement shadowElement = null;
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;

        for (String hostTagName : hostTagNames) {
            if (shadowHost == null) {
                // Find the first host element of the Shadow DOM
                shadowHost = driver.findElement(By.cssSelector(hostTagName));
            } else {
                // Find the next host element inside the Shadow DOM of the previous host element
                String jsScript = "return arguments[0].shadowRoot.querySelector('" + hostTagName + "');";
                shadowHost = (WebElement) jsExecutor.executeScript(jsScript, shadowHost);
            }
        }

        // Find the final element inside the Shadow DOM of the last host element
        String jsScript = "return arguments[0].shadowRoot.querySelector('" + selector + "');";
        shadowElement = (WebElement) jsExecutor.executeScript(jsScript, shadowHost);

        return shadowElement;
    }
    public WebElement getShadowRootElement(String hostSelector, String shadowSelector) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(waitTimeInSeconds));
        WebElement shadowHost = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(hostSelector)));
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        WebElement shadowRoot = (WebElement) jsExecutor.executeScript("return arguments[0].shadowRoot", shadowHost);
        WebElement shadowElement = wait.until(ExpectedConditions.visibilityOf((WebElement) jsExecutor.executeScript("return arguments[0].querySelector(arguments[1])", shadowRoot, shadowSelector)));
        return shadowElement;
    }
}
