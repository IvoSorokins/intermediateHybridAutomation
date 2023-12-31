// TestNG, Appium imports
import dataObjects.event;
import dataObjects.media;
import org.testng.annotations.*;
import io.appium.java_client.AppiumDriver;

// dataProviders imports
import dataObjects.speaker;
import dataProviders.eventsData;
import dataProviders.speakersAndMediaData;
import dataProviders.speakersData;

// utils imports
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

    // Test Cases (Priority is set to run in order)
    @Test(groups ={"TC_1","Tutorial flow"},
            priority = 1)
    public void displayTutorialScreen(){
        WelcomeScreen.verifyTutorial1IsDisplayed();
    }

    @Test(groups ={"TC_2","Tutorial flow"},
            priority = 2)
    public void skipTutorialScreen()throws InterruptedException{
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
    public void continueToSchedueleScreen()throws InterruptedException{ // Test Case will only  work on Android
        WelcomeScreen.swipeLeftOnce();
        WelcomeScreen.verifyTutorial1ScreenNotDisplayedAfterSwipe();

        WelcomeScreen.swipeLeft2Times();
        WelcomeScreen.verifyTutorial4IsDisplayed();
        WelcomeScreen.clickContinueIfDisplayed();
        ScheduleScreen.isScheduleDisplayed();
    }

    @Test(groups ={"TC_5","Favorites user flow"},dataProvider = "eventProvider",dataProviderClass = eventsData.class,
            priority = 5)
    public void navigateToEvents(event Event)throws InterruptedException{
        skipTutorialScreen(); // Skip Tutorial Screen

        ScheduleScreen.swipeDownUntilElementIsVisible(Event.getEventName());
        ScheduleScreen.clickEventIfDisplayed(Event.getEventName());
        EventScreen.checkIfEventNameIsDisplayed(Event.getEventName());
    }

    @Test(groups={"TC_6","Favorites user flow"},dataProvider = "eventProvider",dataProviderClass = eventsData.class,
            priority = 6)
    public void favouriteEvent(event Event)throws InterruptedException{
        navigateToEvents(Event); // Navigate to each event screen

        EventScreen.verifyStarButtonLocation();
        EventScreen.clickStarButtonIfDisplayed(1);
        EventScreen.navigateBackToScheduleScreen();
        ScheduleScreen.clickFavouriteTabIfDisplayed();
        ScheduleScreen.checkIfEventIsDisplayed(Event.getEventName());
    }

    @Test(groups={"TC_7","Favorites user flow"},dataProvider = "eventProvider",dataProviderClass = eventsData.class,
            priority = 7)
    public void unFavouriteEvent(event Event)throws InterruptedException{
        navigateToEvents(Event); // Navigate to each event screen

        EventScreen.clickStarButtonIfDisplayed(2);
        EventScreen.navigateBackToScheduleScreen();
        ScheduleScreen.clickFavouriteTabIfDisplayed();
        ScheduleScreen.noEventsDisplayed(Event.getEventName());
    }

    @Test(groups={"TC_8","Favorites user flow"},dataProvider = "eventProvider",dataProviderClass = eventsData.class,
            priority = 8)
    public void unFavouriteEventFavTabPopUp(event Event)throws InterruptedException{
        favouriteEvent(Event); // Navigates to Favourite Tab after favouring event

        ScheduleScreen.swipeEventLeftHorizontally(Event.getEventName(),Event.getEventDescription());
        ScheduleScreen.getRemoveButton(Event.getIndex());
        ScheduleScreen.clickRemoveIfDisplayed();
        ScheduleScreen.isRemovePopUpDisplayed();
        ScheduleScreen.isRemoveButtonOnPopUpDisplayed();
        ScheduleScreen.isCancelButtonOnPopUpDisplayed();
    }

    @Test(groups={"TC_9","Favorites user flow"},dataProvider = "eventProvider",dataProviderClass = eventsData.class,
            priority = 9)
    public void cancelRemoveEventFromFavTab(event Event)throws InterruptedException{
        unFavouriteEventFavTabPopUp(Event); //

        ScheduleScreen.clickCancelButtonOnPopUp();
        ScheduleScreen.checkIfEventIsDisplayed(Event.getEventName());
        ScheduleScreen.removeButtonIsNotDisplayed();
    }

    @Test(groups={"TC_10","Favorites user flow"},dataProvider = "eventProvider",dataProviderClass = eventsData.class,
            priority = 10)
    public void removeEventFromFavTab(event Event)throws InterruptedException{
        unFavouriteEventFavTabPopUp(Event);

        ScheduleScreen.clickRemoveButtonOnPopUp();
        ScheduleScreen.noEventsDisplayed(Event.getEventName());
    }

    @Test(groups={"TC_11","About Speaker flow"},
            priority = 11)
    public void navigateToSpeakers()throws InterruptedException {
       skipTutorialScreen();

       SpeakersScreen.clickSpeakersButtonIfDisplayed();
       SpeakersScreen.isSpeakersTitleDisplayed();
    }

    @Test(groups={"TC_12","About Speaker flow"},dataProvider = "speakersProvider",dataProviderClass = speakersData.class,
            priority = 12)
    public void checkEachSpeaker(speaker Speaker)throws InterruptedException{
        navigateToSpeakers();

        SpeakersScreen.checkEachSpeakerDisplayed(Speaker.getIndex(),Speaker.getUserName(),Speaker.getProfession());
    }

    @Test(groups={"TC_13","About Speaker flow"},dataProvider = "speakersProvider",dataProviderClass = speakersData.class,
            priority = 13)
    public void navToSpeakerProfile(speaker Speaker)throws InterruptedException{
        navigateToSpeakers();

        SpeakersScreen.clickSpeakerProfile(Speaker.getIndex(), Speaker.getUserName());
        SpeakerAboutScreen.verifyAllButtons();
        SpeakerAboutScreen.verifyMediaButtonColorTheme();
        SpeakerAboutScreen.verifySpeakerAboutDisplayed(Speaker.getUserName(),Speaker.getDescription());
        SpeakerAboutScreen.clickOnPhoneButton();
        SpeakerAboutScreen.verifyContactInfo(Speaker.getEmail(),Speaker.getNumber());
    }

    @Test(groups={"TC_14","About Speaker flow"},dataProvider = "speakersProvider",dataProviderClass = speakersData.class,
            priority = 14)
    public void aboutNavToSpeakerProfile(speaker Speaker)throws InterruptedException{
        navigateToSpeakers();

        SpeakersScreen.clickSpeakerAbout(Speaker.getUserName());
        SpeakerAboutScreen.verifyAllButtons();
        SpeakerAboutScreen.verifyMediaButtonColorTheme();
        SpeakerAboutScreen.verifySpeakerAboutDisplayed(Speaker.getUserName(),Speaker.getDescription());
        SpeakerAboutScreen.clickOnPhoneButton();
        SpeakerAboutScreen.verifyContactInfo(Speaker.getEmail(),Speaker.getNumber());
    }

    @Test(groups={"TC_15","About Speaker flow"},dataProvider = "combinedProvider",dataProviderClass = speakersAndMediaData.class,
            priority = 15)
    public void navToMedia(speaker Speaker, media Media)throws InterruptedException{
        navToSpeakerProfile(Speaker);

        SpeakerAboutScreen.clickOnCancelButtonOnPopUpIfDisplayed();
        SpeakerAboutScreen.clickOnMedia(Media.getMedia());
        IonicAccountScreen.switchToNewWindow();
        IonicAccountScreen.verifyUrl(Media.getButtonColor());
    }
}
