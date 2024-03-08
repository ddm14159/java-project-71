package hexlet.code;

import java.util.Objects;
import java.util.TreeSet;
import java.util.Set;
import java.util.Map;
import java.util.TreeMap;

public class Difference {
    public static Map<String, Object> get(Map<String, Object> data1, Map<String, Object> data2) {
        var result = new TreeMap<String, Object>();

        Set<String> keys = new TreeSet<>();
        keys.addAll(data1.keySet());
        keys.addAll(data2.keySet());

        keys.forEach(key -> {
            var value1 = data1.get(key);
            var value2 = data2.get(key);

            if (data1.containsKey(key) && !data2.containsKey(key)) {
                result.put(key, new Status(Status.REMOVED, value1));
            } else if (!data1.containsKey(key) && data2.containsKey(key)) {
                result.put(key, new Status(Status.ADDED, value2));
            } else if (data1.containsKey(key) && data2.containsKey(key) && !Objects.deepEquals(value1, value2)) {
                result.put(key, new Status(Status.CHANGED, value1, value2));
            } else {
                result.put(key, new Status(Status.UNCHANGED, value1));
            }
        });

        return result;
    }
}
