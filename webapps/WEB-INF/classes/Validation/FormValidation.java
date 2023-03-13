package Validation;

public class FormValidation {
    public static boolean inputExist(String input) {
        return input != null && !input.isEmpty();
    }

    public static boolean emailIsValid(String email) {
        return email.matches("^[A-Za-z0-9+_.-]+@(.+)$");
    }

    public static boolean passwordIsValid(String password) {
        return password.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$");
    }
}
