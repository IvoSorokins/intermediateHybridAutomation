package utils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.remote.SupportsContextSwitching;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


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

    public String getSecondWebViewContext(){
        // Get a handle to the open contexts
        Set<String> contextNames = ((SupportsContextSwitching) driver).getContextHandles();

        // Convert the Set to a List
        List<String> contextList = new ArrayList<>(contextNames);

        // Check if there is a second context
        if (contextList.size() < 2) {
            throw new IllegalStateException("No second WebView context found");
        }

        // Return the second context
        return contextList.get(1);
    }







}

