package dataObjects;

public class speaker {

        private String userName;
        private String profession;
        private String email;
        private String number;
        private String description;
        private int index;

        public speaker(int index, String userName, String profession, String email,String number,String description){
            this.index = index;
            this.userName = userName;
            this.profession = profession;
            this.email = email;
            this.number = number;
            this.description = description;
        }

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


