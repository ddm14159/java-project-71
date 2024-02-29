package hexlet.code;

import hexlet.code.formattes.PlainFormatter;
import hexlet.code.formattes.StylishFormatter;

import java.util.List;
import java.util.Map;

public class Formatter {
    public static final String FORMAT_STYLISH = "stylish";
    public static final String FORMAT_PLAIN = "plain";

    public static String format(List<Map<String, Object>> data, String format) {
        switch (format) {
            case FORMAT_PLAIN:
                return PlainFormatter.get(data);
            default:
                return StylishFormatter.get(data);
        }
    }
}
