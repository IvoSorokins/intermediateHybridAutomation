import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import pages.welcomeScreen;
import static utils.testSetup.startServer;

public class allTests {
    private WebDriver driver;

    public  welcomeScreen WelcomeScreen;

    @BeforeMethod(alwaysRun = true)
    public void setUp(){
        driver = startServer(""); // Select platform iOS or else(Android caps)
        WelcomeScreen = new welcomeScreen(driver);
    }
    @Test(description ="Part 1 Sign Up",enabled=true, priority = 0)
    public void SignUp() throws Exception {
        WelcomeScreen.isDisplayed();
    }

}
//    @Test(description = "Part 4",groups = {""},enabled=false, priority=2,)


