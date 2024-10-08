package ch.heigvd.dai.commands.converter;

import ch.heigvd.dai.algorithm.AES;
import ch.heigvd.dai.algorithm.DES;
import ch.heigvd.dai.algorithm.Algorithm;
import picocli.CommandLine.ITypeConverter;

public class IAlgorithmConverter implements ITypeConverter<Algorithm> {
    public Algorithm convert(String value) throws Exception {
        if (value.equals("AES") || value.isEmpty()) {
            return new AES();
        }
        if (value.equals("DES")){
            return new DES();
        }
        throw new Exception("Unknown algorithm");
    }
}
