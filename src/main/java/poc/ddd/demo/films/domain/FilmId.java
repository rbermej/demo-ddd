package poc.ddd.demo.films.domain;

import poc.ddd.demo.shared.domain.Identifier;

public final class FilmId extends Identifier {
    public FilmId(String value) {
        super(value);
    }

    public FilmId() {
    }
}
