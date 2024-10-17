package ch.heigvd.dai.commands;

import ch.heigvd.dai.algorithm.AES;
import ch.heigvd.dai.algorithm.Algorithm;
import ch.heigvd.dai.file.FileManager;
import ch.heigvd.dai.algorithm.RandomPassphraseGenerator;
import picocli.CommandLine;

import java.util.Objects;
import java.util.concurrent.Callable;

import java.awt.datatransfer.StringSelection;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;

@CommandLine.Command(
        name = "encrypt",
        aliases = {"enc", "e"},
        description = "Encrypt a file with the specified algorithm and passphrase.",
        mixinStandardHelpOptions = true,
        descriptionHeading = "%nUsage:%n  ", // Custom heading
        optionListHeading = "%nOptions:%n",  // Custom heading for options
        footer = "%nPassphrase will be randomly generated if not provided.")

/** Encrypts the content of a file
 * @author Tristan Baud
 * @author Mathieu Emery
*/
public class Encrypt implements Callable<Integer> {
    @CommandLine.ParentCommand protected Root root;

    /**
     * Encrypt the content of a file using the correct algorithm and generates a passphrase if necessary
     */
    @Override
    public Integer call() {

        FileManager fileManager = new FileManager(root.getFilename(), root.getFilename() + ".encrypted");
        fileManager.read();

        Algorithm algorithm = root.getAlgorithm();

        if(Objects.isNull(root.passphrase)){
            root.passphrase = RandomPassphraseGenerator.generator();

            String myString = root.passphrase;
            StringSelection stringSelection = new StringSelection(myString);
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(stringSelection, null);

            System.out.println("The randomly generated passphrase has been copied to your clipboard.");
        }

        fileManager.write(algorithm.encrypt(fileManager.getData(), root.passphrase));
        System.out.println("File encrypted successfully using " + algorithm.name +".");
        return 0;
    }
}
