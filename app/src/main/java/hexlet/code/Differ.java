package hexlet.code;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Differ {
    public static String genDiff(String filepath1, String filepath2) throws Exception {
        var data1 = Parser.parse(filepath1);
        var data2 = Parser.parse(filepath2);

        List<String> keys = new ArrayList<>(data1.keySet());

        for (var key: data2.keySet()) {
            if (!keys.contains(key)) {
                keys.add(key);
            }
        }

        Collections.sort(keys);

        var result = new StringBuilder();
        result.append("{");

        for (String key : keys) {
            if (data1.containsKey(key) && !data2.containsKey(key)) {
                result.append("\n").append("  - ").append(key).append(": ").append(data1.get(key));
                continue;
            }

            if (!data1.containsKey(key) && data2.containsKey(key)) {
                result.append("\n").append("  + ").append(key).append(": ").append(data2.get(key));
                continue;
            }

            if (data1.containsKey(key) && data2.containsKey(key) && !data1.get(key).equals(data2.get(key))) {
                result.append("\n").append("  - ").append(key).append(": ").append(data1.get(key));
                result.append("\n").append("  + ").append(key).append(": ").append(data2.get(key));
                continue;
            }

            result.append("\n").append("    ").append(key).append(": ").append(data1.get(key));
        }

        result.append("\n}");

        return result.toString();
    }
}
