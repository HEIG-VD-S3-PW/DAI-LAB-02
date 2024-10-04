package ch.heigvd.dai.commands.converter;

import ch.heigvd.dai.algorithm.AES;
import ch.heigvd.dai.algorithm.Algorithm;
import picocli.CommandLine.ITypeConverter;

public class IAlgorithmConverter implements ITypeConverter<Algorithm> {
    public Algorithm convert(String value) throws Exception {
        if (value.equals("AES")) {
            return new AES();
        }
        throw new Exception("Unknown algorithm");
    }
}
