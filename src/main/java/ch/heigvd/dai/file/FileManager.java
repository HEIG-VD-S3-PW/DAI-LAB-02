package ch.heigvd.dai.file;

import java.io.*;
import java.nio.file.*;

public class FileManager {
    private final String inputFileName;
    private final String outputFileName;
    private byte[] data;

    /**
     * Creates a new FileManager instance
     * @param inputFileName the input file path
     * @param outputFileName the output file path
     * @throws IllegalArgumentException if either filename is null or empty
     */
    public FileManager(String inputFileName, String outputFileName) {
        if (inputFileName == null || inputFileName.trim().isEmpty()) {
            throw new IllegalArgumentException("Input file name cannot be null or empty");
        }
        if (outputFileName == null || outputFileName.trim().isEmpty()) {
            throw new IllegalArgumentException("Output file name cannot be null or empty");
        }

        this.inputFileName = inputFileName;
        this.outputFileName = outputFileName;
        this.data = new byte[0];
    }

    /**
     * Reads the content of the input file into memory
     * @throws IOException if an I/O error occurs
     * @throws FileNotFoundException if the input file doesn't exist
     */
    public void read() throws IOException {
        Path inputPath = Paths.get(inputFileName);

        if (!Files.exists(inputPath)) {
            throw new FileNotFoundException("Input file does not exist: " + inputFileName);
        }

        if (!Files.isReadable(inputPath)) {
            throw new IOException("Input file is not readable: " + inputFileName);
        }

        try (BufferedInputStream bufferedInputStream = new BufferedInputStream(
                new FileInputStream(inputPath.toFile()), 8192)) {
            // 8192 is the default buffer size used by BufferedInputStream
            this.data = bufferedInputStream.readAllBytes();
        }
    }

    /**
     * Writes data to the output file
     * @param data the data to write
     * @throws IOException if an I/O error occurs
     * @throws IllegalArgumentException if data is null
     */
    public void write(byte[] data) throws IOException {
        if (data == null) {
            throw new IllegalArgumentException("Data cannot be null");
        }

        Path outputPath = Paths.get(outputFileName);
        Path parentDir = outputPath.getParent();

        // Create parent directories if they don't exist
        if (parentDir != null && !Files.exists(parentDir)) {
            Files.createDirectories(parentDir);
        }

        try (BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(
                new FileOutputStream(outputPath.toFile()), 8192)) {
            bufferedOutputStream.write(data);
            bufferedOutputStream.flush();
        }
    }

    /**
     * Gets the currently stored data
     * @return a copy of the data array to prevent external modification
     */
    public byte[] getData() {
        return data.clone();
    }
}