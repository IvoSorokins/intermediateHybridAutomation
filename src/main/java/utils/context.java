package utils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.remote.SupportsContextSwitching;
import org.openqa.selenium.support.PageFactory;


public class context {

    private final AppiumDriver driver;

    public context(AppiumDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(this.driver), this);
    }

    public String getCurrentContextName(){
        return ((SupportsContextSwitching) driver).getContext();
    }

    public void switchToNative() {
        ((SupportsContextSwitching) driver).context("NATIVE_APP");

    }
    public void switchToWebView(String contextName) {
        ((SupportsContextSwitching) driver).context(contextName);
    }
}
