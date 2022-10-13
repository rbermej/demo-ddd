package poc.ddd.demo.evaluations.domain;

import poc.ddd.demo.shared.domain.bus.event.DomainEvent;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Objects;

public final class EvaluationCreatedDomainEvent extends DomainEvent {
    private final Integer score;
    private final String filmId;

    public EvaluationCreatedDomainEvent() {
        super(null);
        this.score = null;
        this.filmId = null;
    }

    public EvaluationCreatedDomainEvent(String aggregateId, Integer score, String filmId) {
        super(aggregateId);
        this.score = score;
        this.filmId = filmId;
    }

    public EvaluationCreatedDomainEvent(
            String aggregateId,
            String eventId,
            String occurredOn,
            Integer score,
            String filmId
    ) {
        super(aggregateId, eventId, occurredOn);
        this.score = score;
        this.filmId = filmId;
    }

    @Override
    public String eventName() {
        return "evaluation.created";
    }

    @Override
    public HashMap<String, Serializable> toPrimitives() {
        return new HashMap<String, Serializable>() {{
            put("score", score);
            put("filmId", filmId);
        }};
    }

    @Override
    public EvaluationCreatedDomainEvent fromPrimitives(
            String aggregateId,
            HashMap<String, Serializable> body,
            String eventId,
            String occurredOn
    ) {
        return new EvaluationCreatedDomainEvent(
                aggregateId,
                eventId,
                occurredOn,
                (Integer) body.get("score"),
                (String) body.get("filmId")
        );
    }

    public Integer score() {
        return score;
    }

    public String duration() {
        return filmId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        EvaluationCreatedDomainEvent that = (EvaluationCreatedDomainEvent) o;
        return eventId().equals(that.eventId()) &&
                score.equals(that.score) &&
                filmId.equals(that.filmId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(eventId(), score, filmId);
    }
}
