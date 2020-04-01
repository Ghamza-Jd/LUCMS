package services;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class Validator {
    public static boolean validateUsername(String username) {
        final String regex = "^[a-z0-9_-]{3,15}$";
        final Pattern pattern = Pattern.compile(regex);
        final Matcher matcher = pattern.matcher(username);
        return matcher.find();
    }
    public static boolean validatePassword(String password) {
        final String regex = "^[a-zA-Z@#$%^&+=](?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).{8,}[a-zA-Z0-9]$";
        final Pattern pattern = Pattern.compile(regex);
        final Matcher matcher = pattern.matcher(password);
        return matcher.find();
    }
    public static boolean validateDate(String date) {
        final String regex = "^([0-2][0-9]|(3)[0-1])(-)(((0)[0-9])|((1)[0-2]))(-)\\d{4}$";
        final Pattern pattern = Pattern.compile(regex);
        final Matcher matcher = pattern.matcher(date);
        return matcher.find();
    }
}
