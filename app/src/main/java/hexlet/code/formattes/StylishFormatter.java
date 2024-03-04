package hexlet.code.formattes;

import hexlet.code.Differ;

import java.util.List;
import java.util.Map;

public class StylishFormatter {
    private static final String INDENTATION_ADDED = "  + ";
    private static final String INDENTATION_REMOVED = "  - ";
    private static final String INDENTATION_UNCHANGED = "    ";
    private static final String NEW_LINE = "\n";

    public static String get(List<Map<String, Object>> data) {
        var result = new StringBuilder();

        result.append("{");

        for (var item: data) {
            var key = item.get(Differ.INDEX_KEY);
            var type = item.get(Differ.INDEX_TYPE);
            var value = item.get(Differ.INDEX_VALUE);
            var newValue = item.getOrDefault(Differ.INDEX_NEW_VALUE, null);

            switch (type.toString()) {
                case Differ.STATUS_ADDED:
                    result.append(NEW_LINE).append(INDENTATION_ADDED).append(key).append(": ").append(value);
                    break;
                case Differ.STATUS_REMOVED:
                    result.append(NEW_LINE).append(INDENTATION_REMOVED).append(key).append(": ").append(value);
                    break;
                case Differ.STATUS_CHANGED:
                    result.append(NEW_LINE).append(INDENTATION_REMOVED).append(key).append(": ").append(value);
                    result.append(NEW_LINE).append(INDENTATION_ADDED).append(key).append(": ").append(newValue);
                    break;
                default:
                    result.append(NEW_LINE).append(INDENTATION_UNCHANGED).append(key).append(": ").append(value);
            }
        }

        result.append("\n}");

        return result.toString();
    }
}
