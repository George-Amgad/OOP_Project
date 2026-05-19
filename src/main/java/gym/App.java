package gym;

import java.io.FileWriter;
import java.time.LocalDate;
import java.util.ArrayList;

public class App {
    public static void main(String[] args) {
        try {
            // WORKAROUND: DataManager attempts to load data immediately upon instantiation.
            // If temp.json doesn't exist, Jackson will throw an exception and print a stack trace.
            // We create an empty JSON array file first to prevent this.
            // try (FileWriter fw = new FileWriter("temp.json")) {
            //     fw.write("[]");
            // }

            // 1. Create dummy Customer data
            ArrayList<InBody> inBodyRecords = new ArrayList<>();
            inBodyRecords.add(new InBody(LocalDate.now(), 1.75f, 75.5f, 15.0f, 4.0f, 45.0f, 12.0f));
            inBodyRecords.add(new InBody(LocalDate.now().minusMonths(1), 1.75f, 77.0f, 16.5f, 3.8f, 43.0f, 11.5f));
            MembershipPlan plan = new MembershipPlan(5, 3);
            Subscription sub = new Subscription(1, 10, plan);
            Customer c1 = new Customer(
                    1, 
                    "Alice Smith", 
                    Gender.FEMALE, // Assuming you have a Gender enum in the gym package
                    "123 Fitness Ave", 
                    "11111111111", 
                    "alice@example.com", 
                    sub,
                    inBodyRecords
            );

            ArrayList<Object> dataToSave = new ArrayList<>();
            dataToSave.add(c1);

            // 2. Save dummy data to temp.json
            System.out.println("Initializing DataManager to save...");
            DataManager saver = new DataManager("temp.json");
            saver.saveData(dataToSave);
            System.out.println("Data successfully saved to temp.json!\n");

            // 3. Load data from temp.json
            System.out.println("Initializing DataManager to read...");
            DataManager loader = new DataManager("temp.json");
            ArrayList<Object> loadedData = loader.loadData();

            // 4. Print loaded data
            System.out.println("Loaded Data:");
            for (Object obj : loadedData) {
                System.out.println(obj.toString());
            }

        } catch (Exception e) {
            System.err.println("An error occurred in App: " + e.getMessage());
            e.printStackTrace();
        }
    }
}