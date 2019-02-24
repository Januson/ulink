package org.ulink;

import java.util.function.Supplier;

public class NumberProvider implements Supplier<Number> {
    @Override
    public Number get() {
        return 1;
    }
}
