import dataProviders.eventNames;
import org.testng.annotations.*;
import screens.eventScreen;
import screens.scheduleScreen;
import screens.welcomeScreen;
import io.appium.java_client.AppiumDriver;
import utils.interactions;

import static java.lang.Thread.sleep;
import static utils.testSetup.startServer;


public class allTests {
    public static String platform = ""; // Select platform iOS or else(Android caps)
    private AppiumDriver driver;


    private interactions interactions;
    public welcomeScreen WelcomeScreen;
    public scheduleScreen ScheduleScreen;

    public eventScreen EventScreen;

   @BeforeMethod(alwaysRun = true)
    public void setUp(){
       driver = startServer(platform);
       WelcomeScreen = new welcomeScreen(driver);
       ScheduleScreen = new scheduleScreen(driver);
       EventScreen = new eventScreen(driver);
    }
    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        driver.quit();
    }

    @Test(groups ={"TC_1","Tutorial flow"},
            enabled=true,
            priority = 0)
    public void displayTutorialScreen()throws InterruptedException{
        WelcomeScreen.tutorial1IsDisplayed(); // Checks that tutorial 1 is displayed
    }
    @Test(groups ={"TC_2","Tutorial flow"},
            enabled=true,
            priority = 0)
    public void skipTutorialScreen()throws InterruptedException{
        WelcomeScreen.tutorialSkip(); // Clicks on skip button
        ScheduleScreen.isDisplayed(); // Checks that schedule screen is displayed
    }
    @Test(groups ={"TC_3","Tutorial flow"},
            enabled=true,
            priority = 0)
    public void userSwipesTutorialScreen()throws InterruptedException { // Note Done
        boolean isSwipeable = WelcomeScreen.isTutorial1DisplayedAfterSwipeLeft(); // Check if screen is even swipeable

        WelcomeScreen.continueSwipingTutorial(isSwipeable);// Continue swiping if screen is swipeable or display message
                                                           // about screen not being swipeable (most likely on iOS)
    }
    @Test(groups ={"TC_4","Tutorial flow"},
            enabled=true,
            priority = 0)
    public void continueToSchedueleScreen()throws InterruptedException{
        interactions.swipe("Left",3, driver);
        WelcomeScreen.isSkipButtonVisible(); // Checks that skip button is not visible, expected result is false
        WelcomeScreen.tutorial4IsDisplayed(); // Checks that tutorial 4 is displayed
        WelcomeScreen.clickContinueButton(); // Clicks on continue button
        ScheduleScreen.isDisplayed(); // Checks that schedule screen is displayed
    }
    @Test(groups ={"TC_5","Favorites user flow"},dataProvider = "eventProvider",dataProviderClass = eventNames.class,
            enabled=true,
            priority = 0)
    public void navigateToEvents(String eventName)throws InterruptedException{
        WelcomeScreen.tutorialSkip(); // Skip tutorial
        ScheduleScreen.isDisplayed(); // Check that schedule screen is displayed
        ScheduleScreen.isEventDisplayed(eventName); // Check that event with name from dataProvider is displayed
        ScheduleScreen.clickEvent(eventName); // Click on event with name from dataProvider
        EventScreen.isEventNameDisplayed(eventName); // Check that event name is displayed on Event Screen
    }

    @Test(groups={"TC_6","Favorites user flow"},dataProvider = "eventProvider",dataProviderClass = eventNames.class,
            enabled=true,
            priority = 0)
    public void favouriteEvent(String eventName)throws InterruptedException{
        WelcomeScreen.tutorialSkip(); // Skip tutorial
        ScheduleScreen.clickEvent(eventName); // Click on event with name from dataProvider
        EventScreen.isEventNameDisplayed(eventName); // Check that event name is displayed on Event Screen
        EventScreen.isStarButtonDisplayed(); // Find Favour (Star) button
        EventScreen.verifyStarButtonLocation(); // Verify Favour (Star) button location
        EventScreen.verifyStarButtonColorWhite();
        EventScreen.clickStarButton(1); // Click on Favour (Star) button
        EventScreen.verifyStarButtonColorBlack();
        sleep(2000);
    }
    @Test(groups={"TC_7","Favorites user flow"},dataProvider = "eventProvider",dataProviderClass = eventNames.class,
            enabled=true,
            priority = 0)
    public void unFavouriteEvent(String eventName)throws InterruptedException{
        WelcomeScreen.tutorialSkip();
        ScheduleScreen.clickEvent(eventName);
        EventScreen.clickStarButton(2); // Click on Favour (Star) button
        EventScreen.verifyStarButtonColorWhite();
    }
    public void unFavouriteEventFavTab(String eventName)throws InterruptedException{
        favouriteEvent(eventName); // Favourites events

    }




}



