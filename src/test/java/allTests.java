import org.testng.annotations.*;
import pages.scheduleScreen;
import pages.welcomeScreen;
import io.appium.java_client.AppiumDriver;

import static utils.testSetup.startServer;


public class allTests {
    public static String platform = ""; // Select platform iOS or else(Android caps)
    private AppiumDriver driver;


    public welcomeScreen WelcomeScreen;
    public scheduleScreen ScheduleScreen;

   @BeforeClass(alwaysRun = true)
    public void setUp(){
       driver = startServer(platform);
       WelcomeScreen = new welcomeScreen(driver);
       ScheduleScreen = new scheduleScreen(driver);
    }

    @Test(groups ={"TC_1","Tutorial flow"},
            enabled=true,
            priority = 0)
    public void displayTutorialScreen(){
        WelcomeScreen.tutorial1IsDisplayed();
    }
    @Test(groups ={"TC_2","Tutorial flow"},
            enabled=true,
            priority = 0)
    public void skipTutorialScreen(){
        WelcomeScreen.tutorialSkipped();
        ScheduleScreen.isDisplayed();
    }
    @Test(groups ={"TC_3","Tutorial flow"},
            enabled=true,
            priority = 0)
    public void userSwipesTutorialScreen()throws InterruptedException { // Note Done
        WelcomeScreen.swipeRight(2);
        if (WelcomeScreen.tutorial1IsDisplayedAndroid()){
            System.out.println("Tutorial screen did not swipe (Most likely duo to a iOS simulator bug - expected behavior)");
        }else {
            WelcomeScreen.swipeRight(2);
        }
    }

    @Test(
            groups ={},
            enabled=true,
            priority=0)
    public void userSwipsTutorialScreen(){

    }

}
//    @Test(description = "Part 4",groups = {""},enabled=false, priority=2,)


