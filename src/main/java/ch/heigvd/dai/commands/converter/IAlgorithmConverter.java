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
    public Algorithm convert(String value) throws Exception {
        if (value == null || value.isEmpty()) {
            value = "AES";
        }
        try {
            String className = "ch.heigvd.dai.algorithm." + value.toUpperCase();
            return (Algorithm) Class.forName(className).getDeclaredConstructor().newInstance();
        } catch (ClassNotFoundException e) {
            throw new IllegalArgumentException("Invalid algorithm: " + value);
        }
    }

}
