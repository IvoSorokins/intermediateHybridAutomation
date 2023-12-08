package utils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.remote.SupportsContextSwitching;
import org.openqa.selenium.support.PageFactory;

/**
 * This class provides methods to interact with the context of the mobile application.
 * The context is used to switch between the native and web views.
 */
public class context {

    private final AppiumDriver driver;

    public context(AppiumDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(this.driver), this);
    }
    /**
     * This method returns the current context of the mobile application which will be WebView because there is only 1 Native context and no need for getting it
     * @return The current context of the mobile application.
     */
    public String getCurrentContextName(){
        return ((SupportsContextSwitching) driver).getContext();
    }

    /**
     * This method switches the context of the mobile application to the native context.
     */
    public void switchToNative() {
        ((SupportsContextSwitching) driver).context("NATIVE_APP");

    }

    /**
     * Combining with the method getCurrentContextName() to switch to the webview context
     * @param contextName The name of the context to switch to from getCurrentContextName()
     */
    public void switchToWebView(String contextName) {
        ((SupportsContextSwitching) driver).context(contextName);
    }
}
