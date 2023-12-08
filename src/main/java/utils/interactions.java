package utils;


import io.appium.java_client.AppiumDriver;
import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.TouchAction;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import static java.time.Duration.ofSeconds;
import static utils.testProperties.waitTimeInSeconds;


public class interactions {

    private static AppiumDriver driver;
    public static context Context;


    public interactions(AppiumDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(this.driver), this);
        Context = new context(driver);
    }


    /**
     * This method is used to scroll to an element in the application
     * Note: This method is used in the test cases that require scrolling on app not elements
     * @param element - WebElement to scroll to
     */
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

    /**
     * This method is used to swipe in the application Native context in all directions (Up, Down, Left, Right)
     * Note: This method is used in the test cases that require swiping on app not elements
     * @param direction - Direction of the swipe (Up, Down, Left, Right)
     * @param times - Number of times to swipe
     *
     */
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
            startY = direction.equals("Up") ? (int) (size.height * 0.8) : (int) (size.height * 0.3);
            endY = direction.equals("Up") ? (int) (size.height * 0.31) : (int) (size.height * 0.8);
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

    /** This method is used to navigate back in the application
     *
     * Note: As of the current version of the test suite, this method is not used in any test case
     * However, it could be useful in future test cases that require navigating back in the application
     *
     * Usage:
     * - Call this method whenever you need to navigate back in the application
     */
    public void navigateBack(){
        String webviewContext = Context.getCurrentContextName(); // Get Webview context
        Context.switchToNative();
        driver.navigate().back();
        Context.switchToWebView(webviewContext);
    }

    /**
     * Asserts the visibility of a WebElement. Using isDisplayed() is not enough, as it only checks if the element is in the DOM
     * so it might be present but not visible in the view.
     *
     * @param element The WebElement to check.
     * @param elementName The name of the element (used in the assertion failure message).
     * @param shouldBeVisible Whether the element should be visible or not.
     */
    public void assertElementVisibility(WebElement element, String elementName, boolean shouldBeVisible){
        // WebDriverWait timer for a specified number of seconds
        WebDriverWait wait = new WebDriverWait(driver, ofSeconds(waitTimeInSeconds));

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
            // If the element is visible in the view when it should not be, fail assertion
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
    /**
     * Clicks on a WebElement if it's displayed, better then additional isDisplayed() ad click() as it is combined in one method
     * so user doesn't have to repeate the same code over and over again
     * @param element The WebElement to click.
     * @param elementName The name of the element (used in the assertion failure message).
     * @param shouldBeVisible Whether the element should be visible or not is set to true automatically as it should be clicked if displayed
     */
    public void clickElementIfDisplayed(WebElement element,  String elementName){
        assertElementVisibility(element, elementName, true);
        element.click();
    }
    // Used for returning boolean value based of element visibility
    public boolean isElementVisible(WebElement element){
        // WebDriverWait timer for a specified number of seconds
        WebDriverWait wait = new WebDriverWait(driver, ofSeconds(waitTimeInSeconds));

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

    /**
     * Finds an element by tag name and text, mostly used together with dataProviders, or simple elements in xpath that have the same tag name
     * @param tagName - tag name of the element
     * @param text - text of the element
     * @return - returns the element
     */
    public WebElement findElementByTagNameAndText(String tagName, String text){
        return driver.findElement(By.xpath(String.format("//%s[text()=\"%s\"]", tagName, text)));
    }

    /**
     * Swipes until the element is visible in the view or until the max swipe count is reached
     * @param element - WebElement to swipe to
     * @param MaxSwipeCount - max swipe count
     */
    public void swipeUntilElementVisible(WebElement element,int MaxSwipeCount) {
        boolean isElementVisible = isElementVisibleInView(element);
        int swipeCount = 0;

        while (!isElementVisible && swipeCount < MaxSwipeCount) {
            swipe("Up", 1);
            isElementVisible = isElementVisibleInView(element);
            swipeCount++;
        }

        if (!isElementVisible) {
            Assert.fail("Element not found after " + MaxSwipeCount + " swipes.");
        }
    }

    /**
     * Used to swipe an element horizontally(in this automation case on events as it displays remove or favourite buttons)
     * Works only
     * @param elementLocator - xpath of the element
     * @param direction - direction of the swipe
     */
    public void swipeElementHorizontally(String elementLocator,String direction){
        String webviewContext = Context.getCurrentContextName(); // Get Webview context
        Context.switchToNative();

        WebElement element =  driver.findElement(By.xpath(elementLocator));

        Point location = element.getLocation();
        Dimension size = element.getSize();

        int centerY = location.y + size.height / 2;
        int startX = direction.equals("left") ? location.x + size.width - 1 : location.x; // Start from the right side of the element if direction is left, else start from the left side
        int endX = direction.equals("left") ? location.x : location.x + size.width - 1; // End at the left side of the element if direction is left, else end at the right side

        // Perform the swipe action
        TouchAction action = new TouchAction((PerformsTouchActions) driver);
        action.longPress(PointOption.point(startX, centerY))
                .moveTo(PointOption.point(endX, centerY))
                .release()
                .perform();
        Context.switchToWebView(webviewContext);
    }
}
