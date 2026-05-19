package gym;

import java.util.ArrayList;
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
                    @JsonProperty("email") String email) {
        super(id, name, gender, address, phoneNumber, email);
    }
    public void addInBodyRecord(InBody inBody) {
        inBodyRecords.add(inBody);
    }    
    public ArrayList<InBody> getInBodyRecords() {
        return new ArrayList<>(inBodyRecords);
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


}