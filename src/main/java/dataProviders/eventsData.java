package dataProviders;

import dataObjects.event;
import org.testng.annotations.DataProvider;

public class eventsData {
        @DataProvider(name = "eventProvider")
        public Object[][] provideData() {
            return new Object[][]{
                    {new event("Breakfast","8:00 am — 9:00 am: Dining Hall",0)},
                    {new event("Getting Started with Ionic","9:30 am — 9:45 am: Hall 2",1)},
                    {new event("Ionic Tooling","9:45 am — 10:00 am: Executive Ballroom",2)},
                    {new event("University of Ionic","9:15 am — 9:30 am: Hall 3",3)},
                    {new event("Migrating to Ionic","10:00 am — 10:15 am: Hall 1",4)},
                    {new event("What's New in Angular","10:15 am — 10:30 am: Hall 3",5)},
                    {new event("The Evolution of Ionicons","10:15 am — 10:30 am: Hall 2",6)},
                    {new event("Ionic Pro","10:45 am — 11:00 am: Grand Ballroom A",7)},
                    {new event("Ionic Workshop","11:00 am — 11:45 am: Hall 1",8)},
                    {new event("Community Interaction","11:30 am — 11:50 am: Hall 3",9)},
                    {new event("Navigation in Ionic","11:30 am — 12:00 pm: Grand Ballroom A",10)},
                    {new event("Lunch","12:00 pm — 1:00 pm: Dining Hall",11)},
                    {new event("Ionic in the Enterprise","1:00 pm — 1:15 pm: Hall 1",12)},
                    {new event("Ionic Worldwide","1:15 pm — 1:30 pm: Hall 1",13)},
                    {new event("The Ionic Package","1:30 pm — 2:00 pm: Grand Ballroom B",14)},
                    {new event("Push Notifications in Ionic","2:00 pm — 2:30 pm: Hall 2",15)},
                    {new event("Ionic Documentation","2:30 pm — 2:45 pm: Grand Ballroom B",16)},
                    {new event("UX in Ionic","2:45 pm — 3:00 pm: Hall 3",17)},
                    {new event("Angular Directives in Ionic","3:00 pm — 3:30 pm: Hall 1",18)},
                    {new event("Mobile States","3:30 pm — 3:45 pm: Hall 2",19)}
        };
    }
}
