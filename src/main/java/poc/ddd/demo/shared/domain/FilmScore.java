package poc.ddd.demo.shared.domain;

public final class FilmScore extends DoubleValueObject {
    public FilmScore(Double value) {
        super(value);
    }

    private FilmScore() {
        super(0.0d);
    }
}
