package utils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.List;


public class shadowDomHelper {

    private static AppiumDriver driver;

    public interactions Interactions;

    public shadowDomHelper(AppiumDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(this.driver), this);
        Interactions = new interactions(driver);
    }
    public WebElement getShadowRootElement(String hostSelector, String childSelector){
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        String mark = "return document.querySelector(\""+hostSelector+"\").shadowRoot.querySelector(\""+childSelector+"\")";
        WebElement shadowRootElement = (WebElement) jsExecutor.executeScript(mark);
        return shadowRootElement;
    }

    public WebElement getShadowElementAtIndex(String hostSelector, String childSelector,int index){
            JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;

            String mark = "var hostElements = document.querySelectorAll('" +hostSelector + "');" // ''
                    + "var shadowRootElements = [];"
                    + "for (var i = 0; i < hostElements.length; i++) {"
                    + "    var shadowRoot = hostElements[i].shadowRoot;"
                    + "    var shadowRootElement = shadowRoot.querySelector('" + childSelector + "');" //
                    + "    shadowRootElements.push(shadowRootElement);"
                    + "}"
                    + "return shadowRootElements;";
            List<WebElement> shadowRootElements = (List<WebElement>) jsExecutor.executeScript(mark);
            WebElement element = shadowRootElements.get(index);
            return element;
        }
    }
