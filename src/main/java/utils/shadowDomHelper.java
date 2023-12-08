package utils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;


import java.util.List;

/**
 * This class provides methods to interact with elements inside a shod DOM using Appium, JavascriptExecutor and CSS selectors
 */
public class shadowDomHelper {

    private static AppiumDriver driver;

    public interactions Interactions;

    /**
     * Constructor for the ShadowDomHelper class.
     * @param driver The AppiumDriver instance used to interact with the mobile application.
     */
    public shadowDomHelper(AppiumDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(this.driver), this);
        Interactions = new interactions(driver);
    }

    /**
     * This method returns the root element of a shadow DOM.
     * @param hostSelector The CSS selector for the host element of the shadow DOM.
     * @param childSelector The CSS selector for the child element inside the shadow DOM.
     * @return The root element of the shadow DOM.
     */
    public WebElement getShadowRootElement(String hostSelector, String childSelector){
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        String mark = "return document.querySelector(\""+hostSelector+"\").shadowRoot.querySelector(\""+childSelector+"\")";
        WebElement shadowRootElement = (WebElement) jsExecutor.executeScript(mark);
        return shadowRootElement;
    }

    /**
     * This method returns a child element at a specific index inside a shadow DOM.
     * @param hostSelector The CSS selector for the host element of the shadow DOM.
     * @param childSelector The CSS selector for the child elements inside the shadow DOM.
     * @param index The index of the child element to return.
     * @return The child element at the specified index.
     *
     * This method is never called in the code, but could be used in future if there are multiple similar child elements.
     */
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
