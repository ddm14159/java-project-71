package hexlet.code.formattes;

import java.util.Map;
import hexlet.code.Status;

public class StylishFormatter {
    private static final String INDENTATION_START = "{";
    private static final String INDENTATION_END = "}";
    private static final String INDENTATION_ADDED = "  + ";
    private static final String INDENTATION_REMOVED = "  - ";
    private static final String INDENTATION_UNCHANGED = "    ";
    private static final String NEW_LINE = "\n";

    public static String get(Map<String, Object> data) {
        var result = new StringBuilder();

        result.append(INDENTATION_START);

        for (var item: data.entrySet()) {
            var key = item.getKey();
            var status = (Status) item.getValue();

            switch (status.getName()) {
                case Status.ADDED:
                    result
                            .append(NEW_LINE)
                            .append(INDENTATION_ADDED)
                            .append(key).append(": ")
                            .append(status.getValue());
                    break;
                case Status.REMOVED:
                    result
                            .append(NEW_LINE)
                            .append(INDENTATION_REMOVED)
                            .append(key).append(": ")
                            .append(status.getValue());
                    break;
                case Status.CHANGED:
                    result
                            .append(NEW_LINE)
                            .append(INDENTATION_REMOVED)
                            .append(key)
                            .append(": ")
                            .append(status.getValue());
                    result
                            .append(NEW_LINE)
                            .append(INDENTATION_ADDED)
                            .append(key).append(": ")
                            .append(status.getNewValue());
                    break;
                default:
                    result
                            .append(NEW_LINE)
                            .append(INDENTATION_UNCHANGED)
                            .append(key).append(": ")
                            .append(status.getValue());
            }
        }

        result.append(NEW_LINE).append(INDENTATION_END);

        return result.toString();
    }
}
