package utils;

public class Cards {
    public static final String INFO_COLOR = "#1E90FF";
    public static final String WARNING_COLOR = "#FF7B28";
    public static final String DANGER_COLOR = "#FF3B3B";

    public static String getColor(String level) {
        level = level.toLowerCase();
        if(level.equals("info"))    return INFO_COLOR;
        if(level.equals("warning")) return WARNING_COLOR;
        if(level.equals("danger"))  return DANGER_COLOR;
        return "#000";
    }
}
