package utils;

public class Cards {
    public static final String INFO_COLOR = "#1E90FF";
    public static final String IMPORTANT_COLOR = "#FF7B28";
    public static final String URGENT_COLOR = "#FF3B3B";

    public static String getColor(String level) {
        level = level.toLowerCase();
        if(level.equals("info"))        return INFO_COLOR;
        if(level.equals("important"))   return IMPORTANT_COLOR;
        if(level.equals("urgent"))      return URGENT_COLOR;
        return "#FFF";
    }
}
