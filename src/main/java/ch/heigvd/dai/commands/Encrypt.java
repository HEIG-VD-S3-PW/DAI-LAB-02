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

public class Encrypt implements Callable<Integer> {
    @CommandLine.ParentCommand protected Root root;

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

            System.out.println("Randomly generated passphrase (Already copied to clipboard): " + root.passphrase);
        }

        fileManager.write(algorithm.encrypt(fileManager.getData(), root.passphrase));

        return 0;
    }
}
