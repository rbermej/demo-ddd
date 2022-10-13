package poc.ddd.demo.shared.domain;

import java.util.Objects;

public abstract class FloatValueObject {
    private final Float value;

    public FloatValueObject(Float value) {
        this.value = value;
    }

    public Float value() {
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
        var that = (FloatValueObject) o;
        return value.equals(that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
