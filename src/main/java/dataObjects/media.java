package dataObjects;

public class media{
    private String media;
    private String mediaLink;


    public media(String media,String buttonColor){
        this.media = media;
        this.mediaLink = buttonColor;
    }

    public String getMedia() {
        return media;
    }

    public String getButtonColor() {
        return mediaLink;
    }

    @Override
    public String toString() {
        return "speaker{" +
                "mediaData='" + media + '\'' +
                ", mediaLink='" + mediaLink + '\'' +
                '}';
    }
}

