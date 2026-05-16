package gym;
public class Subscription {
    private int customerId, coachId;
    // constructor
    public Subscription(int customerId, int coachId) {
        this.customerId = customerId;
        this.coachId = coachId;
    }
    // setters and getters
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }
    public int getCustomerId() {
        return customerId;
    }
    public void setCoachId(int coachId) {
        this.coachId = coachId;
    }
    public int getCoachId() {
        return coachId;
    }
}