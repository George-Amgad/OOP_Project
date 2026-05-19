package gym;

import java.util.ArrayList;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

enum UserType {
    COACH,
    ADMIN,
    MEMBER
}

public class User {
    private UserType type;
    private String username;
    private String password;
    private static ArrayList<Object> userDataBase;

    public static void loadUserDataBase(String filename) throws Exception {
        DataManager manager = new DataManager(filename);
        userDataBase = manager.loadData();
    }

    public User() {
    }

    @JsonCreator
    public User(@JsonProperty("type") UserType type,
            @JsonProperty("username") String username,
            @JsonProperty("password") String password) {
        register(type, username, password);
    }

    public void setType(UserType type) {
        this.type = type;
    }

    public UserType getType() {
        return type;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void changePassword(String currentPassword, String newPassword) throws Exception {
        if (!(currentPassword.equals(this.password))) {
            throw new Exception("Incorrect password, please enter your current password correctly.");
        }
        if (!verifyPassword(newPassword)) {
            throw new IllegalArgumentException(
                    "Invalid password, passwords must be at least 8 characters long, with at least one uppercase, lowercase, number and special character. And spaces are not allowed.");
        }
    }

    public boolean verifyPassword(String password) {
        if (password.contains(" ") || password.length() < 8) {
            return false;
        }
        if (password.contains("[ABCDEFGHIJKLMNOPQRSTUVWXYZ]") && password.contains("[abcdefghijklmnopqrstuvwxyz]")
                && password.contains("[0123456789]")) {
            return true;
        }
        return false;
    }

    public void login(String username, String password) {
        for (Object entry : User.userDataBase) {
            User user = (User) entry;
            if (user.username == username) {
                if (user.password == password) {
                    setType(type);
                    setUsername(username);
                    this.password = password;
                }
                else {
                    throw new IllegalArgumentException("Incorrect Password.");
                }
                break;
            }
            else {
                throw new IllegalArgumentException("User not found (incorrect username).");
            }
        }
    }

    public void logout() {
        setType(null);
        setUsername(null);
        password = null;
    }

    public void register(UserType type, String username, String password) {
        if (!verifyPassword(password)) {
            throw new IllegalArgumentException(
                    "Invalid password, passwords must be at least 8 characters long, with at least one uppercase, lowercase, number and special character. And spaces are not allowed.");
        }
        for (Object entry : User.userDataBase) {
            User user = (User) entry;
            if (user.username == username) {
                throw new IllegalArgumentException("A user with that name already exists.");
            }
        }
        setType(type);
        setUsername(username);
        this.password = password;
    }
}
