package hexlet.code;

import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;

public class TestApp {
    @Test
    void testDiffStylish() throws Exception {
        testDiff(
                "src/test/resources/nested1.json",
                "src/test/resources/nested2.json",
                Formatter.FORMAT_STYLISH,
                "src/test/resources/result_stylish"
        );
        testDiff(
                "src/test/resources/nested1.yml",
                "src/test/resources/nested2.yml",
                Formatter.FORMAT_STYLISH,
                "src/test/resources/result_stylish"
        );
    }

    @Test
    void testDiffPlain() throws Exception {
        testDiff(
                "src/test/resources/nested1.json",
                "src/test/resources/nested2.json",
                Formatter.FORMAT_PLAIN,
                "src/test/resources/result_plain"
        );
        testDiff(
                "src/test/resources/nested1.yml",
                "src/test/resources/nested2.yml",
                Formatter.FORMAT_PLAIN,
                "src/test/resources/result_plain"
        );
    }

    @Test
    void testDiffJson() throws Exception {
        testDiff(
                "src/test/resources/nested1.json",
                "src/test/resources/nested2.json",
                Formatter.FORMAT_JSON,
                "src/test/resources/result_json"
        );
        testDiff(
                "src/test/resources/nested1.yml",
                "src/test/resources/nested2.yml",
                Formatter.FORMAT_JSON,
                "src/test/resources/result_json"
        );
    }


    private void testDiff(String filepath1, String filepath2, String format, String resultFilePath) throws Exception {
        var resultPath = Paths.get(resultFilePath);
        var result = Files.readString(resultPath);
        var actual = Differ.generate(filepath1, filepath2, format);

        assertThat(actual).isEqualTo(result);
    }
}
