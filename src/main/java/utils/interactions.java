package utils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.ElementOption;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import java.time.Duration;

import static java.lang.Thread.sleep;
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
        switchToWebView(driver, webviewContext);
        sleep(2000);  // Wait for 2 second
    }

    public static void navigateBack(AppiumDriver driver)throws InterruptedException{
        String webviewContext = getCurrentContextName(driver); // Get Webview context
        switchToNative(driver);
        driver.navigate().back();
        switchToWebView(driver, webviewContext);
        sleep(1000);
    }
    public static void swipeElement(String eventName,String direction,String platform, AppiumDriver driver) throws InterruptedException {
        String webviewContext = getCurrentContextName(driver); // Get Webview context
        switchToNative(driver);

        WebElement event;
        if (platform.equalsIgnoreCase("iOS")) {
            // Replace 'iOSXPath' with the actual XPath for iOS
            System.out.print(String.format("//XCUIElementTypeStaticText[@name=\"%s\"]",eventName));
            event = driver.findElement(By.xpath(String.format("//XCUIElementTypeStaticText[@name=\"%s\"]",eventName)));
        } else { // Androide
            System.out.print(String.format("//android.view.View[@text='%s']",eventName));
            event = driver.findElement(By.xpath(String.format("//android.view.View[@text='%s']",eventName)));
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
        switchToWebView(driver, webviewContext);
        sleep(1000);
    }

}
