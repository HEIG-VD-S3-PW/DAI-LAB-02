package ch.heigvd.dai.commands;

import picocli.CommandLine;

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

        System.out.println("Decrypting file...");

        return 0;
    }
}
