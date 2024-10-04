package ch.heigvd.dai.commands;

import picocli.CommandLine;

import ch.heigvd.dai.algorithm.AES;
import ch.heigvd.dai.algorithm.Algorithm;
import ch.heigvd.dai.file.FileManager;
import picocli.CommandLine;

import java.io.File;
import java.util.concurrent.Callable;

@CommandLine.Command(
        name = "decrypt",
        aliases = {"dec", "d"},
        description = "Decrypt a file.",
        mixinStandardHelpOptions = true)
public class Decrypt implements Callable<Integer> {
    @CommandLine.ParentCommand protected Root root;


    @Override
    public Integer call() {

        FileManager fileManager = new FileManager(root.getFilename(), root.getFilename().replaceAll(".encrypted", ""));

        fileManager.read();

        Algorithm algorithm = root.getAlgorithm();

        fileManager.write(algorithm.decrypt(fileManager.getData(), "key"));


        return 0;
    }
}
