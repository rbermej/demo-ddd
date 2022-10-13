package poc.ddd.demo.evaluations.application;

import poc.ddd.demo.shared.domain.bus.query.Response;

import java.util.List;

public final class EvaluationsResponse implements Response {
    private final List<EvaluationResponse> films;

    public EvaluationsResponse(List<EvaluationResponse> films) {
        this.films = films;
    }

    public List<EvaluationResponse> films() {
        return films;
    }
}
