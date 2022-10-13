package poc.ddd.demo.films.application;

import poc.ddd.demo.shared.domain.bus.query.Response;

import java.util.List;

public final class FilmsResponse implements Response {
    private final List<FilmResponse> films;

    public FilmsResponse(List<FilmResponse> films) {
        this.films = films;
    }

    public List<FilmResponse> films() {
        return films;
    }
}
