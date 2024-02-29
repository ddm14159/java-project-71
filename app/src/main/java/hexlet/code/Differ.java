package hexlet.code;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Differ {
    public static final String STATUS_ADDED = "added";
    public static final String STATUS_REMOVED = "removed";
    public static final String STATUS_CHANGED = "changed";
    public static final String STATUS_UNCHANGED = "unchanged";
    public static final String INDEX_KEY = "key";
    public static final String INDEX_TYPE = "type";
    public static final String INDEX_VALUE = "value";
    public static final String INDEX_NEW_VALUE = "newValue";
    private static List<Map<String, Object>> getDiffObject(String filepath1, String filepath2) throws Exception {
        var data1 = Parser.parse(filepath1);
        var data2 = Parser.parse(filepath2);

        List<String> keys = new ArrayList<>(data1.keySet());

        for (var key: data2.keySet()) {
            if (!keys.contains(key)) {
                keys.add(key);
            }
        }

        Collections.sort(keys);

        var result = new ArrayList<Map<String, Object>>();

        for (String key : keys) {
            var value1 = stringifyNull(data1.get(key));
            var value2 = stringifyNull(data2.get(key));

            if (data1.containsKey(key) && !data2.containsKey(key)) {
                result.add(Map.of(
                        INDEX_KEY, key,
                        INDEX_TYPE, STATUS_REMOVED,
                        INDEX_VALUE, value1
                ));
                continue;
            }

            if (!data1.containsKey(key) && data2.containsKey(key)) {
                result.add(Map.of(
                        INDEX_KEY, key,
                        INDEX_TYPE, STATUS_ADDED,
                        INDEX_VALUE, value2
                ));
                continue;
            }

            if (data1.containsKey(key) && data2.containsKey(key) && !Objects.deepEquals(value1, value2)) {
                result.add(Map.of(
                        INDEX_KEY, key,
                        INDEX_TYPE, STATUS_CHANGED,
                        INDEX_VALUE, value1,
                        INDEX_NEW_VALUE, value2
                ));
                continue;
            }

            result.add(Map.of(
                    INDEX_KEY, key,
                    INDEX_TYPE, STATUS_UNCHANGED,
                    INDEX_VALUE, value1
            ));
        }

        return result;
    }

    public static String generate(String filepath1, String filepath2) throws Exception {
        var difference = getDiffObject(filepath1, filepath2);
        var format = Formatter.FORMAT_STYLISH;
        return Formatter.format(difference, format);
    }

    public static String generate(String filepath1, String filepath2, String format) throws Exception {
        var difference = getDiffObject(filepath1, filepath2);
        return Formatter.format(difference, format);
    }

    private static Object stringifyNull(Object item) {
        if (item == null) {
            return "null";
        }

        return item;
    }
}
