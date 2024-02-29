package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.commons.io.FilenameUtils;
public class Parser {

    private static final String TYPE_JSON = "json";
    private static final String TYPE_YAML = "yaml";
    private static final String TYPE_YML = "yml";
    public static Map<String, Object> parse(String filepath) throws Exception {
        var path = Paths.get(filepath);
        var extension = FilenameUtils.getExtension(filepath);

        var mapper = getMapper(extension);

        if (mapper == null) {
            throw new UnsupportedOperationException("Данный формат файла не поддерживается: " + extension);
        }

        var fileData = Files.readString(path);

        return mapper.readValue(fileData, new TypeReference<>() { });
    }

    private static ObjectMapper getMapper(String extension) throws Exception {
        if (getJsonExtensions().contains(extension)) {
            return new ObjectMapper();
        }

        if (getYmlExtensions().contains(extension)) {
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
