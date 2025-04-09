package org.example;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

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
        if (formal) {
            System.out.println("Good day, " + name + "!");
        } else {
            System.out.println("Hello, " + name + "!");
        }
    }

    public static void main(String[] args) {
        int exitCode = new CommandLine(new GreetingApp()).execute(args);
        System.exit(exitCode);
    }
}
