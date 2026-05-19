package gym;

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

    public User() {
    }

    @JsonCreator
    public User(@JsonProperty("type") UserType type,
                @JsonProperty("username") String username,
                @JsonProperty("password") String password) {
        login(type, username, password);
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

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void login(UserType type, String username, String password) {
        setType(type);
        setUsername(username);
        setPassword(password);
    }

    public void logout() {
        setType(null);
        setUsername(null);
        setPassword(null);
    }

    public void register(UserType type, String username, String password) {
        setType(type);
        setUsername(username);
        setPassword(password);
    }
}
