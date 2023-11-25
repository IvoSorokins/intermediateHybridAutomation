package utils;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

public class shadowDomHelper {

    private static AppiumDriver driver;
    public WebElement expandRootElement(WebElement element) {
        WebElement ele = (WebElement) ((JavascriptExecutor) driver)
                .executeScript("return arguments[0].shadowRoot", element);
        return ele;
    }

    public WebElement getShadowDomElement(WebElement shadowHost, String cssSelector) {
        WebElement shadowRoot = expandRootElement(shadowHost);
        return (WebElement) ((JavascriptExecutor) driver)
                .executeScript("return arguments[0].querySelector('" + cssSelector + "')", shadowRoot);
    }
}