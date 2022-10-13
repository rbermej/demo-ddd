package poc.ddd.demo.shared.infrastructure.bus.event.rabbitmq.wip;

import org.springframework.amqp.AmqpException;
import poc.ddd.demo.shared.domain.bus.event.DomainEvent;
import poc.ddd.demo.shared.domain.bus.event.EventBus;

import java.util.List;

public class RabbitMqEventBus implements EventBus {
    private final RabbitMqPublisher publisher;
    private final String exchangeName;

    public RabbitMqEventBus(RabbitMqPublisher publisher) {
        this.publisher = publisher;
        this.exchangeName = "domain_events";
    }

    @Override
    public void publish(List<DomainEvent> events) {
        events.forEach(this::publish);
    }

    private void publish(DomainEvent domainEvent) {
        try {
            this.publisher.publish(domainEvent, exchangeName);
        } catch (AmqpException error) {
            //TODO send alert
        }
    }
}
