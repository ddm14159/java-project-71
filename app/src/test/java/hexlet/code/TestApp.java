package hexlet.code;

import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Paths;
import static org.assertj.core.api.Assertions.assertThat;

public class TestApp {
    @Test
    void testMain() throws Exception {
        var filepath1 = "src/test/resources/file1.json";
        var filepath2 = "src/test/resources/file2.json";
        var filepathResult = "src/test/resources/result";

        var path = Paths.get(filepathResult);
        var result = Files.readString(path);

        String actual = App.genDiff(filepath1, filepath2);

        assertThat(actual).isEqualTo(result);
    }
}
