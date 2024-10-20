package ch.heigvd.dai.algorithm;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Arrays;

 /** Encrypt / Decrypt the content of a file with AES
  * @author Tristan Baud
  * @author Mathieu Emery
  * @see https://medium.com/@deepak.sirohi9188/java-aes-encryption-and-decryption-1b30c9a5d900
 */

public class AES extends Algorithm {

    /**
     * Constructor that sets the private attributes of the class
     */
    public AES() {
        super("AES", "AES is a symmetric encryption algorithm.");
    }

    /**
     * Generates a SecretKeySpec for AES encryption using a given key string.
     * @param key The input string used to generate the AES key.
     * @return A SecretKeySpec object that contains the AES key derived from the input string.
     * @throws Exception If the SHA-256 algorithm is not available or there is an issue generating the key.
     */
    private SecretKeySpec generateKey(String key) throws Exception {
        MessageDigest sha = MessageDigest.getInstance("SHA-256");
        byte[] keyBytes = key.getBytes(StandardCharsets.UTF_8);
        keyBytes = sha.digest(keyBytes);
        keyBytes = Arrays.copyOf(keyBytes, 32); // 256 bits (32 bytes)
        return new SecretKeySpec(keyBytes, "AES");
    }

    /**
     * Generates a random initialization vector (IV) for AES encryption.
     * @return An IvParameterSpec object containing the generated IV.
     */
    private IvParameterSpec generateIv() {
        byte[] iv = new byte[16];
        new java.security.SecureRandom().nextBytes(iv);
        return new IvParameterSpec(iv);
    }

    /**
     * Method to encrypt data using AES
     * @param bytesToEncrypt data to encrypt
     * @param key chosen key to encrypt the data
     * @return encrypted data
    */
    @Override
    public byte[] encrypt(byte[] bytesToEncrypt, String key) {
        try {
            SecretKeySpec secretKey = generateKey(key);
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");

            IvParameterSpec iv = generateIv();
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, iv);

            byte[] encryptedBytes = cipher.doFinal(bytesToEncrypt);

            byte[] ivAndEncrypted = new byte[iv.getIV().length + encryptedBytes.length];
            System.arraycopy(iv.getIV(), 0, ivAndEncrypted, 0, iv.getIV().length);
            System.arraycopy(encryptedBytes, 0, ivAndEncrypted, iv.getIV().length, encryptedBytes.length);

            return ivAndEncrypted;

        } catch (Exception e) {
            System.err.println("An unexpected error occurred during encryption: " + e.getMessage());
            return null;
        }
    }

    /**
     * Method to decrypt data using AES
     * @param bytesToDecrypt data to decrypt
     * @param key key that was used to encrypt
     * @return decrypted data
    */
    @Override
    public byte[] decrypt(byte[] bytesToDecrypt, String key) {
        try {
            SecretKeySpec secretKey = generateKey(key);
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");

            byte[] iv = Arrays.copyOfRange(bytesToDecrypt, 0, 16); // Ensure 16 bytes are extracted
            byte[] encryptedBytes = Arrays.copyOfRange(bytesToDecrypt, 16, bytesToDecrypt.length);

            IvParameterSpec ivSpec = new IvParameterSpec(iv);
            cipher.init(Cipher.DECRYPT_MODE, secretKey, ivSpec);

            return cipher.doFinal(encryptedBytes);

        } catch (BadPaddingException e){
            System.err.println("Invalid key or corrupted data.");
            return null;

        } catch (Exception e) {
            System.err.println("An unexpected error occurred during decryption: " + e.getMessage());
            return null;
        }
    }
}
