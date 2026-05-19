package gym;

import java.util.Scanner;
import java.util.ArrayList;

public class App {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        try {
            while (true) {
                // init
                DataManager dataManager = new DataManager("users.json");
                ArrayList<Object> users = new ArrayList<>(0),
                members = new ArrayList<>(0),
                coaches = new ArrayList<>(0),
                gym = new ArrayList<>(0);

                User user = new User();
                try {
                    System.out.println("Welcome to your gym management app! Type 'exit' to exit.");
                    users = dataManager.loadData();
                    dataManager = new DataManager("customers.json");
                    members = dataManager.loadData();
                    dataManager = new DataManager("coaches.json");
                    coaches = dataManager.loadData();
                    dataManager = new DataManager("gym.json");
                } catch (Exception e) {
                    System.out.println("CRITICAL ERROR: Failed to load data.");
                }
                // user handling
                if (users.isEmpty()) {
                    System.out.println("First time use detected, please create an Admin profile;");
                    System.out.print("Please enter your username: ");
                    String username = in.nextLine();
                    while (true) {
                        try {
                            System.out.println();
                            System.out.print("Please enter a strong password: ");
                            String password = in.nextLine();
                            user.register(UserType.ADMIN, username, password);
                            break;
                        } catch (Exception e) {
                            System.out.println(e);
                        }
                    }
                    users.add(user);
                } else {
                    while (user.getUsername() == null) {
                        System.out.println("Type 'login' or 'register' to get started.");
                        String input = in.next();
                        switch (input.toLowerCase().trim()) {
                            case "login": {
                                System.out.print("Please enter your username: ");
                                String username = in.nextLine().trim();
                                System.out.println();
                                System.out.print("Please enter your password: ");
                                String password = in.nextLine().trim();
                                try {
                                    user.login(username, password);
                                } catch (Exception e) {
                                    System.out.println(e);
                                }
                            }
                            case "register": {
                                System.out.print("Please pick a user profile, type 'customer' or 'coach' to proceed: ");
                                String userTypeStr = in.nextLine().trim();
                                UserType userType = null;
                                switch (userTypeStr.toLowerCase()) {
                                    case "customer": {
                                        userType = UserType.MEMBER;
                                        break;
                                    }
                                    case "coach": {
                                        userType = UserType.COACH;
                                        break;
                                    }
                                }
                                System.out.print("Please enter your username: ");
                                String username = in.nextLine().trim();
                                System.out.println();
                                System.out.print("Please enter your password: ");
                                String password = in.nextLine().trim();
                                try {
                                    if (userType == null) {
                                        throw new Exception("Please pick a valid user profile.");
                                    }
                                    user.register(userType, username, password);
                                } catch (Exception e) {
                                    System.out.println(e);
                                }
                            }
                        }
                    }
                }
                // now we have a fully set-up user
                switch(user.getType()) {
                    case ADMIN: {
                        // some code
                        break;
                    }
                    case MEMBER: {
                        // some code
                        break;
                    }
                    case COACH: {
                        // some code
                        break;
                    }
                }

            }
        } finally {
            in.close();
        }
    }
}