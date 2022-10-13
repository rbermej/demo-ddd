package poc.ddd.demo.evaluations.application;

import poc.ddd.demo.evaluations.domain.Evaluation;
import poc.ddd.demo.shared.domain.bus.query.Response;

public final class EvaluationResponse implements Response {
    private final String id;
    private final float score;
    private final String filmId;

    public EvaluationResponse(String id, float score, String filmId) {
        this.id = id;
        this.score = score;
        this.filmId = filmId;
    }

    public static EvaluationResponse fromAggregate(Evaluation evaluation) {
        return new EvaluationResponse(evaluation.id().value(), evaluation.score().value(), evaluation.filmId().value());
    }

    public String id() {
        return id;
    }

    public Float score() {
        return score;
    }

    public String filmId() {
        return filmId;
    }
}
