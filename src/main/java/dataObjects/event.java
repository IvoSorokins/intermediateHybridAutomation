package dataObjects;

public class event {
    private String mediaName;
    private String mediaDescription;


    public event(String mediaName,String mediaDescription){
        this.mediaName = mediaName;
        this.mediaDescription = mediaDescription;
    }

    public String getMediaName() {
        return mediaName;
    }

    public String getMediaDescription() {
        return mediaDescription;
    }

    @Override
    public String toString() {
        return "event{" +
                "mediaName='" + mediaName + '\'' +
                ", mediaDescription='" + mediaDescription + '\'' +
                '}';
    }
}
