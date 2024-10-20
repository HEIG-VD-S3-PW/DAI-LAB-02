package ch.heigvd.dai.algorithm;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

 /** Encrypt / Decrypt the content of a file with DES
  * @author Tristan Baud
  * @author Mathieu Emery
  * @see https://stackoverflow.com/questions/72351900/encryption-with-des-in-java
 */

public class DES extends Algorithm {
    /**
     * Constructor that sets the private attributes of the class
     */
    public DES() {
        super("DES", "DES is a symmetric encryption algorithm.");
    }

    /**
     * Generates a SecretKeySpec for DES encryption using a given key string.
     * @param key The input string used to generate the DES key.
     * @return A SecretKeySpec object containing the DES key derived from the input string.
     * @throws Exception If there is an issue generating the key.
     */
    private static SecretKeySpec getKey(String key) throws Exception {
        // DES requires an 8-byte key, so we ensure that the key is exactly 8 bytes
        byte[] keyBytes = key.getBytes();
        byte[] desKeyBytes = new byte[8];
        System.arraycopy(keyBytes, 0, desKeyBytes, 0, Math.min(keyBytes.length, 8));
        return new SecretKeySpec(desKeyBytes, "DES");
    }

    /**
     * Method to encrypt data using DES
     * @param bytesToEncrypt data to encrypt
     * @param key chosen key to encrypt the data
     * @return encrypted data
     */
    @Override
    public byte[] encrypt(byte[] bytesToEncrypt, String key) {
        try {
            SecretKeySpec secretKey = getKey(key); // Generate the DES key from the string
            Cipher cipher = Cipher.getInstance("DES");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            return cipher.doFinal(bytesToEncrypt); // Return encrypted byte array
        } catch (Exception e) {
            System.err.println("An unexpected error occurred during encryption: " + e.getMessage());
            return null;
        }
    }

    /**
     * Method to decrypt data using DES
     * @param bytesToDecrypt data to decrypt
     * @param key key that was used to encrypt
     * @return decrypted data
     */
    @Override
    public byte[] decrypt(byte[] bytesToDecrypt, String key) {
        try {
            SecretKeySpec secretKey = getKey(key); // Generate the DES key from the string
            Cipher cipher = Cipher.getInstance("DES");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            return cipher.doFinal(bytesToDecrypt); // Return decrypted byte array

        }catch (BadPaddingException e){
            System.err.println("Invalid key or corrupted data.");
            return null;

        } catch (Exception e) {
            System.err.println("An unexpected error occurred during decryption: " + e.getMessage());
            return null;
        }
    }

}
