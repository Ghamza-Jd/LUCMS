package utils;

public final class Cards {
    private static final String INFO_COLOR = "#1E90FF";
    private static final String IMPORTANT_COLOR = "#FF7B28";
    private static final String URGENT_COLOR = "#FF3B3B";

    public static String getColor(String priority) {
        String level = priority.toLowerCase();
        if(level.equals("info"))        return INFO_COLOR;
        if(level.equals("important"))   return IMPORTANT_COLOR;
        if(level.equals("urgent"))      return URGENT_COLOR;
        return "#FFF";
    }
}
