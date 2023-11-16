package utils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import static utils.context.*;


public class interactions {

    // Check if an element center is visible in the current view
    public boolean isElementVisibleInView(WebElement element, AppiumDriver driver) {
        return (Boolean) ((JavascriptExecutor) driver).executeScript(
                "var elem = arguments[0],                 " + // Retrieves the WebElement from the arguments passed to the JavaScript code
                        "  box = elem.getBoundingClientRect(),    " + // Calculates the position and dimensions of the element using
                        "  cx = box.left + box.width / 2,         " + //  Calculate the horizontal and
                        "  cy = box.top + box.height / 2,         " + // vertical center coordinates of the element
                        "  e = document.elementFromPoint(cx, cy); " + // Retrieves the element at the calculated center coordinates
                        // This helps to identify the actual element that is at the center, as elements might overlap
                        "for (; e; e = e.parentElement) {         " + // Initiates a loop that iterates through the parent elements of the found element
                        "  if (e === elem)                        " + // Checks if the current parent element is the same as the original WebElement
                        "    return true;                         " + // If it is, then the element is visible
                        "}                                        " +
                        "return false;                            " // If not, then the element is not visible
                , element); // Pass the WebElement as an argument to the JavaScript code
    }

    public static void swipe(String direction, int times, AppiumDriver driver) throws InterruptedException {
        String webviewContext = getCurrentContextName(driver); // Get Webview context
        switchToNative(driver);

        // Get the size of the screen
        Dimension size = driver.manage().window().getSize();

        // Calculate start and end points for the swipe
        int startX = (direction.equals("Left")) ? (int) (size.width * 0.8) : (int) (size.width * 0.2);
        int endX = (direction.equals("Left")) ? (int) (size.width * 0.2) : (int) (size.width * 0.8);
        int startY = size.height / 2;

        TouchAction touchAction = new TouchAction((PerformsTouchActions) driver);

        // Perform the swipe for the given number of times
        for (int i = 0; i < times; i++) {
            touchAction.press(PointOption.point(startX, startY))
                    .moveTo(PointOption.point(endX, startY))
                    .release()
                    .perform();
        }
        Thread.sleep(4000);  // Wait for 4 second
        switchToWebView(driver, webviewContext);
    }

    public static WebElement findElementInShadowRoot(WebElement shadowHost, String cssSelector, AppiumDriver driver) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        WebElement shadowRoot = (WebElement) jsExecutor.executeScript("return arguments[0].shadowRoot", shadowHost);
        return (WebElement) jsExecutor.executeScript("return arguments[0].querySelector(arguments[1])", shadowRoot, cssSelector);
    }
}
