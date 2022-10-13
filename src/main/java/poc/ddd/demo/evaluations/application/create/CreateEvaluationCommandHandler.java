package poc.ddd.demo.evaluations.application.create;

import poc.ddd.demo.evaluations.domain.EvaluationFilmId;
import poc.ddd.demo.evaluations.domain.EvaluationId;
import poc.ddd.demo.evaluations.domain.EvaluationScore;
import poc.ddd.demo.shared.domain.Service;
import poc.ddd.demo.shared.domain.bus.command.CommandHandler;

@Service
public final class CreateEvaluationCommandHandler implements CommandHandler<CreateEvaluationCommand> {
    private final EvaluationCreator creator;

    public CreateEvaluationCommandHandler(EvaluationCreator creator) {
        this.creator = creator;
    }

    @Override
    public void handle(CreateEvaluationCommand command) {
        var id = new EvaluationId(command.id());
        var score = new EvaluationScore(command.score());
        var filmId = new EvaluationFilmId(command.filmId());

        creator.create(id, score, filmId);
    }
}
