package gym;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

enum Gender {
    MALE,
    FEMALE
}

public class Person {
    private int id;
    private String name;
    private String phoneNumber;
    private String address;
    private String email;
    private Gender gender;

    @JsonCreator
    public Person(@JsonProperty("id") int id,
                  @JsonProperty("name") String name,
                  @JsonProperty("gender") Gender gender,
                  @JsonProperty("address") String address,
                  @JsonProperty("phoneNumber") String phoneNumber,
                  @JsonProperty("email") String email) {
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
        if (phoneNumber == null || phoneNumber.length() != 11) {
            throw new IllegalArgumentException("Phone number must be 11 digits.");
        }
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setGender(Gender gender) {
        if (gender == null) {
            throw new IllegalArgumentException("Gender cannot be null.");
        }
        this.gender = gender;
    }

    public Gender getGender() {
        return gender;
    }

    public void setAddress(String address) {
        if (address == null || address.trim().isEmpty()) {
            throw new IllegalArgumentException("Address cannot be empty.");
        }
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setEmail(String email) {
        if (email == null || !email.contains("@") || !email.contains(".")) {
            throw new IllegalArgumentException("Email must be a valid address.");
        }
        if (email.length() < 6) {
            throw new IllegalArgumentException("Email must be at least 6 characters long.");
        }
        this.email = email;
    }

    public String getEmail() {
        return email;
    }
}
