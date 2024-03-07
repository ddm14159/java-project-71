package hexlet.code;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;

public final class TestApp {

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

    @ParameterizedTest
    @ValueSource(strings = {"json", "yml"})
    public void generateTest(String format) throws Exception {
        String filePath1 = getFixturePath("file1." + format).toString();
        String filePath2 = getFixturePath("file2." + format).toString();

        assertThat(Differ.generate(filePath1, filePath2)).isEqualTo(resultStylish);
        assertThat(Differ.generate(filePath1, filePath2, Formatter.FORMAT_STYLISH)).isEqualTo(resultStylish);
        assertThat(Differ.generate(filePath1, filePath2, Formatter.FORMAT_PLAIN)).isEqualTo(resultPlain);
        assertThat(Differ.generate(filePath1, filePath2, Formatter.FORMAT_JSON)).isEqualTo(resultJson);
    }
}
