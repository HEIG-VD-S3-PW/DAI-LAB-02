package ch.heigvd.dai.commands;

import picocli.CommandLine;

@CommandLine.Command(
        description = "A small CLI to encrypt and decrypt files.",
        version = "1.0.0",
        subcommands = {},
        scope = CommandLine.ScopeType.INHERIT,
        mixinStandardHelpOptions = true)
public class Root {

    @CommandLine.Parameters(index = "0", description = "The name of the file.")
    protected String filename;

    public String getFilename() {
        return filename;
    }


}
