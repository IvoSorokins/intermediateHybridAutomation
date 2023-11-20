package utils;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

public class shadowDomHelper {


    public WebElement getShadowHost(String cssSelector, AppiumDriver driver){
        return driver.findElement(By.cssSelector(cssSelector));
    }

    public WebElement getShadowRoot(WebElement shadowHost,AppiumDriver driver) {
        return (WebElement) ((JavascriptExecutor) driver).executeScript("return arguments[0].shadowRoot", shadowHost);
    }

    public WebElement getTargetElement(WebElement shadowRoot, String xpath, AppiumDriver driver) {
        return (WebElement) ((JavascriptExecutor) driver).executeScript(
                "return arguments[0].querySelector('" + xpath + "')", shadowRoot);
    }

    public void clickOnElement(WebElement element) {
        element.click();
    }

}
