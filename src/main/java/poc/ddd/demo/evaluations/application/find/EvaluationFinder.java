package poc.ddd.demo.evaluations.application.find;

import poc.ddd.demo.evaluations.application.EvaluationResponse;
import poc.ddd.demo.evaluations.domain.EvaluationId;
import poc.ddd.demo.evaluations.domain.EvaluationIdNotExist;
import poc.ddd.demo.evaluations.domain.EvaluationRepository;
import poc.ddd.demo.shared.domain.Service;

@Service
public final class EvaluationFinder {
    private final EvaluationRepository repository;

    public EvaluationFinder(EvaluationRepository repository) {
        this.repository = repository;
    }

    public EvaluationResponse find(EvaluationId id) throws EvaluationIdNotExist {
        return repository.search(id)
                .map(EvaluationResponse::fromAggregate)
                .orElseThrow(() -> new EvaluationIdNotExist(id));
    }
}