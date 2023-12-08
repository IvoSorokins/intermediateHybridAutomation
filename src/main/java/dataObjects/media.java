package dataObjects;

public class media{

    private String media;
    private String mediaLink;

    /**
     * Constructor for the media class
     * @param media The name of the media
     * @param mediaLink The link to which media will bring user
     */
    public media(String media,String mediaLink){
        this.media = media;
        this.mediaLink = mediaLink;
    }

    /**
     * Getter methods that return the values of the corresponding proprties when called
     */
    public String getMedia() {
        return media;
    }

    public String getButtonColor() {
        return mediaLink;
    }

    /**
     *@Override annotation is used to inform the compiler that the annotated method is intended to override a method in the superclas
     */
    @Override
    public String toString() {
        return "speaker{" +
                "mediaData='" + media + '\'' +
                ", mediaLink='" + mediaLink + '\'' +
                '}';
    }
}

