package gym;
import java.util.ArrayList;
import java.util.List;
public class Gym {
    private String name, address, phoneNumber;
    private List<Equipment> sportsEquipments;
    private List<Coach> coaches;
    private List<Subscription> subscriptions;
    // Constructor
    public Gym(String name, String address, String phoneNumber){
        setName (name);
        setAddress (address);
        setPhoneNumber(phoneNumber);
        this.sportsEquipments = new ArrayList<>();
        this.coaches = new ArrayList<>();
        this.subscriptions = new ArrayList<>();
    }
    // setters, getters and adders
    public void setName (String name){
        this.name= name;
    }
    public String getName (){
        return name;
    }
    public void setAddress(String address){
        this.address = address;
    }
    public String getAddress(){
        return address;
    }
    public void setPhoneNumber(String phoneNumber) {
        if(phoneNumber.length() != 11) {
            throw new java.lang.Error("phone number must be 11 digits.");
        }
        this.phoneNumber = phoneNumber;
    }
    public String getPhoneNumber(){
        return phoneNumber;
    }
    public void addSportsEquipments (Equipment E1){
        this.sportsEquipments.add(E1);
    }
    public void addCoach(Coach coach){
        this.coaches.add(coach);    
    }
    private String getSportsEquipments() {
        String result = "\n--- Gym Sports Equipments ---\n";
        for (int i = 0 ; i < sportsEquipments.size() ; i++){
            Equipment e = sportsEquipments.get(i);
            result +=(i + 1)
            + "Name: " + e.getName() +"\n"
            + "Code: " + e.getCode() + "\n"
            + "Qty: " + e.getQuantity()+ "\n";
        }
        return result;
    }
    private String getCoaches() {
        String result = "\n--- Coaches ---\n";
        for (int i = 0 ; i < coaches.size() ; i++){
            Coach c = coaches.get(i);
            result += (i + 1)+ "Name: " + c.getName() +"\n";
        }
        return result;
    }
    // display methods
    public void displaySportsEquipments() { 
        System.out.println(getSportsEquipments());
    }
    public void displaycoaches() { 
        System.out.println(getCoaches());
    }
    // toString method
    public String toString() {
        String result = "Gym\n\tGym Name: " + name + "\n\tAddress: " + address
            + "\n\tPhone Number: " + phoneNumber + getSportsEquipments() + getCoaches();
        return result;
    }
    public void addSubscription(Subscription subscription) throws Exception {
        int countSub = 0 ;
        for(int i = 0 ; i < subscriptions.size(); i++){
            Subscription existingsub = subscriptions.get(i);
            if (existingsub.getCoachId() == (subscription.getCoachId())){
                countSub++;
            }
        }
        if (countSub >= 10){
            throw new Exception("This coach has reached the maximum limit of 10 customers!");
        }
        else 
            this.subscriptions.add(subscription);
    }
}