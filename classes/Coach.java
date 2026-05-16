public class Coach extends Person {
    private int workingHours;
    public Coach(int id, 
                String name,
                String gender,
                String address,
                String phoneNumber, 
                String email,
                int workingHours)
    {
        super(id, name, gender, address, phoneNumber, email);
        setWorkingHours(workingHours);
    }
    public void setWorkingHours(int workingHours) {
        if(workingHours < 0) {
            throw new java.lang.Error("working hours must be non-negative.");
        }else if(workingHours > 10) {
            throw new java.lang.Error("working hours must be less than or equal to 10.");
        }else 
            this.workingHours = workingHours;
    }
    public int getWorkingHours() {
        return workingHours;
    }
    public String toString() {
        return "coach:\n" +
            "  ID: " + getId() + "\n" +
            "  Name: " + getName() + "\n" +
            "  Gender: " + getGender() + "\n" +
            "  Address: " + getAddress() + "\n" +
            "  Phone Number: " + getPhoneNumber() + "\n" +
            "  Email: " + getEmail() + "\n" +
            "  Working Hours: " + getWorkingHours();
    }
}