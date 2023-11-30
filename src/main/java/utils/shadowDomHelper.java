package utils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;



public class shadowDomHelper {

    private static AppiumDriver driver;

    public shadowDomHelper(AppiumDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(this.driver), this);

    }
    private String getMarkHelper(String hostSelector,String childSelector){
        String mark = "return document.querySelector(\""+hostSelector+"\").shadowRoot.querySelector(\""+childSelector+"\")";
                            //document.querySelector("ion-item-group:nth-child(1) > ion-item-sliding > ion-item-options ion-item-option").shadowRoot.querySelector("button");
        return mark;
    }
}
