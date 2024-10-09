package ch.heigvd.dai.algorithm;

import java.util.Random;

public class RandomPassphraseGenerator {
    public static String  generator(){
        Random r = new Random();
        final int firstChar = 21; // '!'
        final int lastChar = 126; // '~'
        final int passphraseLenght = 10;

        String passphrase = "";

        for(int i = 0; i < passphraseLenght; ++i){
            char a = '!';
            a += (char) r.nextInt(lastChar-firstChar);
            passphrase += a;
        }

        return passphrase;
    }
}