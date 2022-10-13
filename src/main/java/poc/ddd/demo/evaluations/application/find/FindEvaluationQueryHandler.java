package poc.ddd.demo.evaluations.application.find;


import poc.ddd.demo.evaluations.application.EvaluationResponse;
import poc.ddd.demo.evaluations.domain.EvaluationId;
import poc.ddd.demo.evaluations.domain.EvaluationIdNotExist;
import poc.ddd.demo.shared.domain.Service;
import poc.ddd.demo.shared.domain.bus.query.QueryHandler;

@Service
public final class FindEvaluationQueryHandler implements QueryHandler<FindEvaluationQuery, EvaluationResponse> {
    private final EvaluationFinder finder;

    public FindEvaluationQueryHandler(EvaluationFinder finder) {
        this.finder = finder;
    }

    @Override
    public EvaluationResponse handle(FindEvaluationQuery query) throws EvaluationIdNotExist {
        return finder.find(new EvaluationId(query.id()));
    }
}
