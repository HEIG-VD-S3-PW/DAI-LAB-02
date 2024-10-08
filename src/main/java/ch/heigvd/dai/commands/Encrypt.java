package ch.heigvd.dai.commands;

import ch.heigvd.dai.algorithm.AES;
import ch.heigvd.dai.algorithm.Algorithm;
import ch.heigvd.dai.file.FileManager;
import ch.heigvd.dai.algorithm.RandomPassphraseGenerator;
import picocli.CommandLine;

import java.util.concurrent.Callable;

@CommandLine.Command(
        name = "encrypt",
        aliases = {"enc", "e"},
        description = "Encrypt a file.",
        mixinStandardHelpOptions = true)
public class Encrypt implements Callable<Integer> {
    @CommandLine.ParentCommand protected Root root;

    @Override
    public Integer call() {

        FileManager fileManager = new FileManager(root.getFilename(), root.getFilename() + ".encrypted");
        fileManager.read();

        Algorithm algorithm = root.getAlgorithm();
        System.out.println("Randomly generated passphrase: " + RandomPassphraseGenerator.generator());
        fileManager.write(algorithm.encrypt(fileManager.getData(), "key"));

        return 0;
    }
}
