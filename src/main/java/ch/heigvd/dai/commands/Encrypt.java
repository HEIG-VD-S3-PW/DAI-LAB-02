package ch.heigvd.dai.commands;

import ch.heigvd.dai.algorithm.Algorithm;
import ch.heigvd.dai.algorithm.RandomPassphraseGenerator;
import ch.heigvd.dai.file.FileManager;
import picocli.CommandLine;

import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.Toolkit;
import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.Callable;

@CommandLine.Command(
        name = "encrypt",
        aliases = {"enc", "e"},
        description = "Encrypt a file.",
        mixinStandardHelpOptions = true)
public class Encrypt implements Callable<Integer> {
    @CommandLine.ParentCommand
    protected Root root;

    @Override
    public Integer call() {
        String inputFileName = root.getFilename();
        String outputFileName = inputFileName + ".encrypted";

        FileManager fileManager = new FileManager(inputFileName, outputFileName);

        try {
            fileManager.read();
        } catch (IOException e) {
            System.err.println("Error reading input file: " + e.getMessage());
            return 1;
        }

        Algorithm algorithm = root.getAlgorithm();

        // Generate a random passphrase if not provided
        if (Objects.isNull(root.passphrase)) {
            root.passphrase = RandomPassphraseGenerator.generator();

            // Copy the passphrase to the clipboard
            StringSelection stringSelection = new StringSelection(root.passphrase);
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(stringSelection, null);

            System.out.println("Randomly generated passphrase (copied to clipboard): " + root.passphrase);
        }

        try {
            byte[] encryptedData = algorithm.encrypt(fileManager.getData(), root.passphrase);

            fileManager.write(encryptedData);

            System.out.println("Encryption successful. Encrypted file: " + outputFileName);
            return 0;
        } catch (IOException e) {
            System.err.println("Error writing encrypted file: " + e.getMessage());
            return 1;
        } catch (Exception e) {
            System.err.println("An unexpected error occurred during encryption: " + e.getMessage());
            return 1;
        }
    }
}
