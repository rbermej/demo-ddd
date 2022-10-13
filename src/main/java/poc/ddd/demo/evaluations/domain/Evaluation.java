package poc.ddd.demo.evaluations.domain;

import poc.ddd.demo.shared.domain.AggregateRoot;

import java.util.Objects;

public final class Evaluation extends AggregateRoot {
    private final EvaluationId id;
    private final EvaluationScore score;
    private final EvaluationFilmId filmId;

    public Evaluation(EvaluationId id, EvaluationScore score, EvaluationFilmId filmId) {
        this.id = id;
        this.score = score;
        this.filmId = filmId;
    }

    private Evaluation() {
        id = null;
        score = null;
        filmId = null;
    }

    public static Evaluation create(EvaluationId id, EvaluationScore score, EvaluationFilmId filmId) {
        final var evaluation = new Evaluation(id, score, filmId);
        evaluation.record(new EvaluationCreatedDomainEvent(id.value(), score.value(), filmId.value()));
        return evaluation;
    }

    public EvaluationId id() {
        return id;
    }

    public EvaluationScore score() {
        return score;
    }

    public EvaluationFilmId filmId() {
        return filmId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        var film = (Evaluation) o;
        return id.equals(film.id) &&
                score.equals(film.score) &&
                filmId.equals(film.filmId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, score, filmId);
    }
}
