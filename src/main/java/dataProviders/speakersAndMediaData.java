package dataProviders;

import org.testng.annotations.DataProvider;

/**
 * This dataProvider class is used to provide data for the speakers tests
 */
public class speakersAndMediaData {

    @DataProvider(name = "combinedProvider")
    public Object[][] provideData() {
        Object[][] speakersData = new speakersData().provideData();
        Object[][] mediaData = new mediaData().provideData();
        Object[][] combinedData = new Object[speakersData.length * mediaData.length][];
        int index = 0;
        for (Object[] speaker : speakersData) {
            for (Object[] media : mediaData) {
                combinedData[index++] = new Object[] {speaker[0], media[0]};
            }
        }
        return combinedData;
    }
}
