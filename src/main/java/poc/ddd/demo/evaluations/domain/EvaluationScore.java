package poc.ddd.demo.evaluations.domain;

import poc.ddd.demo.shared.domain.FloatValueObject;
import poc.ddd.demo.shared.domain.IntValueObject;

public final class EvaluationScore extends IntValueObject {
    public EvaluationScore(Integer value) {
        super(value);
    }

    private EvaluationScore() {
        super(0);
    }
}
