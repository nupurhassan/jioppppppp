package memoranda.auth;

import org.mindrot.jbcrypt.BCrypt;
import java.util.HashMap;
import java.util.Map;

public class UserManager {
    private static Map<String, User> users = new HashMap<>();

    public static void addUser(String username, String password) {
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
        users.put(username, new User(username, hashedPassword));
    }

    //Added method to provide functionality to change username
    public static boolean updateUsername(String oldUsername, String newUsername) {
        User oldUser = users.get(oldUsername);

        if(oldUser == null) {
            return false;
        }

        if(users.containsKey(newUsername)) {
            return false;
        }

        User newUser = new User(newUsername, oldUser.getPasswordHash());
        users.put(newUsername, newUser);
        users.remove(oldUsername);

        return true;
    }

    //Added method to provide functionality to change password
    public static void updatePassword(String username, User newUserPassword) {
        users.replace(username, newUserPassword);
    }

    public static User getUserByUsername(String username) {
        return users.get(username);
    }
} 