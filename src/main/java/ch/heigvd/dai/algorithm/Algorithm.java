package ch.heigvd.dai.algorithm;


import java.lang.reflect.InvocationTargetException;

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

    // Static method to get the algorithm from the user input or file extension
    public static Algorithm valueOf(String value) {
        try {
            String className = "ch.heigvd.dai.algorithm." + value.toUpperCase();
            return (Algorithm) Class.forName(className).getDeclaredConstructor().newInstance();
        } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException |
                 InvocationTargetException e) {
            throw new IllegalArgumentException("Invalid algorithm: " + value);
        }
    }
}
