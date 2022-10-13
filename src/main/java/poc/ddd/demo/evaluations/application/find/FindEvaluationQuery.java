package poc.ddd.demo.evaluations.application.find;

import poc.ddd.demo.shared.domain.bus.query.Query;

public final class FindEvaluationQuery implements Query {
    private final String id;

    public FindEvaluationQuery(String id) {
        this.id = id;
    }

    public String id() {
        return id;
    }
}
