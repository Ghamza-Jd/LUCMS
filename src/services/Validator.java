package services;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {
    public static boolean validateUsername(String username) {
        String regex = "^[a-z0-9_-]{3,15}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(username);
        return matcher.find();
    }
    public static boolean validatePassword(String password){
        String regex = "^[a-zA-Z@#$%^&+=](?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).{8,}[a-zA-Z0-9]$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(password);
        return matcher.find();
    }
}
