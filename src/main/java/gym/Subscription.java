package gym;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Subscription {
    private int customerId;
    private int coachId;
    private MembershipPlan membershipPlan ;

    @JsonCreator
    public Subscription(@JsonProperty("customerId") int customerId,
                        @JsonProperty("coachId") int coachId,
                        @JsonProperty("membershipPlan") MembershipPlan membershipPlan) {
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