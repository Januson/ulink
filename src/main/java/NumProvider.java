import java.util.function.Supplier;

public class NumProvider implements Supplier<Integer> {
    @Override
    public Integer get() {
        return 1;
    }
}
