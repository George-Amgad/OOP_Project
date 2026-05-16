import java.util.ArrayList;
import java.util.List;
public class Gym {

    private String Name, Add , Phone_num;

    private List<Equipment> sportsEquipments;
    private List<Coach> coaches;
    private List<Subscription> subscriptions;

    public Gym (String Name , String Add , String Phone_num){
        setName (Name);
        setAdd (Add);
        setPhone_num(Phone_num);
        this.sportsEquipments = new ArrayList<>();
        this.coaches = new ArrayList<>();
        this.subscriptions = new ArrayList<>();
        }
        public void setName (String Name){
            this.Name= Name;
        }
        public String getName (){
            return Name;
        }
        public void setAdd(String Add){
            this.Add = Add;
        }
        public String getAdd(){
            return Add;
        }
          public void setPhone_num(String Phone_num) {
        if(Phone_num.length() != 11) {
            throw new java.lang.Error("phone number must be 11 digits.");
        }
        this.Phone_num = Phone_num;
    }
        public String getPhone_num(){
            return Phone_num;
        }


        public void AddsportsEquipments (Equipment E1){
            this.sportsEquipments.add(E1);
        }
        
        public void Addcoaches(Coach C1){
            this.coaches.add(C1);
            
        }
        public void Addsubscriptions(Subscription S1){
            this.subscriptions.add(S1);
        }

       public void displaySportsEquipments() { 
        System.out.println("\n--- Gym Sports Equipments ---\n");
        for (int i = 0 ; i < sportsEquipments.size() ; i++){
            Equipment E = sportsEquipments.get(i);
          
            System.out.println((i + 1)
            + "Name: " + E.getName() +"\n"
            + "Code: " + E.getCode() + "\n"
            + "Qty: " + E.getQuantity()+ "\n");
        }
    }
       public void displaycoaches() { 
        System.out.println("\n--- Coaches ---\n");
        for (int i = 0 ; i < coaches.size() ; i++){
            
            Coach E = coaches.get(i);
          
            System.out.println((i + 1)+ "Name: " + E.getName() +"\n");
        }
    }
}









