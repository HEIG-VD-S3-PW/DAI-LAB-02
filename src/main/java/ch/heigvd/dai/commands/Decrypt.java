package ch.heigvd.dai.commands;

import ch.heigvd.dai.algorithm.Algorithm;
import ch.heigvd.dai.file.FileManager;
import picocli.CommandLine;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.Callable;

/**
 * Decrypt the content of a file using the file extension as the algorithm.
 */
@CommandLine.Command(
        name = "decrypt",
        aliases = {"dec", "d"},
        description = "Decrypt a file using the algorithm indicated by its file extension.",
        mixinStandardHelpOptions = true,
        descriptionHeading = "%nUsage:%n  ",
        optionListHeading = "%nOptions:%n",
        footer = "%nPassphrase is required for decryption.")
public class Decrypt implements Callable<Integer> {

    @CommandLine.ParentCommand
    protected Root root;

    /**
     * Decrypt the content of a file using the algorithm inferred from the file extension.
     */
    @Override
    public Integer call() {
        String inputFileName = root.getFilename();

        // Extract the file extension to determine the algorithm
        int dotIndex = inputFileName.lastIndexOf('.');
        if (dotIndex == -1 || dotIndex == inputFileName.length() - 1) {
            System.err.println("Unable to determine algorithm from file extension. No extension found.");
            return 1;
        }

        String extension = inputFileName.substring(dotIndex + 1);

        String outputFileName = inputFileName.substring(0, dotIndex).substring(inputFileName.substring(0, dotIndex).lastIndexOf('/') + 1);

        if(root.getOutputPath() != null) {
            if (!FileManager.isPathValid(root.getOutputPath())) {
                System.err.println("The output path does not exist.");
                return 1;
            }else {
                outputFileName = root.getOutputPath() + "/" + outputFileName;
            }
        }

        // Map the extension to the corresponding algorithm
        Algorithm algorithm;
        try {
            algorithm = Algorithm.valueOf(extension.toUpperCase());
        } catch (IllegalArgumentException e) {
            System.err.println("Unknown algorithm extension: " + extension);
            return 1;
        }

        FileManager fileManager = new FileManager(inputFileName, outputFileName);

        try {
            fileManager.read();
        } catch (IOException e) {
            System.err.println("Error reading encrypted file: " + e.getMessage());
            return 1;
        }

        String passphrase = root.getPassphrase();

        // Prompt the user for the passphrase if not provided
        while (passphrase== null || passphrase.isEmpty()) {
            BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
            try {
                System.out.print("Enter the passphrase to decrypt the file: ");
                passphrase = bufferRead.readLine();
            } catch (IOException e) {
                System.err.println("Error reading passphrase: " + e.getMessage());
                return 1;
            }
        }

        try {
            byte[] decryptedData = algorithm.decrypt(fileManager.getData(), passphrase);

            fileManager.write(decryptedData);

            fileManager.deleteInputFile();

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
