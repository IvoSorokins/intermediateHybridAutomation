package utils;


import io.appium.java_client.AppiumDriver;
import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.TouchAction;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.ElementOption;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

import static utils.testProperties.waitTimeInSeconds;


public class interactions {

    private static AppiumDriver driver;

    public static context Context;


    public interactions(AppiumDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(this.driver), this);
        Context = new context(driver);
    }

    // Check if an element center is visible in the current view
    public boolean isElementVisibleInView(WebElement element) {
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

    public static void swipe(String direction, int times){
        String webviewContext = Context.getCurrentContextName(); // Get Webview context
        Context.switchToNative();

        // Get the size of the screen
        Dimension size = driver.manage().window().getSize();

        // Calculate start and end points for the swipe
        int startX, endX, startY, endY;
        if (direction.equals("Left") || direction.equals("Right")) {
            startX = direction.equals("Left") ? (int) (size.width * 0.8) : (int) (size.width * 0.2);
            endX = direction.equals("Left") ? (int) (size.width * 0.2) : (int) (size.width * 0.8);
            startY = endY = size.height / 2;
        } else { // Up or Down
            startY = direction.equals("Up") ? (int) (size.height * 0.8) : (int) (size.height * 0.2);
            endY = direction.equals("Up") ? (int) (size.height * 0.2) : (int) (size.height * 0.8);
            startX = endX = size.width / 2;
        }

        TouchAction touchAction = new TouchAction((PerformsTouchActions) driver);

        // Perform the swipe for the given number of times
        for (int i = 0; i < times; i++) {
            touchAction.press(PointOption.point(startX, startY))
                    .moveTo(PointOption.point(endX, endY))
                    .release()
                    .perform();
        }
        Context.switchToWebView(webviewContext);
    }

    public void navigateBack(){
        String webviewContext = Context.getCurrentContextName(); // Get Webview context
        Context.switchToNative();
        driver.navigate().back();
        Context.switchToWebView(webviewContext);

    }


    public void swipeElement(String eventName,String direction,String platform){
        String webviewContext = Context.getCurrentContextName(); // Get Webview context
        Context.switchToNative();

        WebElement event;
        if (platform.equalsIgnoreCase("iOS")) {
            // Replace 'iOSXPath' with the actual XPath for iOS
            System.out.print(String.format("//XCUIElementTypeStaticText[@name=\"%s\"]",eventName));
            event = driver.findElement(By.xpath(String.format("//XCUIElementTypeStaticText[@name=\"%s\"]",eventName)));
        } else { // Android
            System.out.print(String.format("//android.view.View[@text=\"%s\"]",eventName));
            event = driver.findElement(By.xpath(String.format("//android.view.View[@text=\"%s\"]",eventName)));
        }


        // Get the X and Y coordinates of the element
        int leftX = event.getLocation().getX();
        int rightX = leftX + event.getSize().getWidth();
        int Y = event.getLocation().getY();


        // Create a new TouchAction
        TouchAction touchAction = new TouchAction((PerformsTouchActions) driver);
//        switchToNative(driver);

        // Perform the swipe action based on the direction
        if (direction.equalsIgnoreCase("left")) {
            touchAction.press(ElementOption.point(rightX, Y))
                    .waitAction(WaitOptions.waitOptions(Duration.ofMillis(500))) // Add a wait time
                    .moveTo(ElementOption.point(leftX, Y))
                    .release()
                    .perform();
            System.out.println("Swipe left x:"+rightX+" y: " +Y+" Move to:x"+leftX+"y: "+Y);
        } else if (direction.equalsIgnoreCase("right")) {
            touchAction.press(ElementOption.point(leftX, Y))
                    .waitAction(WaitOptions.waitOptions(Duration.ofMillis(500)))
                    .moveTo(ElementOption.point(rightX, Y))
                    .release()
                    .perform();
            System.out.println("Swipe Right x:"+leftX+" y: " +Y+" Move to: x"+rightX+"y: "+Y);
        }
        Context.switchToWebView(webviewContext);
    }

    /**
     * Asserts the visibility of a WebElement.
     *
     * @param element The WebElement to check.
     * @param elementName The name of the element (used in the assertion failure message).
     * @param shouldBeVisible Whether the element should be visible or not.
     */
    public void assertElementVisibility(WebElement element, String elementName, boolean shouldBeVisible){
        // WebDriverWait timer for a specified number of seconds
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(waitTimeInSeconds));

        // Boolean to check if the element is visible
        boolean isElementVisible;

        try {
            // Wait until the element is found
            wait.until(ExpectedConditions.visibilityOf(element));
            // If the element was found , set isElementVisible to true
            isElementVisible = true;
        }catch (TimeoutException e){
            // If a TimeoutException is thrown, the element is not found
            // Set isElementVisible to false
            isElementVisible = false;
        }

        // If the element should be visible but is not found, fail assertion
        if(shouldBeVisible && !isElementVisible){
            Assert.fail(elementName + " is not found");

            // If the element should not be visible but is found, check if it's visible in the view
        } else if(!shouldBeVisible && isElementVisible){
            boolean isElementVisibleInView = isElementVisibleInView(element);
            // If the element is visible in the view, fail assertion
            if(isElementVisibleInView){
                Assert.fail(elementName + " is visible in view");
            }
            // If the element should be visible and is found, check if it's visible in the view
        } else if(shouldBeVisible && isElementVisible){
            boolean isElementVisibleInView = isElementVisibleInView(element);
            // If the element is not visible in the view, fail the assertion
            if(!isElementVisibleInView){
                Assert.fail(elementName + " is found, but not visible in view");
            }
        }
    }

    public void clickElementIfDisplayed(WebElement element,  String elementName){
        assertElementVisibility(element, elementName, true);
        element.click();
    }
    // Used for returning boolean value based of element visibility
    public boolean isElementVisible(WebElement element){
        // WebDriverWait timer for a specified number of seconds
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(waitTimeInSeconds));

        // Boolean to check if the element is visible
        boolean isElementVisible;

        try {
            // Wait until the element is found
            wait.until(ExpectedConditions.visibilityOf(element));
            // If the element was found , set isElementVisible to true
            isElementVisible = true;
        }catch (TimeoutException e){
            // If a TimeoutException is thrown, the element is not found
            // Set isElementVisible to false
            isElementVisible = false;
        }

        // If the element is found, check if it's visible in the view
        if(isElementVisible){
            boolean isElementVisibleInView = isElementVisibleInView(element);
            // If the element is not visible in the view, return false
            if(!isElementVisibleInView){
                return false;
            }
        }

        // Return the visibility of the element
        return isElementVisible;
    }
    public WebElement findElementByTagNameAndText(String tagName, String text){
        return driver.findElement(By.xpath(String.format("//%s[text()=\"%s\"]", tagName, text)));
    }
    public void swipeUntilElementVisible(WebElement element) {
        boolean isElementVisible = isElementVisibleInView(element);
        int swipeCount = 0;

        while (!isElementVisible && swipeCount < 6) {
            swipe("Up", 1);
            isElementVisible = isElementVisibleInView(element);
            swipeCount++;
        }

        if (!isElementVisible) {
            Assert.fail("Element not found after " + 6 + " swipes.");
        }
    }
}
