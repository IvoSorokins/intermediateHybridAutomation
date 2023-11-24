// TestNG, Appium imports
import org.testng.annotations.*;
import io.appium.java_client.AppiumDriver;

// dataProviders imports
import dataObjects.speaker;
import dataProviders.eventNamesData;
import dataProviders.speakersAndMediaData;
import dataProviders.speakersData;

// utils imports
import static java.lang.Thread.sleep;
import static utils.testProperties.platform;
import static utils.testSetup.startServer;
import screens.*;


public class allTests {

    // Appium driver instance
    private AppiumDriver driver;

    // Screens objects
    public welcomeScreen WelcomeScreen;
    public scheduleScreen ScheduleScreen;
    public eventScreen EventScreen;
    public speakersScreen SpeakersScreen;
    public speakerAboutScreen SpeakerAboutScreen;
    public ionicAccountScreen IonicAccountScreen;

    // Method to set up the environment before each test
   @BeforeMethod(alwaysRun = true)
    public void setUp(){
       driver = startServer(platform); // Starting Appium server based of platform(Android or iOS, caps are set in testSetup class)

       // Screens objects initialization
       WelcomeScreen = new welcomeScreen(driver);
       ScheduleScreen = new scheduleScreen(driver);
       EventScreen = new eventScreen(driver);
       SpeakersScreen = new speakersScreen(driver);
       SpeakerAboutScreen = new speakerAboutScreen(driver);
       IonicAccountScreen = new ionicAccountScreen(driver);
    }

