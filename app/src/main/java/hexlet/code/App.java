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
public final class App implements Callable<Integer> {
    @Parameters(arity = "1", description = "path to first file")
    private String filepath1;

    @Parameters(arity = "1", description = "path to second file")
    private String filepath2;

    @Option(names = {"-f", "--format"},
            arity = "1",
            defaultValue = Formatter.FORMAT_STYLISH,
            description = "output format [default: " + Formatter.FORMAT_STYLISH + "]"
    )
    private String format;
    private static final int SUCCESS_EXIT_CODE = 0;
    private static final int ERROR_EXIT_CODE = 1;

    public static void main(String[] args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }
    @Override public Integer call() {
        try {
            String formattedDiff = Differ.generate(this.filepath1, this.filepath2, this.format);
            System.out.println(formattedDiff);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return ERROR_EXIT_CODE;
        }

        return SUCCESS_EXIT_CODE;
    }
}
