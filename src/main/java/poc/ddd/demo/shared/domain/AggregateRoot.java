package poc.ddd.demo.shared.domain;

import poc.ddd.demo.shared.domain.bus.event.DomainEvent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class AggregateRoot {

    private List<DomainEvent> domainEvents = new ArrayList<>();

    final public List<DomainEvent> pullDomainEvents() {
        var events = domainEvents;
        domainEvents = Collections.emptyList();
        return events;
    }

    final protected void record(DomainEvent event) {
        domainEvents.add(event);
    }
}
