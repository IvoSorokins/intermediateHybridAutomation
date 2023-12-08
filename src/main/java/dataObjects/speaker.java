package dataObjects;

public class speaker {

    private String userName;
    private String profession;
    private String email;
    private String number;
    private String description;
    private int index;

    /**
     * Constructor for the speaker class
     * @param index Used for locating elements by numbering them , getting them by number from the list
     * @param userName The name of the speaker
     * @param profession The profession of the speaker
     * @param email The email of the speaker
     * @param number The number of the speaker
     * @param description The description of the speaker
     */
    public speaker(int index, String userName, String profession, String email,String number,String description){
        this.index = index;
        this.userName = userName;
        this.profession = profession;
        this.email = email;
        this.number = number;
        this.description = description;
    }

    /**
     * Getter methods that return the values of the corresponding proprties when called
     */
    public int getIndex() {
            return index;
        }

    public String getUserName() {
        return userName;
    }

    public String getProfession() {
            return profession;
        }

    public String getEmail() {
        return email;
    }

    public String getNumber(){
        return number;
    }

    public String getDescription(){
        return description;
    }

    /**
     * @Override annotation is used to inform the compiler that the annotated method is intended to override a method in the superclas
     */
    @Override
    public String toString() {
        return "speaker{" +
                "index='" + index + '\'' +
                "userName='" + userName + '\'' +
                ", profession='" + profession + '\'' +
                ", email='" + email + '\'' +
                ", number='" + number + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}


