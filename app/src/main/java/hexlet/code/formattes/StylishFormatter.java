package hexlet.code.formattes;

import java.util.List;
import java.util.Map;

public class StylishFormatter {
    private static final String ADDED_INDENTATION = "  + ";
    private static final String REMOVED_INDENTATION = "  - ";
    private static final String UNCHANGED_INDENTATION = "    ";

    public static String get(List<Map<String, Object>> data) {
        var result = new StringBuilder();

        result.append("{");

        for (var item: data) {
            var key = item.get("key");
            var type = item.get("type");
            var value = item.get("value");
            var newValue = item.getOrDefault("newValue", null);

            switch (type.toString()) {
                case "added":
                    result.append("\n").append(ADDED_INDENTATION).append(key).append(": ").append(value);
                    break;
                case "removed":
                    result.append("\n").append(REMOVED_INDENTATION).append(key).append(": ").append(value);
                    break;
                case "changed":
                    result.append("\n").append(REMOVED_INDENTATION).append(key).append(": ").append(value);
                    result.append("\n").append(ADDED_INDENTATION).append(key).append(": ").append(newValue);
                    break;
                default:
                    result.append("\n").append(UNCHANGED_INDENTATION).append(key).append(": ").append(value);
            }
        }

        result.append("\n}");

        return result.toString();
    }
}
