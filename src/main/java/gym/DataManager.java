package gym;

import java.util.Scanner;
import java.util.Dictionary;
import java.util.ArrayList;
import java.io.File;
// import java.io.PrintWriter;
// import java.io.PrintWriter;
// import java.io.FileNotFoundException;
import java.lang.reflect.Constructor;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DataManager {
    private static Dictionary<String, String[]> parseFormat;
    // private Scanner reader;
    // private PrintWriter writer;
    String filename;
    private ArrayList<Object> data;

    public DataManager(String filename) throws Exception {
        this.filename = filename;
        // reader = new Scanner(new File(filename));
        // if(!reader.hasNextLine()) {
        // throw new IllegalArgumentException("File is empty.");
        // }
        // reader.useDelimiter("\\s*,\\s*");
        // writer = new PrintWriter(filename);
        if (filename.endsWith(".csv"))
            data = this.loadDataFromCSV();
        else if (filename.endsWith(".json"))
            throw new IllegalArgumentException("For .json files, you must specify the class name.");
        else
            throw new IllegalArgumentException("Unsupported file format. Only .csv and .json are supported.");
    }

    public DataManager(String filename, String className) throws Exception {
        this.filename = filename;
        // reader = new Scanner(new File(filename));
        // if(!reader.hasNextLine()) {
        // throw new IllegalArgumentException("File is empty.");
        // }
        // reader.useDelimiter("\\s*,\\s*");
        // writer = new PrintWriter(filename);
        Class<?> cls = Class.forName(className);
        if (filename.endsWith(".csv"))
            data = this.loadDataFromCSV();
        else if (filename.endsWith(".json"))
            data = this.loadDataFromJSON(cls);
        else
            throw new IllegalArgumentException("Unsupported file format. Only .csv and .json are supported.");
    }

    // public void close() {
    // reader.close();
    // writer.close();
    // }

    public static void generateParseFormat() throws ClassNotFoundException {
        File currentDir = new File(".");
        File[] files = currentDir.listFiles();

        if (files != null) {
            for (File file : files) {
                if (file.isFile() && file.getName().endsWith(".java")) {
                    // Perform your operation
                    String className = file.getName().replace(".java", "");
                    Class<?> cls = Class.forName(className);
                    Constructor<?>[] constructors = cls.getConstructors();
                    String[] paramTypes = null;
                    for (Constructor<?> constructor : constructors) {
                        Class<?>[] parameterTypes = constructor.getParameterTypes();
                        if (paramTypes == null || parameterTypes.length > paramTypes.length) {
                            paramTypes = new String[parameterTypes.length];
                            for (int i = 0; i < parameterTypes.length; i++) {
                                paramTypes[i] = parameterTypes[i].getSimpleName();
                            }
                        }
                    }
                    parseFormat.put(className, paramTypes);
                }
            }
        }
    }

    protected ArrayList<Object> loadDataFromCSV() throws Exception {
        Scanner reader = new Scanner(new File(filename));
        try {
            if (!reader.hasNextLine()) {
                throw new IllegalArgumentException("File is empty.");
            }
            data = new ArrayList<>();
            String line = reader.nextLine();
            String[] values = line.split("\\s*,\\s*");
            String className = values[0].trim();
            Constructor<?> constructor = getLargestConstructor(className);
            String[] paramTypes = parseFormat.get(className);
            while (reader.hasNextLine()) {
                int rowIndex = reader.nextInt(); // trash
                Object[] params = new Object[paramTypes.length];
                for (int i = 0; i < paramTypes.length; i++) {
                    switch (paramTypes[i]) {
                        case "int":
                            params[i] = reader.nextInt();
                            break;
                        case "float":
                            params[i] = reader.nextFloat();
                            break;
                        case "double":
                            params[i] = reader.nextDouble();
                            break;
                        case "String":
                            params[i] = reader.next();
                            break;
                        case "LocalDate":
                            params[i] = parseDate(reader.next());
                            break;
                    }
                }
                data.add(constructor.newInstance(params));
            }
        } finally {
            reader.close();
        }
        return data;
    }

    // loads data from a JSON array into an ArrayList of Objects
    private ArrayList<Object> loadDataFromJSON(Class<?> cls) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.findAndRegisterModules();
        try {
            data = mapper.readValue(new File(filename),
                    mapper.getTypeFactory().constructCollectionType(ArrayList.class, cls));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    // saves the current data ArrayList to a JSON file
    public void saveDataToJSON(ArrayList<Object> data) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.findAndRegisterModules();
        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(new File(filename), data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveDataToJSON() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.findAndRegisterModules();
        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(new File(filename), data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // expose loaded data for tests
    public ArrayList<Object> getData() {
        return data;
    }

    // private ArrayList<ArrayList<Object>> loadData() throws ClassNotFoundException
    // {
    // data = new ArrayList<>();
    // String line = reader.nextLine();
    // String[] values = line.split("\\s*,\\s*");
    // String className = values[0].trim();
    // Constructor<?> constructor = getLargestConstructor(className);
    // String[] paramTypes = parseFormat.get(className);
    // while (reader.hasNextLine()) {
    // }
    // return data;
    // }

    private Constructor<?> getLargestConstructor(String className) throws ClassNotFoundException {
        Class<?> cls = Class.forName(className);
        Constructor<?>[] constructors = cls.getConstructors();
        Constructor<?> largestConstructor = null;
        for (Constructor<?> constructor : constructors) {
            if (largestConstructor == null
                    || constructor.getParameterCount() > largestConstructor.getParameterCount()) {
                largestConstructor = constructor;
            }
        }
        return largestConstructor;
    }

    public static String formatDate(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return date.format(formatter);
    }

    public static LocalDate parseDate(String dateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return LocalDate.parse(dateString, formatter);
    }

}