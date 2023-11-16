package utils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.remote.SupportsContextSwitching;


public class context {
    public static String getCurrentContextName(AppiumDriver driver){
        return ((SupportsContextSwitching) driver).getContext();
    }

    public static void switchToNative(AppiumDriver driver) {
        ((SupportsContextSwitching) driver).context("NATIVE_APP");

    }

    public static void switchToWebView(AppiumDriver driver,String contextName) {
        ((SupportsContextSwitching) driver).context(contextName);
    }







}

