package poc.ddd.demo.films.domain;

import poc.ddd.demo.shared.domain.AggregateRoot;
import poc.ddd.demo.shared.domain.FilmScore;

import java.util.Objects;

public class Film extends AggregateRoot {
    private final FilmId id;
    private final FilmName name;
    private final FilmDuration duration;
    private FilmScore score;

    public Film(FilmId id, FilmName name, FilmDuration duration, FilmScore score) {
        this.id = id;
        this.name = name;
        this.duration = duration;
        this.score = score;
    }

    private Film() {
        id = null;
        name = null;
        duration = null;
        score = null;
    }

    public static Film create(FilmId id, FilmName name, FilmDuration duration) {
        return new Film(id, name, duration, new FilmScore(0.0d));
    }

    public FilmId id() {
        return id;
    }

    public FilmName name() {
        return name;
    }

    public FilmDuration duration() {
        return duration;
    }

    public FilmScore score() {
        return score;
    }

    public void setScore(FilmScore score) {
        this.score = score;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        var film = (Film) o;
        return id.equals(film.id) &&
                name.equals(film.name) &&
                duration.equals(film.duration) &&
                score.equals(film.score);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, duration, score);
    }
}
