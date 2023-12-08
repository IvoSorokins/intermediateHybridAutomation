package dataObjects;

/**
 * This class represents an event with a name, description, and index.
 */
public class event {

    private String eventName;
    private String eventDescription;
    private int index;

    /**
     * Constructor for the event class
     * @param eventName The name of the event
     * @param eventDescription The description of the event
     * @param index The index of the event
     */
    public event(String eventName,String eventDescription, int index){
        this.eventName = eventName;
        this.eventDescription = eventDescription;
        this.index = index;
    }

    /**
     * Getter methods that return the values of the corresponding proprties when called
     */
    public String getEventName() {
        return eventName;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public int getIndex() {
        return index;
    }

    /**
     *@Override annotation is used to inform the compiler that the annotated method is intended to override a method in the superclas
     */
    @Override
    public String toString() {
        return "event{" +
                "eventName='" + eventName + '\'' +
                ", eventDescription='" + eventDescription + '\'' +
                ", index=" + index +
                '}';
    }
}
