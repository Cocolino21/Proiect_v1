package Controller;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
public class HashIt {

    public static String hashThePass(String pass) {
        try {
            // Create MessageDigest instance for MD5 hashing
            MessageDigest md = MessageDigest.getInstance("MD5");

            // Add password bytes to digest
            md.update(pass.getBytes());

            // Get the hash's bytes
            byte[] bytes = md.digest();

            // Convert bytes to hexadecimal format
            StringBuilder sb = new StringBuilder();
            for (byte aByte : bytes) {
                sb.append(Integer.toString((aByte & 0xff) + 0x100, 16).substring(1));
            }

            // Return the hashed password
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            // Handle NoSuchAlgorithmException (if necessary)
        }
        return null;
    }


}
