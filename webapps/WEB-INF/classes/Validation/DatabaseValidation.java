package Validation;

public class DatabaseValidation {
    public static boolean usernameExists(String username) {
        return (DBservices.DatabaseOperations.getUserByUsername(username) != null);
    }

    public static boolean emailExists(String email) {
        return (DBservices.DatabaseOperations.getUserByEmail(email) != null);
    }
}
