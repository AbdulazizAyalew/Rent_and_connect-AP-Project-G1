package utils;

public class InputValidator {

    public static boolean isValidUsername(String username) {
        return username != null && !username.trim().isEmpty();
    }

    public static boolean isValidPassword(String password) {
        return password != null && password.length() >= 4;
    }

    public static boolean isValidCity(String city) {
        return city != null && !city.trim().isEmpty();
    }

    public static boolean isValidDouble(String input) {
        try {
            Double.parseDouble(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isValidInt(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
