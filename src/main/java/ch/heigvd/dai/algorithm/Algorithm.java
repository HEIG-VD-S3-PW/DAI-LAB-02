package ch.heigvd.dai.algorithm;


/**
 * Abstract class to store the algorithm's data and define encryption/decryption methods.
 */
public abstract class Algorithm {

    // Make fields private and provide access via getters
    private String name;
    private String description;

    // Protected constructor to allow subclass access
    protected Algorithm(String name, String description) {
        this.name = name;
        this.description = description;
    }

    // Getters for encapsulation
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    // Abstract methods for encryption and decryption
    public abstract byte[] encrypt(byte[] bytesToEncrypt, String key);

    public abstract byte[] decrypt(byte[] bytesToDecrypt, String key);
}
