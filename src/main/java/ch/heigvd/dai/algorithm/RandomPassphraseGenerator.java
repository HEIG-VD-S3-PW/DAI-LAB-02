package ch.heigvd.dai.algorithm;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.util.Random;

/**
 * Generates a random passphrase to encrypt the content of a file
 */
public class RandomPassphraseGenerator {
    /**
     * Creates a random passphrase of size N with all ASCII printable character
     */
    public static String  generator(){
        Random r = new Random();
        final int firstChar = 33; // '!'
        final int lastChar = 126; // '~'
        final int passphraseLenght = 10;

        String passphrase = "";

        for(int i = 0; i < passphraseLenght; ++i){
            char a = firstChar;
            a += (char) r.nextInt(lastChar-firstChar);
            passphrase += a;
        }

        return passphrase;
    }
}
