package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;
import picocli.CommandLine;
import picocli.CommandLine.Parameters;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
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

    @Option(names = {"-f", "--format"},
            arity = "1",
            defaultValue = "stylish",
            description = "output format [default: stylish]"
    )
    String format;

    public static void main(String[] args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }
    @Override public Integer call() throws Exception {
        System.out.println(Differ.genDiff(this.filepath1, this.filepath2));
        return null;
    }
}
