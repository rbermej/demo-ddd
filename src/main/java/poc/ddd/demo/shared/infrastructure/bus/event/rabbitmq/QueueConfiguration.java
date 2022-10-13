package poc.ddd.demo.shared.infrastructure.bus.event.rabbitmq;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QueueConfiguration {

    @Bean
    public Queue evaluationsCreatedQueue() {
        return new Queue("evaluations.created", false);
    }

}
