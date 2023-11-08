# Hybrid App Test Automation Framework

This is a test automation framework for hybrid mobile applications, designed for regression testing, and it's built using TestNG and Appium in Java. The tests can be executed on both Android and iOS platforms by specifying the platform when starting the Appium server.

# Framework Overview

## TestNG
TestNG is a testing framework inspired by JUnit and NUnit but introduced some new functionalities that make it more powerful and easier to use. It supports parallel execution, test case prioritization, and various test configurations. In this project, TestNG is used for test management and execution.
In TestNG, annotations are used to define the behavior of test methods, setup methods, and teardown methods. Annotations provide a way to control the flow and execution of test cases. Here are some commonly used annotations in TestNG and their purposes:  
- '@Test' annotation is used to mark a method as a test method.
- '@BeforeMethod' annotation is used to mark a method that should run before each test method in a test class
- '@AfterMethod' annotation is used to mark a method that should run after each test method in a test class
- '@BeforeClass' annotation is used to mark a method that should run once before any of the test methods in the class
- '@AfterClass' annotation is used to mark a method that should run once after all the test methods in the class have executed  

More information about annotations can be found here: https://www.browserstack.com/guide/testng-annotations-in-selenium#:~:text=of%20TestNG%20Annotations-,What%20are%20TestNG%20Annotations%3F,part%20of%20the%20test%20code  
## Appium
Appium is an open-source tool for automating native, mobile web, and hybrid applications on both Android and iOS platforms. It provides a consistent API to interact with your mobile application. This project leverages Appium for interacting with mobile apps and simulators.
The framework includes the following components:

- TestNG for test execution and management.
- Appium for mobile automation.
- Java programming language for scripting.
- Page Object Model (POM) design pattern for organized test scripts.
- Desired capabilities configuration in a `config.properties` file.
- Test setup and properties management using utility classes.
- A sample test suite to demonstrate how to set up and run tests for a hybrid mobile application.
- pom.xml file contains information about the project and configuration details used by maven to build the project.

# Setup

1.**Prerequisites**: Make sure you have the following installed:
- Appium (I used 2.2.1)
- Android SDK (for Android testing)
- ADB: (for communication with Android device)
- Xcode and xcode command line tools (for iOS testing)
- Java (I used 21.0.1) and JDK(Java Development Kit, I used openjdk 21.0.1)
- Maven (for project dependencies)
- Android Studio for testing with Android Simulator / Physical Device
- Xcode(Simulator) for testing with iOS Simulator / 
- Set JAVA_HOME and ANDROID_HOME environment variables to point to the respective installation directories.
- IDE (I Used IntelliJ IDEA)
2.**Clone the Repository**: Clone this repository to your local machine.

3.**Configuration**: Configure your test execution by modifying the `config.properties` file located in `src/main/resources/`.

    - Common properties:
        - `appiumURL`: Appium server URL.
        - `autoWebview`: Set to `false`.
        - `fullContextList`: Set to `true`.
        - `simplicityWaitForElement`: Set the waiting time for elements.

    - iOS properties:
        - `iOSAutomationName`: XCUITest (automation name for iOS).
        - `iOSUdId`: Your iOS device UDID.
        - `iOSPlatformName`: iOS.
        - `iOSDeviceName`: Your iOS device name.
        - `nativeWebTap`: Set to `true`.
        - `includeSafariInWebviews`: Set to `true`.
        - `waitForQuiescence`: Set to `false`.
        - `iOSPlatformVersion`: Your iOS version.
        - `bundleId`: Your iOS app bundle identifier.

    - Android properties:
        - `androidAutomationName`: UiAutomator2 (automation name for Android).
        - `androidPlatformName`: Android.
        - `androidDeviceName`: Your Android device name.
        - `androidUdId`: Your Android device UDID.
        - `broswerName`: Chrome (browser name for Android).
        - `nativeWebScreenshot`: Set to `true`.
        - `newCommandTimeout`: Set the timeout value.
        - `AndroidPlatformVersion`: Your Android version.
        - `appPackage`: Your Android app package.
        - `appActivity`: Your Android app activity.

4.**Appium Server**: Make sure the Appium server is up and running before running tests.

5.**Dependencies**: Build the Maven project to fetch the required dependencies. Run the following command in your project directory:
mvn clean install

## Framework Setup
To set up the framework and run your tests, follow these steps:

1. )Clone this repository to your local machine.  
2. )Open the project in your preferred Java development environment (e.g., IntelliJ IDEA, Eclipse).  
3. )Update the config.properties file located in the src/main/resources directory with your desired configuration settings. This file includes platform-specific capabilities, such as the bundle ID for iOS and other settings.  
4. )In your test class (e.g., allTests.java), use the startServer method from the testSetup class to initialize the Appium server for your chosen platform (Android or iOS):  
```startServer("iOS"); // Select platform (iOS or Android caps)```
This method will load the desired capabilities defined in the config.properties file and start the Appium server for your selected platform.


5. )Remember to regularly update your test suite to include new test cases and maintain your regression test coverage.  





# Running the Tests

To run the sample test case, execute the `allTests` class located in the `test` package. Right-click on your test class in your IDE and select "Run" or "Run as TestNG test." Alternatively, you can use Maven to run your tests by executing the following command in your project's root directory:
```mvn clean test```  


## TroubleShooting

If you encounter any issues, please review the setup steps and ensure all prerequisites are met. Check the Appium server logs for further details on any errors.

Feel free to customize the framework and tests as needed for your specific application. Happy testing!

