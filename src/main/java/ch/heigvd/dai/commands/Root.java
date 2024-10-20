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
    private String filename;

    @CommandLine.Option(
            names = {"-a", "--algorithm"},
            description = "The algorithm to use (Possible values: DES, AES (Default: AES).",
            required = false,
            converter = IAlgorithmConverter.class)
    private Algorithm algorithm;

    @CommandLine.Option(
            names = {"-p", "--passphrase"},
            description = "The passphrase to use (Will be randomly generated if left empty).",
            required = false)
    private String passphrase;

    @CommandLine.Option(
            names = {"-o", "--output"},
            description = "The path of the output file. (Default: same directory as the input file).",
            required = false)
    private String outputPath;


    public String getFilename() { return filename; }

    public Algorithm getAlgorithm() { return algorithm; }

    public String getPassphrase() { return passphrase; }

    public String getOutputPath() { return outputPath; }


}
