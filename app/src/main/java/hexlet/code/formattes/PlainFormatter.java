package hexlet.code.formattes;

import java.util.List;
import java.util.Map;
import hexlet.code.Status;

public class PlainFormatter {
    private static final String MESSAGE_UPDATED = "Property '%s' was updated. From %s to %s";
    private static final String MESSAGE_ADDED = "Property '%s' was added with value: %s";
    private static final String MESSAGE_REMOVED = "Property '%s' was removed";
    private static final String VALUE_COMPLEX = "[complex value]";
    private static final String VALUE_STRING = "'%s'";
    private static final String NEW_LINE = "\n";

    public static String get(Map<String, Object> data) {
        var result = new StringBuilder();

        for (var item: data.entrySet()) {
            var key = item.getKey();
            var status = (Status) item.getValue();
            var value = status.getValue();
            var newValue = status.getNewValue();

            switch (status.getName()) {
                case Status.ADDED:
                    result.append(String.format(MESSAGE_ADDED, key, stringify(value))).append(NEW_LINE);
                    break;
                case Status.REMOVED:
                    result.append(String.format(MESSAGE_REMOVED, key)).append(NEW_LINE);
                    break;
                case Status.CHANGED:
                    result
                            .append(String.format(MESSAGE_UPDATED, key, stringify(value), stringify(newValue)))
                            .append(NEW_LINE);
                    break;
                default:
                    break;
            }
        }

        result.deleteCharAt(result.length() - 1);

        return result.toString();
    }

    private static String stringify(Object item) {
        if (item == null) {
            return "null";
        }

        if (item instanceof String) {
            return String.format(VALUE_STRING, item);
        }
        if (item instanceof Map || item instanceof List) {
            return VALUE_COMPLEX;
        }

        return item.toString();
    }
}
