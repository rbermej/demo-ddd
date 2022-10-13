package poc.ddd.demo.films.infrastructure.bus.event.rabbitmq;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import poc.ddd.demo.evaluations.domain.EvaluationFilmId;
import poc.ddd.demo.films.application.increment.FilmsScoreUpdater;
import poc.ddd.demo.films.domain.FilmId;
import poc.ddd.demo.shared.domain.Service;

@Service
public class UpdateFilmsScoreOnEvaluationCreated {

    private final FilmsScoreUpdater updater;

    public UpdateFilmsScoreOnEvaluationCreated(FilmsScoreUpdater updater) {
        this.updater = updater;
    }

    @RabbitListener(queues = "evaluations.created")
    public void evaluationsCreatedListener(EvaluationFilmId evaluationFilmId) {
        updater.updateScore(new FilmId(evaluationFilmId.value()));
    }

}
