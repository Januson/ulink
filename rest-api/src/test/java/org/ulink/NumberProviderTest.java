package org.ulink;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class NumberProviderTest {

    @Test
    void shouldProvideNumber() {
        final var number = new NumberProvider();

        assertThat(number.get()).isEqualTo(1);
    }
}