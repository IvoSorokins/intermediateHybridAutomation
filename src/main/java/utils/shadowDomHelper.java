package utils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class shadowDomHelper {

    private static AppiumDriver driver;

    public shadowDomHelper(AppiumDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(this.driver), this);

    }

    // Code From : https://www.headspin.io/blog/working-with-web-components-shadow-dom
    public WebElement getNestedShadowRootElement(List<String> hostTagNames, String selector) {
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
}
