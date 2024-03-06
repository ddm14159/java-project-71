package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Parser {
    private static final String TYPE_JSON = "json";
    private static final String TYPE_YAML = "yaml";
    private static final String TYPE_YML = "yml";

    public static Map<String, Object> parse(String data, String format) throws Exception {
        var mapper = getMapper(format);

        if (mapper == null) {
            throw new UnsupportedOperationException("Given data format is not supported: " + format);
        }

        return mapper.readValue(data, new TypeReference<>() { });
    }

    private static ObjectMapper getMapper(String format) {

        if (getJsonExtensions().contains(format)) {
            return new ObjectMapper();
        }

        if (getYmlExtensions().contains(format)) {
            return new YAMLMapper();
        }

        return null;
    }

    private static List<String> getJsonExtensions() {
        return new ArrayList<>(List.of(TYPE_JSON));
    }

    private static List<String> getYmlExtensions() {
        return new ArrayList<>(List.of(TYPE_YML, TYPE_YAML));
    }

}
