package ch.heigvd.dai.commands;

import ch.heigvd.dai.algorithm.Algorithm;
import ch.heigvd.dai.commands.converter.IAlgorithmConverter;
import picocli.CommandLine;

/**
 * Manage the commands and parameters of the application
 * @author Tristan Baud
 * @author Mathieu Emery
 */
@CommandLine.Command(
        mixinStandardHelpOptions = true,
        name = "Ammar",
        version = "1.0.0",
        description = "A command-line utility to encrypt and decrypt files using AES or DES encryption algorithms.",
        headerHeading = "\n======= File Encryption CLI =======\n\n",  // Ensures header separation
        header = "Encrypt and decrypt files securely using AES or DES.",
        footer = "\nCredits: Tristan Baud and Mathieu Emery",
        synopsisHeading = "\nUsage: ",
        descriptionHeading = "\nDescription:\n",
        parameterListHeading = "\nArguments:\n",
        optionListHeading = "\nOptions:\n",
        commandListHeading = "\nCommands:\n",
        subcommands = {
                Encrypt.class,  // Subcommand for encryption
                Decrypt.class   // Subcommand for decryption
        })
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
