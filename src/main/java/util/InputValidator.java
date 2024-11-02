package util;

public class InputValidator {

    public static boolean isValidIsbn(String isbn) {
        return isbn != null && isbn.matches("\\d{3}-\\d{10}");
    }

    public static boolean isValidName(String name) {
        return name != null && !name.trim().isEmpty();
    }
}
