package poc.ddd.demo.films.application;

import poc.ddd.demo.films.domain.Film;
import poc.ddd.demo.shared.domain.bus.query.Response;

import java.util.Objects;

public final class FilmResponse implements Response {
    private final String id;
    private final String name;
    private final Integer duration;
    private final Double score;

    public FilmResponse(String id, String name, Integer duration, Double score) {
        this.id = id;
        this.name = name;
        this.duration = duration;
        this.score = score;
    }

    public static FilmResponse fromAggregate(Film film) {
        return new FilmResponse(film.id().value(), film.name().value(), film.duration().value(), film.score().value());
    }

    public String id() {
        return id;
    }

    public String name() {
        return name;
    }

    public Integer duration() {
        return duration;
    }

    public Double score() {
        return score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FilmResponse that = (FilmResponse) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(duration, that.duration) && Objects.equals(score, that.score);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, duration, score);
    }
}
