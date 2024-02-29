package hexlet.code;

import java.util.*;

public class Differ {
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
            if (data1.containsKey(key) && !data2.containsKey(key)) {
                result.add(Map.of(
                        "key", key,
                        "type", "removed",
                        "value", data1.get(key)
                ));
                continue;
            }

            if (!data1.containsKey(key) && data2.containsKey(key)) {
                result.add(Map.of(
                        "key", key,
                        "type", "added",
                        "value", data2.get(key)
                ));
                continue;
            }
            var value1 = stringify(data1.get(key));
            var value2 = stringify(data2.get(key));

            if (data1.containsKey(key) && data2.containsKey(key) && !Objects.deepEquals(value1, value2)) {
                result.add(Map.of(
                        "key", key,
                        "type", "changed",
                        "value", value1,
                        "newValue", value2
                ));
                continue;
            }

            result.add(Map.of(
                    "key", key,
                    "type", "unchanged",
                    "value", data1.get(key)
            ));
        }

        return result;
    }

    public static String genDiff(String filepath1, String filepath2) throws Exception {
        var difference = getDiffObject(filepath1, filepath2);
        var format = "stylish";
        return Formatter.format(difference, format);
    }

    public static String genDiff(String filepath1, String filepath2, String format) throws Exception {
        var difference = getDiffObject(filepath1, filepath2);
        return Formatter.format(difference, format);
    }

    private static String stringify(Object item) {
        return String.valueOf(item);
    }
}
