import java.util.HashMap;
import java.util.Map;

public class UserManager {
    private Map<String, User> users = new HashMap<>();

    public void addUser(User user) {
        users.put(user.getUsername(), user);
    }

    public User authenticate(String username, String password) {
        User user = users.get(username);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }

    public void listUsers() {
        users.values().forEach(user -> System.out.println("Username: " + user.getUsername() + ", Role: " + user.getRole()));
    }
}
