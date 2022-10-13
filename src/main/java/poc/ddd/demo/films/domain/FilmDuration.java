package poc.ddd.demo.films.domain;

import poc.ddd.demo.shared.domain.IntValueObject;

public final class FilmDuration extends IntValueObject {
    public FilmDuration(Integer value) {
        super(value);
    }

    private FilmDuration() {
        super(0);
    }
}
