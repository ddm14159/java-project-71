package hexlet.code;

import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Paths;
import static org.assertj.core.api.Assertions.assertThat;

public class TestApp {
    @Test
    void testDiff() throws Exception {
        testFileDiff("src/test/resources/nested1.json", "src/test/resources/nested2.json");
        testFileDiff("src/test/resources/nested1.yml", "src/test/resources/nested2.yml");
    }

    private void testFileDiff(String filepath1, String filepath2) throws Exception {
        var resultPath = Paths.get("src/test/resources/nested_result");
        var result = Files.readString(resultPath);
        var actual = Differ.genDiff(filepath1, filepath2);

        assertThat(actual).isEqualTo(result);
    }
}
