package hexlet.code;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

public class Differ {
    public static String generate(String filepath1, String filepath2) throws Exception {
        var format = Formatter.FORMAT_STYLISH;
        return generate(filepath1, filepath2, format);
    }

    public static String generate(String filepath1, String filepath2, String format) throws Exception {
        Map<String, Object> data1 = Parser.parse(getData(filepath1), getDataFormat(filepath1));
        Map<String, Object> data2 = Parser.parse(getData(filepath2), getDataFormat(filepath2));

        var difference = Difference.get(data1, data2);

        return Formatter.format(difference, format);
    }

    private static String getData(String filepath) throws IOException {
        var path = Paths.get(filepath);

        return Files.readString(path).trim();
    }

    private static String getDataFormat(String filePath) {
        int index = filePath.lastIndexOf('.');
        return index > 0
                ? filePath.substring(index + 1)
                : "";
    }
}
