package dataProviders;

import org.testng.annotations.DataProvider;

public class eventsData {
        @DataProvider(name = "eventProvider")
        public Object[][] provideData() {
            return new Object[][]{
                    {"Breakfast"},
                    {"Getting Started with Ionic"},
                    {"Ionic Tooling"},
                    {"University of Ionic"},
                    {"Migrating to Ionic"},
                    {"What's New in Angular"},
                    {"The Evolution of Ionicons"},
                    {"Ionic Pro"},
                    {"Ionic Workshop"},
                    {"Community Interaction"},
                    {"Navigation in Ionic"},
                    {"Lunch"},
                    {"Ionic in the Enterprise"},
                    {"Ionic Worldwide"},
                    {"The Ionic Package"},
                    {"Push Notifications in Ionic"},
                    {"Ionic Documentation"},
                    {"UX in Ionic"},
                    {"Angular Directives in Ionic"},
                    {"Mobile States"}
        };
    }
}
