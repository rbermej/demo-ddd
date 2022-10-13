package poc.ddd.demo.films.application.find;

import poc.ddd.demo.shared.domain.bus.query.Query;

public final class FindFilmQuery implements Query {
    private final String id;

    public FindFilmQuery(String id) {
        this.id = id;
    }

    public String id() {
        return id;
    }
}
