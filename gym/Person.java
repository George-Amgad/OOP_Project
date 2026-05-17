package gym;
enum Gender {male, female}
public class Person {
    private int id;
    private String name, phoneNumber, address, email;
    private Gender gender;
    // constructor
    public Person(int id, 
                String name, 
                Gender gender,
                String address, 
                String phoneNumber, 
                String email) throws Exception {
        setId(id);
        setName(name);
        setGender(gender);
        setAddress(address);
        setPhoneNumber(phoneNumber);
        setEmail(email);
    }
    // setters and getters
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
    public void setPhoneNumber(String phoneNumber) throws Exception {
        if(phoneNumber.length() != 11) {
            throw new Exception("phone number must be 11 digits.");
        }
        this.phoneNumber = phoneNumber;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setGender(Gender gender) throws Exception {
        if(!(gender == Gender.male || gender == Gender.female)) {
            throw new Exception("gender must be 'male' or 'female'.");
        }
        this.gender = gender;
    }
    public Gender getGender() {
        return gender;
    }
    public void setAddress(String address) throws Exception {
        if(address.trim().isEmpty()) {
            throw new Exception("address cannot be empty.");
        }
        this.address = address;
    }
    public String getAddress() {
        return address;
    }
    public void setEmail(String email) throws Exception {
        if(!email.contains("@")) {
            throw new Exception("email must contain '@'.");
        }
        if(!email.contains(".")) {
            throw new Exception("email must contain '.'.");
        }
        if(email.length() < 6) {
            throw new Exception("email must be at least 6 characters long.");
        }
        this.email = email;
    }
    public String getEmail() {
        return email;
    }
}
