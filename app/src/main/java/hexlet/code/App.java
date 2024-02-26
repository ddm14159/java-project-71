package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;
import picocli.CommandLine;
import picocli.CommandLine.Parameters;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.util.*;
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
    @Override public Integer call() throws Exception {
        var json1 = this.getData(this.filepath1);
        var json2 = this.getData(this.filepath2);

        System.out.println(this.genDiff(json1, json2));
        return null;
    }

    public Map<String, Object> getData(String filepath) throws Exception {
        ObjectMapper mapper = new ObjectMapper();

        var path = Paths.get(filepath);
        var fileData = Files.readString(path);

        return mapper.readValue(fileData, Map.class);
    }

    public String genDiff(Map<String, Object> json1, Map<String, Object> json2) {

        List<String> keys = new ArrayList<>(json1.keySet());

        for (var key: json2.keySet()) {
            if (!keys.contains(key)) {
                keys.add(key);
            }
        }

        Collections.sort(keys);

        var result = new StringBuilder();
        result.append("{");

        for (String key : keys) {
            if (json1.containsKey(key) && !json2.containsKey(key)) {
                result.append("\n").append("  - ").append(key).append(": ").append(json1.get(key));
                continue;
            }

            if (!json1.containsKey(key) && json2.containsKey(key)) {
                result.append("\n").append("  + ").append(key).append(": ").append(json2.get(key));
                continue;
            }

            if (json1.containsKey(key) && json2.containsKey(key) && !json1.get(key).equals(json2.get(key))) {
                result.append("\n").append("  - ").append(key).append(": ").append(json1.get(key));
                result.append("\n").append("  + ").append(key).append(": ").append(json2.get(key));
                continue;
            }

            result.append("\n").append("    ").append(key).append(": ").append(json1.get(key));
        }

        result.append("\n}");

        return result.toString();
    }
}
