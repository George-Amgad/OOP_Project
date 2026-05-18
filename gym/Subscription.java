package gym;

public class Subscription {
    private int customerId;
    private int coachId;

    public Subscription(int customerId, int coachId) {
        this.customerId = customerId;
        this.coachId = coachId;
    }

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