package ch.heigvd.dai.algorithm;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Arrays;

/*
    * AES is a symmetric encryption algorithm.
    * Sources used: https://medium.com/@deepak.sirohi9188/java-aes-encryption-and-decryption-1b30c9a5d900
 */

public class AES extends Algorithm {

    public AES() {
        this.name = "AES";
        this.description = "AES is a symmetric encryption algorithm.";
        this.strength = STRENGTH.HIGH;
    }

    private SecretKeySpec generateKey(String key) throws Exception {
        MessageDigest sha = MessageDigest.getInstance("SHA-256");
        byte[] keyBytes = key.getBytes(StandardCharsets.UTF_8);
        keyBytes = sha.digest(keyBytes);
        keyBytes = Arrays.copyOf(keyBytes, 32); // 256 bits (32 bytes)
        return new SecretKeySpec(keyBytes, "AES");
    }

    private IvParameterSpec generateIv() {
        byte[] iv = new byte[32]; // 32 bytes = 256 bits for AES
        new java.security.SecureRandom().nextBytes(iv);
        return new IvParameterSpec(iv);
    }

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
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public byte[] decrypt(byte[] bytesToDecrypt, String key) {
        try {
            SecretKeySpec secretKey = generateKey(key);
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");

            byte[] iv = Arrays.copyOfRange(bytesToDecrypt, 0, 16);
            byte[] encryptedBytes = Arrays.copyOfRange(bytesToDecrypt, 16, bytesToDecrypt.length);

            IvParameterSpec ivSpec = new IvParameterSpec(iv);
            cipher.init(Cipher.DECRYPT_MODE, secretKey, ivSpec);

            return cipher.doFinal(encryptedBytes);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
