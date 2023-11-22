package dataObjects;

public class media{
    private String media;
    private String buttonColor;


    public media(String media,String buttonColor){
        this.media = media;
        this.buttonColor = buttonColor;
    }

    public String getMedia() {
        return media;
    }

    public String getButtonColor() {
        return buttonColor;
    }

    @Override
    public String toString() {
        return "speaker{" +
                "mediaData='" + media + '\'' +
                ", buttonColor='" + buttonColor + '\'' +
                '}';
    }
}

