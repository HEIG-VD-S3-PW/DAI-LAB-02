package ch.heigvd.dai.commands;

import picocli.CommandLine;

import ch.heigvd.dai.algorithm.AES;
import ch.heigvd.dai.algorithm.Algorithm;
import ch.heigvd.dai.file.FileManager;
import picocli.CommandLine;

import java.io.File;
import java.util.Objects;
import java.util.concurrent.Callable;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;

@CommandLine.Command(
        name = "decrypt",
        aliases = {"dec", "d"},
        description = "Decrypt a file using the specified algorithm and passphrase.",
        mixinStandardHelpOptions = true,
        descriptionHeading = "%nUsage:%n  ",
        optionListHeading = "%nOptions:%n",
        footer = "%nPassphrase is required for decryption.")
public class Decrypt implements Callable<Integer> {
    @CommandLine.ParentCommand protected Root root;


    @Override
    public Integer call() {

        FileManager fileManager = new FileManager(root.getFilename(), root.getFilename().replaceAll(".encrypted", ""));

        fileManager.read();

        Algorithm algorithm = root.getAlgorithm();

        while(Objects.isNull(root.passphrase)){
            System.out.print("Enter the passphrase to decrypt the file: ");

            try {
                BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
                root.passphrase = bufferRead.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        fileManager.write(algorithm.decrypt(fileManager.getData(), root.passphrase));

        System.out.println("File decrypted successfully using " + algorithm.name +".");

        return 0;
    }
}
