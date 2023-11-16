package dataProviders;

import org.testng.annotations.DataProvider;

public class eventNames {
        @DataProvider(name = "eventProvider")
        public Object[][] provideData() {
            return new Object[][]{
                    {"Breakfast"},
                    {"Getting Started with Ionic"},
                    {"Ionic Tooling"},
                    {"University of Ionic"},
                    {"Migrating to Ionic"}
                    // Elements only visible in view as it was not mentioned to include all of them


                    // Add more events as needed
        };
    }
}
