package inkcode;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BasicTest {

    @Test
    public void test() {
        assertThat("this").isEqualTo("this");
    }
}
