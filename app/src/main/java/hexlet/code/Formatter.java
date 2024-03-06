package hexlet.code;

import com.fasterxml.jackson.core.JsonProcessingException;
import hexlet.code.formattes.JsonFormatter;
import hexlet.code.formattes.PlainFormatter;
import hexlet.code.formattes.StylishFormatter;

import java.util.List;
import java.util.Map;

public class Formatter {
    public static final String FORMAT_STYLISH = "stylish";
    public static final String FORMAT_PLAIN = "plain";
    public static final String FORMAT_JSON = "json";

    public static String format(List<Map<String, Object>> data, String format) throws JsonProcessingException {
        return switch (format) {
            case FORMAT_STYLISH -> StylishFormatter.get(data);
            case FORMAT_PLAIN -> PlainFormatter.get(data);
            case FORMAT_JSON -> JsonFormatter.get(data);
            default -> throw new IllegalArgumentException("Output format is invalid");
        };
    }
}
