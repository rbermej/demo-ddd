package poc.ddd.demo.evaluations.application.create;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.messaging.core.AbstractDestinationResolvingMessagingTemplate;
import poc.ddd.demo.evaluations.domain.*;
import poc.ddd.demo.shared.domain.Service;
import poc.ddd.demo.shared.domain.bus.event.DomainEvent;
import poc.ddd.demo.shared.domain.bus.event.EventBus;

@Service
public final class EvaluationCreator {

    private final EvaluationRepository repository;
    //private final EventBus eventBus;
    private RabbitTemplate rabbitTemplate;

    public EvaluationCreator(EvaluationRepository repository, /*EventBus eventBus,*/ RabbitTemplate rabbitTemplate) {
        this.repository = repository;
        //this.eventBus = eventBus;
        this.rabbitTemplate = rabbitTemplate;
    }

    public void create(EvaluationId id, EvaluationScore score, EvaluationFilmId filmId) {
        var evaluation = Evaluation.create(id, score, filmId);
        repository.save(evaluation);
        rabbitTemplate.convertAndSend("evaluations.created", evaluation.filmId());
        //eventBus.publish(evaluation.pullDomainEvents());
    }
}
