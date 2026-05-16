public class Person {
    private int id;
    private String name, phoneNumber, gender, address, email;
    public Person(int id, 
                String name, 
                String gender,
                String address, 
                String phoneNumber, 
                String email) {
        setId(id);
        setName(name);
        setGender(gender);
        setAddress(address);
        setPhoneNumber(phoneNumber);
        setEmail(email);
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
    public void setPhoneNumber(String phoneNumber) {
        if(phoneNumber.length() != 11) {
            throw new java.lang.Error("phone number must be 11 digits.");
        }
        this.phoneNumber = phoneNumber;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setGender(String gender) {
        gender = gender.toLowerCase();
        if(!(gender.equals("male") || gender.equals("female"))) {
            throw new java.lang.Error("gender must be 'male' or 'female'.");
        }
        this.gender = gender;
    }
    public String getGender() {
        return gender;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getAddress() {
        return address;
    }
    public void setEmail(String email) {
        if(!email.contains("@")) {
            throw new java.lang.Error("email must contain '@'.");
        }
        if(!email.contains(".")) {
            throw new java.lang.Error("email must contain '.'.");
        }
        if(email.length() < 6) {
            throw new java.lang.Error("email must be at least 6 characters long.");
        }
        this.email = email;
    }
    public String getEmail() {
        return email;
    }

    Equipment m1 = new Equipment();

    m1


}
