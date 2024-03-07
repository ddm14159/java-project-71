package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import java.util.Map;

public class Parser {
    private static final String TYPE_JSON = "json";
    private static final String TYPE_YAML = "yaml";
    private static final String TYPE_YML = "yml";

    private static Map<String, Object> parseYaml(String content) throws Exception  {
        ObjectMapper mapper = new YAMLMapper();
        return mapper.readValue(content, new TypeReference<>() { });
    }

    private static Map<String, Object> parseJson(String content) throws Exception  {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(content, new TypeReference<>() { });
    }

    public static Map<String, Object> parse(String data, String format) throws Exception {
        return switch (format) {
            case TYPE_YAML, TYPE_YML -> parseYaml(data);
            case TYPE_JSON -> parseJson(data);
            default -> throw new Exception("Given data format is not supported: '" + format + "'");
        };
    }
}
