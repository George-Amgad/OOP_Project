package gym;

import java.util.ArrayList;
import java.util.List;

public class Gym {
    private String name;
    private String address;
    private String phoneNumber;
    private List<Equipment> sportsEquipments;
    private List<Coach> coaches;
    private List<Subscription> subscriptions;

    public Gym(String name, String address, String phoneNumber) {
        setName(name);
        setAddress(address);
        setPhoneNumber(phoneNumber);
        this.sportsEquipments = new ArrayList<>();
        this.coaches = new ArrayList<>();
        this.subscriptions = new ArrayList<>();
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
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

    public void addSportsEquipment(Equipment equipment) {
        this.sportsEquipments.add(equipment);
    }

    public void addCoach(Coach coach) {
        this.coaches.add(coach);
    }

    private String getSportsEquipments() {
        StringBuilder result = new StringBuilder("\n--- Gym Sports Equipments ---\n");
        for (int i = 0; i < sportsEquipments.size(); i++) {
            Equipment equipment = sportsEquipments.get(i);
            result.append(i + 1)
                .append(". Name: ").append(equipment.getName()).append("\n")
                .append("Code: ").append(equipment.getCode()).append("\n")
                .append("Qty: ").append(equipment.getQuantity()).append("\n");
        }
        return result.toString();
    }

    private String getCoaches() {
        StringBuilder result = new StringBuilder("\n--- Coaches ---\n");
        for (int i = 0; i < coaches.size(); i++) {
            Coach coach = coaches.get(i);
            result.append(i + 1).append(". Name: ").append(coach.getName()).append("\n");
        }
        return result.toString();
    }
    //ضيفت دي النهارده  mw
    private String getٍSubscription() {
        String result = "\n--- Subscription ---\n";
        for (int i = 0 ; i < subscriptions.size() ; i++){
            Subscription s = subscriptions.get(i);
            result += (i + 1)+
             "Coach Id: " + s.getCoachId() +"\n" 
             +"Customer Id: "+s.getCustomerId()+"\n";
        }
        return result;
    }

    public void displaySportsEquipments() {
        System.out.println(getSportsEquipments());
    }

    public void displayCoaches() {
        System.out.println(getCoaches());
    }
    // ودي كمان mw
    public void displaysubscription() { 
        System.out.println(getٍSubscription());
    }

    @Override
    public String toString() {
        return "Gym\n\tGym Name: " + name
            + "\n\tAddress: " + address
            + "\n\tPhone Number: " + phoneNumber
            + getSportsEquipments()
            + getCoaches()
            +getٍSubscription();//هنا برضو
    }

    public void addSubscription(Subscription subscription) {
        int countSub = 0;
        for (int i = 0; i < subscriptions.size(); i++) {
            Subscription existingSub = subscriptions.get(i);
            if (existingSub.getCoachId() == subscription.getCoachId()) {
                countSub++;
            }
        }
        if (countSub >= 10) {
            throw new IllegalArgumentException("This coach has reached the maximum limit of 10 customers!");
        } else {
            this.subscriptions.add(subscription);
        }
    }
}