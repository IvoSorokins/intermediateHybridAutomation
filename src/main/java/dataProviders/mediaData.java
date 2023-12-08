package dataProviders;

import dataObjects.media;

import org.testng.annotations.DataProvider;

/**
 * This dataProvider class is used to provide data for the media tests
 */
public class mediaData {
    @DataProvider(name = "mediaProvider")
    public Object[][] provideData() {
        return new Object[][]{
                {new media("Twitter","https://twitter.com/ionicframework")},
                {new media("GitHub","https://github.com/ionic-team/ionic-framework")},
                {new media("Instagram","https://www.instagram.com/ionicframework/")}
        };
    }
}
