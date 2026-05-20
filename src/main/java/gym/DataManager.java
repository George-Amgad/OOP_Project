package gym;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.io.File;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class DataManager {
    private String filename;
    private ArrayList<Object> data;
    private ObjectMapper mapper;

    public DataManager(String filename) {
        this.filename = filename;
        this.data = new ArrayList<>();
        this.mapper = new ObjectMapper();
        this.mapper.registerModule(new JavaTimeModule()); // Required to handle LocalDate
    }

    // Saves the array list by grouping objects under their Class Name keys
    public void saveData(ArrayList<Object> dataToSave) throws Exception {
        Map<String, ArrayList<Object>> structuredMap = new LinkedHashMap<>();

        for (Object obj : dataToSave) {
            if (obj != null) {
                String className = obj.getClass().getName();
                
                // If we haven't seen this class yet, initialize its list
                if (!structuredMap.containsKey(className)) {
                    structuredMap.put(className, new ArrayList<>());
                }
                // Add the object to its respective class list
                structuredMap.get(className).add(obj);
            }
        }

        // Write the mapped object out as pretty-printed JSON
        mapper.writerWithDefaultPrettyPrinter().writeValue(new File(filename), structuredMap);
    }

    // Detects class names from keys, calls constructors, and recursively builds inner lists
    @SuppressWarnings("unchecked")
    public ArrayList<Object> loadData() throws Exception {
        File file = new File(filename);
        if (!file.exists() || file.length() == 0) {
            return new ArrayList<>();
        }

        data.clear();
        
        // 1. Read the JSON file into a basic Map structure
        Map<String, List<?>> rawMap = mapper.readValue(file, Map.class);

        // 2. Loop through each class name key found in the JSON file
        for (String className : rawMap.keySet()) {
            Class<?> targetClass = Class.forName(className); // Find the actual Java class
            List<?> rawList = rawMap.get(className);

            // 3. Convert each raw object into its true class type
            for (Object rawObject : rawList) {
                // convertValue automatically triggers the @JsonCreator constructors 
                // and recursively converts inner lists (like ArrayList<InBody>) automatically!
                Object realObject = mapper.convertValue(rawObject, targetClass);
                data.add(realObject);
            }
        }

        return data;
    }

    public ArrayList<Object> getData() {
        return this.data;
    }

    public void changeFile(String filename){
        this.filename = filename;
    }
}