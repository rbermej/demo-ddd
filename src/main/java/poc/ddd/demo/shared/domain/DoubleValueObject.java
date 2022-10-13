package poc.ddd.demo.shared.domain;

import java.util.Objects;

public abstract class DoubleValueObject {
    private final Double value;

    public DoubleValueObject(Double value) {
        this.value = value;
    }

    public Double value() {
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
        var that = (DoubleValueObject) o;
        return value.equals(that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
