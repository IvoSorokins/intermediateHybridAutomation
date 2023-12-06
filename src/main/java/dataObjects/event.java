package dataObjects;

public class event {
    private String eventName;
    private String eventDescription;
    private int index;



    public event(String mediaName,String mediaDescription, int index){
        this.eventName = mediaName;
        this.eventDescription = mediaDescription;
        this.index = index;

    }

    public String getEventName() {
        return eventName;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public int getIndex() {
        return index;
    }

    @Override
    public String toString() {
        return "event{" +
                "mediaName='" + eventName + '\'' +
                ", mediaDescription='" + eventDescription + '\'' +
                ", index=" + index +
                '}';
    }
}
