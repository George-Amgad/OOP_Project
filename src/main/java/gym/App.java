package gym;

import java.util.ArrayList;

public class App {

    public static void main(String[] args) throws Exception {
        // create sample Equipment objects
        ArrayList<Object> equipments = new ArrayList<>();
        equipments.add(new Equipment("Treadmill", "EQ-001", 5));
        equipments.add(new Equipment("Dumbbell Set", "EQ-002", 10));
        equipments.add(new Equipment("Exercise Bike", "EQ-003", 3));

        String filename = "temp.json";

        // instantiate DataManager (will attempt to load existing file but it's okay)
        DataManager writerManager = new DataManager(filename, "gym.Equipment");
        // save the sample data to JSON
        writerManager.saveDataToJSON(equipments);
        System.out.println("Saved " + equipments.size() + " equipment to " + filename);

        // load the data back using DataManager
        DataManager readerManager = new DataManager(filename, "gym.Equipment");
        ArrayList<Object> loaded = readerManager.getData();
        System.out.println("Loaded " + (loaded == null ? 0 : loaded.size()) + " equipment from " + filename);
        if (loaded != null) {
            for (Object o : loaded) {
                Equipment e = (Equipment) o;
                System.out.println(e.getName() + " | " + e.getCode() + " | qty:" + e.getQuantity());
            }
        }

        // test Customer JSON save/load
        ArrayList<Object> customers = new ArrayList<>();
        ArrayList<InBody> records1 = new ArrayList<>();
        records1.add(new InBody(java.time.LocalDate.now().minusDays(10), 1.75f, 70.0f, 12.5f, 3.2f, 40.0f, 10.0f));
        records1.add(new InBody(java.time.LocalDate.now(), 1.75f, 69.5f, 12.0f, 3.1f, 39.8f, 9.8f));
        customers.add(new Customer(1, "Alice", "alice@example.com", records1));

        ArrayList<InBody> records2 = new ArrayList<>();
        records2.add(new InBody(java.time.LocalDate.now().minusDays(5), 1.82f, 85.0f, 15.0f, 3.8f, 44.0f, 11.5f));
        customers.add(new Customer(2, "Bob", "bob@example.com", records2));

        String customerFile = "customers.json";
        DataManager customerWriter = new DataManager(customerFile, "gym.Customer");
        customerWriter.saveDataToJSON(customers);
        System.out.println("Saved " + customers.size() + " customers to " + customerFile);

        DataManager customerReader = new DataManager(customerFile, "gym.Customer");
        ArrayList<Object> loadedCustomers = customerReader.getData();
        System.out.println("Loaded " + (loadedCustomers == null ? 0 : loadedCustomers.size()) + " customers from " + customerFile);
        if (loadedCustomers != null) {
            for (Object o : loadedCustomers) {
                Customer c = (Customer) o;
                System.out.println("Customer " + c.getId() + ": " + c.getName() + " (" + c.getEmail() + ")");
                System.out.println("  InBody records: " + (c.getInbodyRecords() == null ? 0 : c.getInbodyRecords().size()));
                if (c.getInbodyRecords() != null) {
                    for (InBody inBody : c.getInbodyRecords()) {
                        System.out.println("    " + inBody);
                    }
                }
            }
        }
    }
}
