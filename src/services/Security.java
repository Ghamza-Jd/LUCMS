package services;

import config.Config;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Security {
    /**
     * @param password plain password to be hashed
     * @return digested password
     */
    public static String hash(String password){
        String digest = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            md.update(Config.PASSWORD_SALT.getBytes(StandardCharsets.UTF_8));
            byte[] bytes = md.digest(password.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for (byte aByte : bytes) {
                sb.append(Integer.toString((aByte & 0xff) + 0x100, 16).substring(1));
            }
            digest = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return digest;
    }

    /**
     * @param password plain password
     * @param digest hashed password
     * @return if the hash of the password is equal to the digest
     */
    public static boolean eqHash(String password, String digest){
        return hash(password).equals(digest);
    }
}
