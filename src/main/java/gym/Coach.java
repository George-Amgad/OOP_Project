package gym;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Coach extends Person {
    private int workingHours;

    @JsonCreator
    public Coach(@JsonProperty("id") int id,
                 @JsonProperty("name") String name,
                 @JsonProperty("gender") Gender gender,
                 @JsonProperty("address") String address,
                 @JsonProperty("phoneNumber") String phoneNumber,
                 @JsonProperty("email") String email,
                 @JsonProperty("workingHours") int workingHours) {
        super(id, name, gender, address, phoneNumber, email);
        setWorkingHours(workingHours);
    }

    public void setWorkingHours(int workingHours) {
        if (workingHours < 0) {
            throw new IllegalArgumentException("Working hours must be non-negative.");
        } else if (workingHours > 10) {
            throw new IllegalArgumentException("Working hours must be less than or equal to 10.");
        } else {
            this.workingHours = workingHours;
        }
    }

    public int getWorkingHours() {
        return workingHours;
    }

    @Override
    public String toString() {
        return "Coach:\n"
            + "  ID: " + getId() + "\n"
            + "  Name: " + getName() + "\n"
            + "  Gender: " + getGender() + "\n"
            + "  Address: " + getAddress() + "\n"
            + "  Phone Number: " + getPhoneNumber() + "\n"
            + "  Email: " + getEmail() + "\n"
            + "  Working Hours: " + getWorkingHours();
    }
}