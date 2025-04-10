package org.example;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

@Command(name = "greet",
        description = "Greets the provided name",
        mixinStandardHelpOptions = true,
        version = "greet 1.0")
public class GreetingApp implements Runnable {

    @Parameters(index = "0", description = "The name to greet")
    private String name;

    @Option(names = {"-f", "--formal"}, description = "Use formal greeting")
    private boolean formal;

    public void run() {
        String greeting;
        if (formal) {
            greeting = "Good day, " + name + "!";
        } else {
            greeting = "Hello, " + name + "!";
        }

        Path filePath = Path.of("greeting.txt");

        try {
            // Write the greeting to the file
            Files.writeString(filePath, greeting, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
            System.out.println("Writing to file: " + filePath.toAbsolutePath());
            // Read the greeting from the file and print it
            String content = Files.readString(filePath);
            System.out.println(content);

        } catch (IOException e) {
            System.err.println("An error occurred: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        int exitCode = new CommandLine(new GreetingApp()).execute(args);
        System.exit(exitCode);
    }
}
