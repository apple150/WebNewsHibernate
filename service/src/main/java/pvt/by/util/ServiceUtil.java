package pvt.by.util;

/**
 * Utilities class
 */
public class ServiceUtil {
    /**
     * Check if string is positive number
     * @param string to be tested
     * @return if string contains positive number, return it
     */
    public static int stringToPositiveInt(String string) throws NumberFormatException {
        if (string != null) {
            int num = Integer.parseInt(string);
            // Check if string is positive number
            if (num <= 0) {
                throw new NumberFormatException();
            }
            return num;
        } else {
            throw new NumberFormatException();
        }
    }
}
