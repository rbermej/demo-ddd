package poc.ddd.demo.films.domain;

import poc.ddd.demo.shared.domain.StringValueObject;

public final class FilmName extends StringValueObject {
    public FilmName(String value) {
        super(value);
    }

    public FilmName() {
        super("");
    }

}
