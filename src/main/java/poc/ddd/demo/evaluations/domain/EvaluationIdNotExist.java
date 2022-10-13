package poc.ddd.demo.evaluations.domain;

import poc.ddd.demo.shared.domain.DomainError;

public final class EvaluationIdNotExist extends DomainError {
    public EvaluationIdNotExist(EvaluationId id) {
        super("evaluation_not_exist", String.format("The evaluation <%s> doesn't exist", id.value()));
    }
}
