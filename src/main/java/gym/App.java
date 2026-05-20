package gym;

import java.util.Scanner;
import java.util.ArrayList;

public class App {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        try {
            // init
            DataManager dataManager = new DataManager("gym.json");
            ArrayList<Object> users = new ArrayList<>(0),
                    members = new ArrayList<>(0),
                    coaches = new ArrayList<>(0);
            Gym gym = null;
            try {
                gym = (Gym) dataManager.loadData().get(0);
            } catch (Exception e) {
                System.out.println("CRITICAL ERROR: Failed to load gym data.");
            }

            User user = new User();
            try {
                System.out.println("Welcome to your gym management app! Type 'exit' to exit.");
                dataManager.changeFile("users.json");
                users = dataManager.loadData();
                dataManager.changeFile("customers.json");
                members = dataManager.loadData();
                dataManager.changeFile("coaches.json");
                coaches = dataManager.loadData();
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
            switch (user.getType()) {
                case ADMIN: {
                    System.out.println("You're signed in as an admin, here are some available commands:");
                    // implementation
                    break;
                }
                case MEMBER: {
                    Customer member = null;
                    for (Object entry : members) {
                        if (user.getId() == ((Customer) entry).getId()) {
                            member = (Customer) entry;
                            break;
                        }
                    }
                    if (member == null) {
                        while (true) {
                            System.out.println("New member detected, please enter a few details:");
                            System.out.print("Name: ");
                            String name = in.nextLine().trim();
                            System.out.print("\nGender: ");
                            String genderStr = in.nextLine().trim();
                            Gender gender = null;
                            switch (genderStr.toLowerCase()) {
                                case "male": {
                                    gender = Gender.MALE;
                                    break;
                                }
                                case "female": {
                                    gender = Gender.FEMALE;
                                }
                            }
                            System.out.print("\nAddress: ");
                            String address = in.nextLine().trim();
                            System.out.print("\nPhone Number: ");
                            String phoneNumber = in.nextLine().trim();
                            System.out.print("\nEmail: ");
                            String email = in.nextLine().trim();
                            try {
                                member = new Customer(0, name, gender,
                                        address, phoneNumber, email,
                                        null, null);
                            } catch (Exception e) {
                                System.out.println("Error creating member: " + e.getMessage());
                            }
                        }
                    }
                    System.out.println("You're signed in as a member, here are some available commands:");
                    System.out.println("cinfo: display coach info.\neqdisplay: display gym equipment.");
                    System.out.println("plan: display your membership plan details.");
                    System.out.println("inbody: display inbody information.\nexit: exit the program.");
                    String input = in.nextLine().toLowerCase().trim();
                    while (input != "exit") {
                        switch (input) {
                            case "cinfo": {
                                for (Object entry : coaches) {
                                    if (((Coach) entry).getId() == member.getSubscription().getCoachId()) {
                                        System.out.println((Coach) entry);
                                        break;
                                    }
                                }
                                break;
                            }
                            case "eqdisplay": {
                                System.out.println(gym);
                                break;
                            }
                            case "plan": {
                                System.out.println(member.getSubscription().getMembershipPlan());
                                break;
                            }
                            case "inbody": {
                                for (InBody inbody : member.getInBodyRecords()) {
                                    System.out.println(inbody);
                                }
                                break;
                            }
                        }
                        input = in.nextLine().toLowerCase().trim();
                    }
                    break;
                }

                case COACH: {
                    Coach coach = null;
                    for (Object entry : coaches) {
                        if (user.getId() == ((Coach) entry).getId()) {
                            coach = (Coach) entry;
                            break;
                        }
                    }
                    if (coach == null) {
                        while (true) {
                            System.out.println("New coach detected, please enter a few details:");
                            System.out.print("Name: ");
                            String name = in.nextLine().trim();
                            System.out.print("\nGender: ");
                            String genderStr = in.nextLine().trim();
                            Gender gender = null;
                            switch (genderStr.toLowerCase()) {
                                case "male": {
                                    gender = Gender.MALE;
                                    break;
                                }
                                case "female": {
                                    gender = Gender.FEMALE;
                                }
                            }
                            System.out.print("\nAddress: ");
                            String address = in.nextLine().trim();
                            System.out.print("\nAddress: ");
                            String phoneNumber = in.nextLine().trim();
                            System.out.print("\nAddress: ");
                            String email = in.nextLine().trim();
                            try {
                                coach = new Coach(0, name, gender,
                                        address, phoneNumber, email, 0);
                            } catch (Exception e) {
                                System.out.println("Error creating member: " + e.getMessage());
                            }
                        }
                    }
                    System.out.println("You're signed in as a coach, here are some available commands:");
                    System.out.println("cshow: show a list of all your customers.");
                    System.out.println("inbody: get the inbody history of a customer, usage: inbody <id>.");
                    System.out.println("cinfo: get info about a customer, usage: cinfo <id>.");
                    System.out
                            .println("cfilter: show a list of customers filtered by gender, usage: cfilder <gender>.");
                    System.out.println("exit: exit the program.");
                    String input = in.next().toLowerCase().trim();
                    while (input != "exit") {
                        switch (input) {
                            case "cinfo": {
                                try {
                                    int id = in.nextInt();
                                    for (Object entry : members) {
                                        if (((Customer) entry).getId() == id) {
                                            System.out.println((Customer) entry);
                                            break;
                                        }
                                    }
                                } catch (Exception e) {
                                    System.out.println(e.getMessage());
                                }
                                break;
                            }
                            case "cshow": {
                                for (Object entry : members) {
                                    if (((Customer) entry).getSubscription().getCoachId() == coach.getId()) {
                                        System.out.println((Customer) entry);
                                        break;
                                    }
                                }
                                break;
                            }
                            case "cfilter": {
                                try {
                                    String genderStr = in.nextLine().toLowerCase().trim();
                                    Gender gender = null;
                                    switch (genderStr) {
                                        case "male": {
                                            gender = Gender.MALE;
                                            break;
                                        }
                                        case "female": {
                                            gender = Gender.FEMALE;
                                        }
                                    }
                                    for (Object entry : members) {
                                        if (((Customer) entry).getGender() == gender) {
                                            System.out.println((Customer) entry);
                                            break;
                                        }
                                    }
                                } catch (Exception e) {
                                    System.out.println(e.getMessage());
                                }
                                break;
                            }
                            case "inbody": {
                                try {
                                    int id = in.nextInt();
                                    for (Object entry : members) {
                                        if (((Customer) entry).getId() == id) {
                                            for (InBody inbody : ((Customer) entry).getInBodyRecords()) {
                                                System.out.println(inbody);
                                            }
                                            break;
                                        }
                                    }
                                } catch (Exception e) {
                                    System.out.println(e.getMessage());
                                }
                                break;
                            }
                        }
                        input = in.next().toLowerCase().trim();
                    }
                    break;
                }
            }
            try {
                dataManager.changeFile("users.json");
                dataManager.saveData(users);
                dataManager.changeFile("customers.json");
                dataManager.saveData(members);
                dataManager.changeFile("coaches.json");
                dataManager.saveData(coaches);
                dataManager.changeFile("gym.json");
                ArrayList<Object> gymArr = new ArrayList<>(0);
                gymArr.add(gym);
                dataManager.saveData(gymArr);
            } catch (Exception e) {
                System.out.println("Failed to save because: " + e.getMessage());
            }
        } finally {
            in.close();
        }
    }
}