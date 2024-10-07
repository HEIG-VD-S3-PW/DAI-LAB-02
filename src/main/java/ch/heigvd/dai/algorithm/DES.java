package ch.heigvd.dai.algorithm;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

/*
 * DES is a symmetric encryption algorithm.
 * Sources used: https://stackoverflow.com/questions/72351900/encryption-with-des-in-java
 */

public class DES extends Algorithm {
    public DES() {
        this.name = "DES";
        this.description = "DES is a symmetric encryption algorithm.";
        this.strength = STRENGTH.MEDIUM;
    }

    // Method to convert the key string to a valid 8-byte DES key
    private static SecretKeySpec getKey(String key) throws Exception {
        // DES requires an 8-byte key, so we ensure that the key is exactly 8 bytes
        byte[] keyBytes = key.getBytes();
        byte[] desKeyBytes = new byte[8];
        System.arraycopy(keyBytes, 0, desKeyBytes, 0, Math.min(keyBytes.length, 8));
        return new SecretKeySpec(desKeyBytes, "DES");
    }

    // Method to encrypt data using DES
    @Override
    public byte[] encrypt(byte[] bytesToEncrypt, String key) {
        try {
            SecretKeySpec secretKey = getKey(key); // Generate the DES key from the string
            Cipher cipher = Cipher.getInstance("DES");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            return cipher.doFinal(bytesToEncrypt); // Return encrypted byte array
        }
        catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Method to decrypt data using DES
    @Override
    public byte[] decrypt(byte[] bytesToDecrypt, String key) {
        try {
            SecretKeySpec secretKey = getKey(key); // Generate the DES key from the string
            Cipher cipher = Cipher.getInstance("DES");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            return cipher.doFinal(bytesToDecrypt); // Return decrypted byte array
        }
        catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
