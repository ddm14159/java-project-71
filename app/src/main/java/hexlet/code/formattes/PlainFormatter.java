package hexlet.code.formattes;

import java.util.List;
import java.util.Map;
import hexlet.code.Differ;

public class PlainFormatter {
    private static final String MESSAGE_UPDATED = "Property '%s' was updated. From %s to %s";
    private static final String MESSAGE_ADDED = "Property '%s' was added with value: %s";
    private static final String MESSAGE_REMOVED = "Property '%s' was removed";
    private static final String VALUE_COMPLEX = "[complex value]";
    private static final String VALUE_STRING = "'%s'";
    private static final String NEW_LINE = "\n";

    public static String get(List<Map<String, Object>> data) {
        var result = new StringBuilder();

        for (var item: data) {
            var key = item.get(Differ.INDEX_KEY);
            var type = item.get(Differ.INDEX_TYPE);
            var value = stringify(item.get(Differ.INDEX_VALUE));
            var newValue = stringify(item.getOrDefault(Differ.INDEX_NEW_VALUE, null));

            switch (type.toString()) {
                case Differ.STATUS_ADDED:
                    result.append(String.format(MESSAGE_ADDED, key, value)).append(NEW_LINE);
                    break;
                case Differ.STATUS_REMOVED:
                    result.append(String.format(MESSAGE_REMOVED, key)).append(NEW_LINE);
                    break;
                case Differ.STATUS_CHANGED:
                    result.append(String.format(MESSAGE_UPDATED, key, value, newValue)).append(NEW_LINE);
                    break;
                default:
                    break;
            }
        }

        return result.toString();
    }

    private static String stringify(Object item) {
        if (item == "null") {
            return item.toString();
        } else if (item instanceof Boolean || item instanceof Number) {
            return item.toString();
        } else if (item instanceof String) {
            return String.format(VALUE_STRING, item);
        } else {
            return VALUE_COMPLEX;
        }
    }
}
