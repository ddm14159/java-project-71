package hexlet.code;

import hexlet.code.formattes.StylishFormatter;

import java.util.List;
import java.util.Map;

public class Formatter {
    public static String format(List<Map<String, Object>> data, String format) {
        return StylishFormatter.get(data);
    }
}
