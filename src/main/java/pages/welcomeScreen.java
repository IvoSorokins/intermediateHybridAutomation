package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.TouchAction;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static utils.context.getCurrentContextName;
import static utils.context.switchToWebView;
import static utils.context.switchToNative;

public class welcomeScreen {
    private final AppiumDriver driver;

    public welcomeScreen(AppiumDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(this.driver), this);
    }

    // Locators to used find elements on screen
    @FindBy(xpath = "//ion-button[@id=\"skip_tutorial_btn\"]")
    private  WebElement skipButtonWeb;

    @FindBy(xpath = "//h2[text()=' Welcome to ']")
    private WebElement welcomeTextWeb;

    @AndroidFindBy(xpath = "//android.view.View[@text=\"Welcome to ICA\"]")
    private WebElement welcomeTextAndroid;

    // MobileElement has been replaced by WebElement  https://github.com/appium/java-client/blob/master/docs/v7-to-v8-migration-guide.md#mobileelement




    // Methods to interact with the elements
    public void tutorial1IsDisplayed() {
        isElementVisibleInView(skipButtonWeb);
        isElementVisibleInView(welcomeTextWeb);
    }

    public void tutorialSkipped(){
        skipButtonWeb.click();
    }
    public void swipeRight(int times) throws InterruptedException {
        String webviewContext = getCurrentContextName(driver); // Get Webview context
        System.out.println("Current Context: " + webviewContext);
        switchToNative(driver);
        // Get the size of the screen
        Dimension size = driver.manage().window().getSize();
        System.out.println("Dimension Size:" +size);

        // Calculate start and end points for the swipe
        int startX = (int) (size.width * 0.8);
        int endX = (int) (size.width * 0.2);
        int startY = size.height / 2;

        TouchAction touchAction = new TouchAction((PerformsTouchActions) driver);

        // Perform the swipe for the given number of times
        for (int i = 0; i < times; i++) {
            touchAction.press(PointOption.point(startX, startY))
                    .moveTo(PointOption.point(endX, startY))
                    .release()
                    .perform();
        }
        Thread.sleep(5000);  // Wait for 1 second
        switchToWebView(driver, webviewContext);
    }
    public boolean tutorial1IsDisplayedAndroid(){
        return isElementVisibleInView(welcomeTextWeb);
    }

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
}


