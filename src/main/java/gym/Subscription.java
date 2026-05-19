package gym;

public class Subscription {
    private int customerId;
    private int coachId;
    private MembershipPlan membershipPlan ;

    public Subscription(int customerId, int coachId, MembershipPlan membershipPlan) {
        this.customerId = customerId;
        this.coachId = coachId;
        this.membershipPlan = membershipPlan;
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
     public void setMembershipPlan(MembershipPlan membershipPlan){
        this.membershipPlan = membershipPlan;
    }
    public MembershipPlan getMembershipPlan(){
        return membershipPlan;
    }
}