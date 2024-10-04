package ch.heigvd.dai.commands;

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
        System.out.println("Encrypting file...");
        return 0;
    }
}
