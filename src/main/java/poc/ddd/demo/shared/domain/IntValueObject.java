package poc.ddd.demo.shared.domain;

import java.util.Objects;

public abstract class IntValueObject {
    private final Integer value;

    public IntValueObject(Integer value) {
        this.value = value;
    }

    public Integer value() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        var that = (IntValueObject) o;
        return value.equals(that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
