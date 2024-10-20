package ch.heigvd.dai.commands.converter;

import ch.heigvd.dai.algorithm.AES;
import ch.heigvd.dai.algorithm.DES;
import ch.heigvd.dai.algorithm.Algorithm;
import picocli.CommandLine.ITypeConverter;

/** Choose the algorithm depending on the user entry
 * @author Tristan Baud
 * @author Mathieu Emery
*/

public class IAlgorithmConverter implements ITypeConverter<Algorithm> {
    /**
     * Finds the algorithm chosen by the user
     * @param value string that stores the algorithm entered by the user
     * @return Chosen algorithm
     */
    public Algorithm convert(String value) {
        if (value == null || value.isEmpty()) {
            return new AES();
        }
        return Algorithm.valueOf(value);
    }

}
