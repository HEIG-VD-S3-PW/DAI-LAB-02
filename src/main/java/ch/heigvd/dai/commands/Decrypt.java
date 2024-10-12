package ch.heigvd.dai.commands;

import ch.heigvd.dai.algorithm.Algorithm;
import ch.heigvd.dai.file.FileManager;
import picocli.CommandLine;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;
import java.util.concurrent.Callable;

@CommandLine.Command(
        name = "decrypt",
        aliases = {"dec", "d"},
        description = "Decrypt a file using the specified algorithm and passphrase.",
        mixinStandardHelpOptions = true,
        descriptionHeading = "%nUsage:%n  ",
        optionListHeading = "%nOptions:%n",
        footer = "%nPassphrase is required for decryption.")
public class Decrypt implements Callable<Integer> {
    @CommandLine.ParentCommand
    protected Root root;

    @Override
    public Integer call() {
        String inputFileName = root.getFilename();

        if (!inputFileName.endsWith(".encrypted")) {
            System.err.println("Input file must end with '.encrypted'.");
            return 1;
        }

        String outputFileName = inputFileName.replace(".encrypted", "");

        FileManager fileManager = new FileManager(inputFileName, outputFileName);

        try {
            fileManager.read();
        } catch (IOException e) {
            System.err.println("Error reading encrypted file: " + e.getMessage());
            return 1;
        }

        Algorithm algorithm = root.getAlgorithm();

        // Prompt the user for the passphrase if not provided
        while (root.passphrase == null || root.passphrase.isEmpty()) {
            BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
            try {
                System.out.print("Enter the passphrase to decrypt the file: ");
                root.passphrase = bufferRead.readLine();
            } catch (IOException e) {
                System.err.println("Error reading passphrase: " + e.getMessage());
                return 1;
            }
        }

        try {
            byte[] decryptedData = algorithm.decrypt(fileManager.getData(), root.passphrase);

            fileManager.write(decryptedData);

            System.out.println("Decryption successful. Decrypted file: " + outputFileName);
            return 0;
        } catch (IOException e) {
            System.err.println("Error writing decrypted file: " + e.getMessage());
            return 1;
        } catch (Exception e) {
            System.err.println("An unexpected error occurred during decryption: " + e.getMessage());
            return 1;
        }
    }
}
