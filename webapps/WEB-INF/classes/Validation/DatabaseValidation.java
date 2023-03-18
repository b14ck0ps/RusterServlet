package Validation;

import static DBservices.UserRepository.getUserByEmail;
import static DBservices.UserRepository.getUserByUsername;

public class DatabaseValidation {
    public static boolean usernameExists(String username) {
        return (getUserByUsername(username) != null);
    }

    public static boolean emailExists(String email) {
        return (getUserByEmail(email) != null);
    }
}
