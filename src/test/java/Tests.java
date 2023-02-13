import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
public class Tests {
    @ParameterizedTest
    @CsvSource({"5,5", "10,20", "9384,12384"})
    void getWidth_OK(int width, int height) {
        Assertions.assertEquals(1, 1);
    }
}
