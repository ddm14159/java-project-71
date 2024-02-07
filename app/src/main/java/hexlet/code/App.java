package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Parameters;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

import java.util.concurrent.Callable;

@Command(name = "gendiff",
        mixinStandardHelpOptions = true,
        version = "1.0",
        description = "Compares two configuration files and shows a difference."
)
public class App implements Callable<Integer> {
    @Parameters(arity = "1", description = "path to first file")
    String filepath1;

    @Parameters(arity = "1", description = "path to second file")
    String filepath2;

    @Option(names = {"-f", "--format"}, arity = "1", defaultValue="stylish", description = "output format [default: stylish]")
    String format;

    public static void main(String[] args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }

    public Integer call() {
        System.out.println("Hello World!");
        return null;
    }
}