    // Method to tear down after each test (Clean State)
    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        driver.quit();
    }

    @Test(groups ={"TC_1","Tutorial flow"},
            priority = 1)
    public void displayTutorialScreen(){
        WelcomeScreen.verifyTutorial1IsDisplayed();
    }

    @Test(groups ={"TC_2","Tutorial flow"},
            priority = 2)
    public void skipTutorialScreen(){
        WelcomeScreen.clickSkip();
        ScheduleScreen.isScheduleDisplayed();
    }

    @Test(groups ={"TC_3","Tutorial flow"},
            priority = 3)
    public void userSwipesTutorialScreen(){
        WelcomeScreen.swipeLeftOnce();
        WelcomeScreen.verifyTutorial1ScreenNotDisplayedAfterSwipe();

        // Android only - verify swipeable Right
        WelcomeScreen.verifyTutorial2IsDisplayed();
        WelcomeScreen.swipeLeftOnce();
        WelcomeScreen.verifyTutorial3IsDisplayed();
        WelcomeScreen.swipeLeftOnce();
        WelcomeScreen.verifyTutorial4IsDisplayed();
        // Android only - verify swipeable Left
        WelcomeScreen.swipeRightOnce();
        WelcomeScreen.verifyTutorial3IsDisplayed();
        WelcomeScreen.swipeRightOnce();
        WelcomeScreen.verifyTutorial2IsDisplayed();
        WelcomeScreen.swipeRightOnce();
        WelcomeScreen.verifyTutorial1IsDisplayed();
    }
    @Test(groups ={"TC_4","Tutorial flow"},
            priority = 4)
    public void continueToSchedueleScreen(){ // Test Case will only  work on Android
        WelcomeScreen.swipeLeftOnce();
        WelcomeScreen.verifyTutorial1ScreenNotDisplayedAfterSwipe();

        WelcomeScreen.swipeLeft3Times();
        WelcomeScreen.verifyTutorial4IsDisplayed();
        WelcomeScreen.clickContinue(); // Clicks on continue button
        ScheduleScreen.isScheduleDisplayed(); // Checks that schedule screen is displayed
    }
    @Test(groups ={"TC_5","Favorites user flow"},dataProvider = "eventProvider",dataProviderClass = eventNamesData.class,
            priority = 5)
    public void asdasd(){

    }

    @Test(groups ={"TC_6","Favorites user flow"},dataProvider = "eventProvider",dataProviderClass = eventNamesData.class,
            enabled=true,
            priority = 0)
    public void navigateToEvents(String eventName)throws InterruptedException{
        skipTutorialScreen(); // Skip tutorial
        ScheduleScreen.isEventDisplayed(eventName); // Check that event with name from dataProvider is displayed
        ScheduleScreen.clickEvent(eventName); // Click on event with name from dataProvider
        EventScreen.isEventNameDisplayed(eventName); // Check that event name is displayed on Event Screen
    }

    @Test(groups={"TC_6","Favorites user flow"},dataProvider = "eventProvider",dataProviderClass = eventNamesData.class,
            enabled=true,
            priority = 0)
    public void favouriteEvent(String eventName)throws InterruptedException{
        navigateToEvents(eventName); // Navigate to event
        EventScreen.isStarButtonDisplayed(); // Find Favour (Star) button
        EventScreen.verifyStarButtonLocation(); // Verify Favour (Star) button location
        EventScreen.verifyStarButtonColorWhite();
        EventScreen.clickStarButton(1); // Click on Favour (Star) button
        EventScreen.verifyStarButtonColorBlack();
        sleep(2000);
    }
    @Test(groups={"TC_7","Favorites user flow"},dataProvider = "eventProvider",dataProviderClass = eventNamesData.class,
            enabled=true,
            priority = 0)
    public void unFavouriteEvent(String eventName)throws InterruptedException{
        navigateToEvents(eventName); // Navigate to event
        EventScreen.clickStarButton(2); // Click on Favour (Star) button
        EventScreen.verifyStarButtonColorWhite();
    }
    @Test(groups={"TC_8","Favorites user flow"},dataProvider = "eventProvider",dataProviderClass = eventNamesData.class,
            enabled=true,
            priority = 0)
    public void unFavouriteEventFavTabPopUp(String eventName)throws InterruptedException{
        favouriteEvent(eventName); // Favourite events
        EventScreen.navigateBackToScheduleScreen(); // Navigate back to schedule screen
        ScheduleScreen.isFavouriteTabDisplayed();
        ScheduleScreen.clickFavouriteTab();
        ScheduleScreen.isEventDisplayed(eventName);// Check that event with name from dataProvider is displayed
        ScheduleScreen.swipeEvent(eventName,platform);
        ScheduleScreen.clickRemoveButton();
        ScheduleScreen.isRemovePopUpDisplayed();
        ScheduleScreen.isRemoveButtonOnPopUpDisplayed();
        ScheduleScreen.isCancelButtonOnPopUpDisplayed();
    }
    @Test(groups={"TC_9","Favorites user flow"},dataProvider = "eventProvider",dataProviderClass = eventNamesData.class,
            enabled=true,
            priority = 0)
    public void cancelRemoveEventFromFavTab(String eventName)throws InterruptedException {
        unFavouriteEventFavTabPopUp(eventName);
        ScheduleScreen.clickCancelButtonOnPopUp();
        ScheduleScreen.isEventDisplayed(eventName);
        ScheduleScreen.removeButtonIsNotDisplayed();
    }
    @Test(groups={"TC_10","Favorites user flow"},dataProvider = "eventProvider",dataProviderClass = eventNamesData.class,
            enabled=true,
            priority = 0)
    public void removeEventFromFavTab(String eventName)throws InterruptedException {
        unFavouriteEventFavTabPopUp(eventName);
        ScheduleScreen.clickRemoveButtonOnPopUp();
        ScheduleScreen.noEventsDisplayed(eventName); // Verifys both that event is not displayed and that "No events" message is displayed
    }
    @Test(groups={"TC_11","About Speaker flow"},
            enabled=true,
            priority = 0)
    public void navigateToSpeakers()throws InterruptedException{
       skipTutorialScreen();
        SpeakersScreen.isSpeakersButtonDisplayed();
        SpeakersScreen.clickSpeakersButton();
        SpeakersScreen.isSpeakersTitleDisplayed();
    }

    @Test(groups={"TC_12","About Speaker flow"},dataProvider = "speakersProvider",dataProviderClass = speakersData.class,
            enabled=true,
            priority = 0)
    public void checkEachSpeaker(speaker Speaker)throws InterruptedException{
        navigateToSpeakers();
        SpeakersScreen.checkEachSpeakerDisplayed(Speaker.getIndex(),Speaker.getUserName(),Speaker.getProfession());
    }

    @Test(groups={"TC_13","About Speaker flow"},dataProvider = "speakersProvider",dataProviderClass = speakersData.class,
            enabled=true,
            priority = 0)
    public void navToSpeakerProfile(speaker Speaker)throws InterruptedException {
        navigateToSpeakers();
        SpeakersScreen.clickSpeakerProfile(Speaker.getIndex(), Speaker.getUserName());
        SpeakerAboutScreen.verifyAllButtons();
        SpeakerAboutScreen.verifySpeakerAboutDisplayed(Speaker.getUserName(),Speaker.getDescription());
        SpeakerAboutScreen.clickOnPhoneButton();
        SpeakerAboutScreen.verifyContactInfo(Speaker.getEmail(),Speaker.getNumber());
    }

    @Test(groups={"TC_14","About Speaker flow"},dataProvider = "speakersProvider",dataProviderClass = speakersData.class,
            enabled=true,
            priority = 0)
    public void aboutNavToSpeakerProfile(speaker Speaker)throws InterruptedException {
        navigateToSpeakers();
        SpeakersScreen.clickSpeakerAbout(Speaker.getUserName());
        SpeakerAboutScreen.verifyAllButtons();
        SpeakerAboutScreen.verifySpeakerAboutDisplayed(Speaker.getUserName(),Speaker.getDescription());
        SpeakerAboutScreen.clickOnPhoneButton();
        SpeakerAboutScreen.verifyContactInfo(Speaker.getEmail(),Speaker.getNumber());
    }
    @Test(groups={"TC_15","About Speaker flow"},dataProvider = "combinedProvider",dataProviderClass = speakersAndMediaData.class,
            enabled=true,
            priority = 0)
    public void navToMedia(speaker Speaker, String Media)throws InterruptedException{
        navToSpeakerProfile(Speaker);
        SpeakerAboutScreen.clickOnCancelButton(); // Here I want to input another dataProvider with mediaData name
        SpeakerAboutScreen.clickOnMedia(String.valueOf(Media));
        IonicAccountScreen.verifyWebpageNotAvailableDisplayed();

    }
}



