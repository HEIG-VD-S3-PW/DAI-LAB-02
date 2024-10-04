package ch.heigvd.dai.file;

import java.io.*;

public class FileManager {

    private final String inputFileName;
    private final String outputFileName;

    private byte[] data;

    public FileManager(String _inputFileName, String _outputFileName) {
        this.inputFileName = _inputFileName;
        this.outputFileName = _outputFileName;

        this.data = new byte[0];

    }

    public void read() {

        try (FileInputStream inputStream = new FileInputStream(this.inputFileName);
             BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream)) {

            this.data = bufferedInputStream.readAllBytes();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void write(byte[] data) {

        try (FileOutputStream outputStream = new FileOutputStream(this.outputFileName);
             BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream)) {

            bufferedOutputStream.write(data);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public byte[] getData(){
        return this.data;
    }
}
