import org.testng.annotations.*;
import pages.welcomeScreen;
import io.appium.java_client.AppiumDriver;

import java.io.OutputStream;
import java.io.PrintStream;

import static utils.testSetup.startServer;


public class allTests {
    private AppiumDriver driver;
    public welcomeScreen WelcomeScreen;

   @BeforeMethod(alwaysRun = true)
    public void setUp(){
       driver = startServer(""); // Select platform iOS or else(Android caps)
       WelcomeScreen = new welcomeScreen(driver);
    }

    @Test(  groups ={"TC_1","Tutorial flow"},
            enabled=true,
            priority = 0)
    public void displayTutorialScreen(){
        WelcomeScreen.tutorialIsDisplayed();
    }
    @Test(groups ={"TC_2","Tutorial flow"},
            enabled=true,
            priority = 0)
    public void skipTutorialScreen(){
        WelcomeScreen.tutorialSkipped();
    }

}
//    @Test(description = "Part 4",groups = {""},enabled=false, priority=2,)


