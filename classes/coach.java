public class coach extends Person {
    private int workinghours;

    public coach(int id, 
                String name, String gender, String address, String phonenumber, String email, int workinghours)
    {
        super(id, name, gender, address, phonenumber, email);
        setWorkinghours(workinghours);
    }
    public void setWorkinghours(int workinghours) {
        if(workinghours < 0) {
            throw new java.lang.Error("working hours must be non-negative.");
        }else if(workinghours > 10) {
            throw new java.lang.Error("working hours must be less than or equal to 10.");
        }else 
            this.workinghours = workinghours;
    }
    public int getWorkinghours() {
        return workinghours;
    }

    public String toString() {
        return "coach:\n" +
            "  ID: " + getId() + "\n" +
            "  Name: " + getName() + "\n" +
            "  Gender: " + getGender() + "\n" +
            "  Address: " + getAddress() + "\n" +
            "  Phone Number: " + getPhoneNumber() + "\n" +
            "  Email: " + getEmail() + "\n" +
            "  Working Hours: " + workinghours;
    }

}