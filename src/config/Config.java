package config;

public final class Config {
    public static final String DATABASE_ENGINE = "mysql";
    public static final String DATABASE_NAME = "lums";
    public static final String DATABASE_USERNAME = "dbuser";
    public static final String DATABASE_PASSWORD = "P@ssw0rd";
    public static final String PORT = "3306";
    public static final String DATABASE_URL = "localhost:" + PORT;
    public static final String PASSWORD_SALT = "salt";
    public static final String DATABASE_CONFIG =
            "useUnicode=true" +
            "&useJDBCCompliantTimezoneShift=true" +
            "&useLegacyDatetimeCode=false" +
            "&serverTimezone=UTC"
    ;
}
