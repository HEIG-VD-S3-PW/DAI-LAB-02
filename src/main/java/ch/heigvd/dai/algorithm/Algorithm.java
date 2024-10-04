package ch.heigvd.dai.algorithm;

enum STRENGTH {
    LOW,
    MEDIUM,
    HIGH
}

public abstract class Algorithm {

    public String name;
    public String description;
    public STRENGTH strength;


    public abstract byte[] encrypt(byte[] bytesToEncrypt, String key);

    public abstract byte[] decrypt(byte[] bytesToDecrypt, String key);
}
