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

/**
 * Encrypts the content of a file
 * @author Tristan Baud
 * @author Mathieu Emery
 */
@CommandLine.Command(
        name = "encrypt",
        aliases = {"enc", "e"},
        description = "Encrypt a file with the specified algorithm and passphrase.",
        mixinStandardHelpOptions = true,
        descriptionHeading = "%nUsage:%n  ", // Custom heading
        optionListHeading = "%nOptions:%n",  // Custom heading for options
        footer = "%nPassphrase will be randomly generated if not provided.")
public class Encrypt implements Callable<Integer> {
  
    @CommandLine.ParentCommand
    protected Root root;

    /**
     * Encrypt the content of a file using the correct algorithm and generates a passphrase if necessary
     */
    @Override
    public Integer call() {
        String inputFileName = root.getFilename();

        String outputFileName = inputFileName + "." + root.getAlgorithm().getName().toLowerCase();

        if(root.outputPath != null) {
            // Check if the path exist
            if (!FileManager.checkPath(root.outputPath)) {
                System.err.println("The output path does not exist.");
                return 1;
            }else {
                outputFileName = root.outputPath + "/" + outputFileName;
            }
        }

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

            System.out.println("The randomly generated passphrase has been copied to your clipboard.");

        }

        try {
            byte[] encryptedData = algorithm.encrypt(fileManager.getData(), root.passphrase);

            fileManager.write(encryptedData);

            fileManager.deleteInputFile();

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
