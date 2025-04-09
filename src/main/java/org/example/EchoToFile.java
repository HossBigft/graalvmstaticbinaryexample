package org.example;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

import java.io.*;
import java.util.concurrent.Callable;

@Command(name = "echo-to-file", description = "Writes input to a file and echoes its content.")
public class EchoToFile implements Callable<Integer> {

    @Parameters(index = "0", description = "The string to write to the file.")
    private String input;

    private final String filename = "output.txt";

    @Override
    public Integer call() {
        // Write input to the file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write(input);
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
            return 1;
        }

        // Read and echo the content of the file
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            System.out.println("Content of the file:");
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
            return 1;
        }

        return 0;
    }

    public static void main(String[] args) {
        int exitCode = new CommandLine(new EchoToFile()).execute(args);
        System.exit(exitCode);
    }
}
