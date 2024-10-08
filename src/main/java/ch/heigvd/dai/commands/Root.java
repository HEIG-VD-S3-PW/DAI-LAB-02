package ch.heigvd.dai.commands;

import ch.heigvd.dai.algorithm.Algorithm;
import ch.heigvd.dai.commands.converter.IAlgorithmConverter;
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
            description = "The algorithm to use (possible values: ${COMPLETION-CANDIDATES}, default value: AES).",
            required = false,
            converter = IAlgorithmConverter.class)
    protected Algorithm algorithm;

    @CommandLine.Option(
            names = {"-p", "--passphrase"},
            description = "The passphrase to use (Will be randomly generated if left empty).",
            required = false)
    protected String passphrase;


    public String getFilename() {
        return filename;
    }

    public Algorithm getAlgorithm() {
        return algorithm;
    }


}
