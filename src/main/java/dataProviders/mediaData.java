package dataProviders;

import org.testng.annotations.DataProvider;

public class mediaData {
    @DataProvider(name = "mediaProvider")
    public Object[][] provideData() {
        return new Object[][]{
                {"Twitter",},
                {"GitHub"},
                {"Instagram"}
        };
    }
}
