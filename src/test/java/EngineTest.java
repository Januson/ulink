import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class EngineTest {

    @Test
    void itWorks() {
        var number = new NumProvider();

        assertThat(number.get()).isEqualTo(1);
    }

}
