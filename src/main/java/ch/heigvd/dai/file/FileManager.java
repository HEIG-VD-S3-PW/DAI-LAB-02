package ch.heigvd.dai.file;

/** Manage files
 * @author Tristan Baud
 * @author Mathieu Emery
*/

import java.io.*;

public class FileManager {

    private final String inputFileName;
    private final String outputFileName;

    private byte[] data;

    /**
     * Stores the inputFileName and outputFileName in the private attributes of the class
     * @param _inputFileName The input file's name
     * @param _outputFileName The output file's name
     */
    public FileManager(String _inputFileName, String _outputFileName) {
        this.inputFileName = _inputFileName;
        this.outputFileName = _outputFileName;

        this.data = new byte[0];

    }

    /**
     * Reads the input file's content
     */
    public void read() {

        try (FileInputStream inputStream = new FileInputStream(this.inputFileName);
             BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream)) {

            this.data = bufferedInputStream.readAllBytes();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Write content in the output file
     * @param data content to write in the file
     */
    public void write(byte[] data) {

        try (FileOutputStream outputStream = new FileOutputStream(this.outputFileName);
             BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream)) {

            bufferedOutputStream.write(data);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns the data stored in the private attribute "data"
     * @return Data stored in the class
     */
    public byte[] getData(){
        return this.data;
    }
}
