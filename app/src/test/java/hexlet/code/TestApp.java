package hexlet.code;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;

public class TestApp {

    private static String resultJson;
    private static String resultPlain;
    private static String resultStylish;
    private static final String FIXTURE_PATH = "src/test/resources/fixtures/";

    private static Path getFixturePath(String fileName) {
        return Paths.get(FIXTURE_PATH, fileName)
                .toAbsolutePath().normalize();
    }

    private static String readFixture(String fileName) throws Exception {
        Path filePath = getFixturePath(fileName);
        return Files.readString(filePath).trim();
    }

    @BeforeAll
    public static void beforeAll() throws Exception {
        resultJson = readFixture("result_json.json");
        resultPlain = readFixture("result_plain.txt");
        resultStylish = readFixture("result_stylish.txt");
    }

    private void testDiff(String filepath1, String filepath2, String format, String result) throws Exception {
        var actual = Differ.generate(filepath1, filepath2, format);

        assertThat(actual).isEqualTo(result);
    }

    private void testDiff(String filepath1, String filepath2, String result) throws Exception {
        var actual = Differ.generate(filepath1, filepath2);

        assertThat(actual).isEqualTo(result);
    }

    @Test
    void testDiffStylish() throws Exception {
        testDiff(
                FIXTURE_PATH + "file1.json",
                FIXTURE_PATH + "file2.json",
                Formatter.FORMAT_STYLISH,
                resultStylish
        );
        testDiff(
                FIXTURE_PATH + "file1.yml",
                FIXTURE_PATH + "file2.yml",
                Formatter.FORMAT_STYLISH,
                resultStylish
        );
    }
    @Test
    void testDiffPlain() throws Exception {
        testDiff(
                FIXTURE_PATH + "file1.json",
                FIXTURE_PATH + "file2.json",
                Formatter.FORMAT_PLAIN,
                resultPlain
        );
        testDiff(
                FIXTURE_PATH + "file1.yml",
                FIXTURE_PATH + "file2.yml",
                Formatter.FORMAT_PLAIN,
                resultPlain
        );
    }

    @Test
    void testDiffJson() throws Exception {
        testDiff(
                FIXTURE_PATH + "file1.json",
                FIXTURE_PATH + "file2.json",
                Formatter.FORMAT_JSON,
                resultJson
        );
        testDiff(
                FIXTURE_PATH + "file1.yml",
                FIXTURE_PATH + "file2.yml",
                Formatter.FORMAT_JSON,
                resultJson
        );
    }

    @Test
    void testDiffDefault() throws Exception {
        testDiff(
                FIXTURE_PATH + "file1.json",
                FIXTURE_PATH + "file2.json",
                resultStylish
        );
        testDiff(
                FIXTURE_PATH + "file1.yml",
                FIXTURE_PATH + "file2.yml",
                resultStylish
        );
    }
}
