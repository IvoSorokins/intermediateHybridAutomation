package dataObjects;

public class event {
    private String eventName;
    private String eventDescription;



    public event(String mediaName,String mediaDescription){
        this.eventName = mediaName;
        this.eventDescription = mediaDescription;

    }

    public String getEventName() {
        return eventName;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    @Override
    public String toString() {
        return "event{" +
                "mediaName='" + eventName + '\'' +
                ", mediaDescription='" + eventDescription + '\'' +
                '}';
    }
}
