package ch.heigvd.dai.commands;

import ch.heigvd.dai.algorithm.Algorithm;
import picocli.CommandLine;

@CommandLine.Command(
        description = "A small CLI to encrypt and decrypt files.",
        version = "1.0.0",
        subcommands = {
                Encrypt.class,
                Decrypt.class
        },
        scope = CommandLine.ScopeType.INHERIT,
        mixinStandardHelpOptions = true)
public class Root {

    @CommandLine.Parameters(index = "0", description = "The name of the file.")
    protected String filename;

    @CommandLine.Option(
            names = {"-a", "--algorithm"},
            description = "The algorithm to use (possible values: ${COMPLETION-CANDIDATES}).",
            required = true)
    protected Algorithm algorithm;


    public String getFilename() {
        return filename;
    }

    public Algorithm getAlgorithm() {
        return algorithm;
    }


}
