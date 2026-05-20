package gym;

import java.util.ArrayList;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Customer extends Person {
    private Subscription subscription;
    private ArrayList<InBody> inBodyRecords = new ArrayList<>();

    @JsonCreator
    public Customer(@JsonProperty("id") int id,
                    @JsonProperty("name") String name,
                    @JsonProperty("gender") Gender gender,
                    @JsonProperty("address") String address,
                    @JsonProperty("phoneNumber") String phoneNumber,
                    @JsonProperty("email") String email,
                    @JsonProperty("subscription") Subscription subscription,
                    @JsonProperty("inBodyRecords") ArrayList<InBody> inBodyRecords) throws Exception {
        super(id, name, gender, address, phoneNumber, email);
        // if(id != sub.getCustomerId()){
        //     throw new Exception("Provided subscription doesn't match provided customer ID.");
        // }
        if (subscription != null){
            this.subscription = subscription;
        }
        if (inBodyRecords != null) {
            this.inBodyRecords = new ArrayList<>(inBodyRecords);
        }
    }

    public void addInBodyRecord(InBody inBody) {
        inBodyRecords.add(inBody);
    }

    public ArrayList<InBody> getInBodyRecords() {
        return new ArrayList<>(inBodyRecords);
    }

    public void setInBodyRecords(ArrayList<InBody> inBodyRecords) {
        this.inBodyRecords = inBodyRecords == null ? new ArrayList<>() : new ArrayList<>(inBodyRecords);
    }

    public void showInBodyHistory() {
        if (inBodyRecords.isEmpty()) {
            System.out.println("No InBody records yet");
            return;
        }

        for (InBody ib : inBodyRecords) {
            System.out.println(ib);
        }
    }
    public void setSubscription(Subscription subscription) {
    if (subscription == null) {
        throw new IllegalArgumentException("Subscription cannot be null");
    }
    this.subscription = subscription;
    }

    public Subscription getSubscription() {
        return subscription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Customer)) return false;
        Customer customer = (Customer) o;
        return getId() == customer.getId() &&
                Objects.equals(getName(), customer.getName()) &&
                getGender() == customer.getGender() &&
                Objects.equals(getAddress(), customer.getAddress()) &&
                Objects.equals(getPhoneNumber(), customer.getPhoneNumber()) &&
                Objects.equals(getEmail(), customer.getEmail()) &&
                Objects.equals(subscription, customer.subscription) &&
                Objects.equals(inBodyRecords, customer.inBodyRecords);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getGender(), getAddress(), getPhoneNumber(), getEmail(), subscription, inBodyRecords);
    }

    @Override
    public String toString() {
        return "Customer:\n"
            + "  ID: " + getId() + "\n"
            + "  Name: " + getName() + "\n"
            + "  Gender: " + getGender() + "\n"
            + "  Address: " + getAddress() + "\n"
            + "  Phone Number: " + getPhoneNumber() + "\n"
            + "  Email: " + getEmail() + "\n"
            + "  Subsciption: " + getSubscription() + "\n"
            + "  InBody Records: " + getInBodyRecords();
    }
}
