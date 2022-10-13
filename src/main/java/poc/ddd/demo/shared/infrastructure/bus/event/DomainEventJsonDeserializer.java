package poc.ddd.demo.shared.infrastructure.bus.event;

import poc.ddd.demo.shared.domain.Service;
import poc.ddd.demo.shared.domain.Utils;
import poc.ddd.demo.shared.domain.bus.event.DomainEvent;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

@Service
public final class DomainEventJsonDeserializer {
    private final DomainEventsInformation information;

    public DomainEventJsonDeserializer(DomainEventsInformation information) {
        this.information = information;
    }

    public DomainEvent deserialize(String body) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException, InstantiationException {
        HashMap<String, Serializable> eventData = Utils.jsonDecode(body);
        HashMap<String, Serializable> data = (HashMap<String, Serializable>) eventData.get("data");
        HashMap<String, Serializable> attributes = (HashMap<String, Serializable>) data.get("attributes");
        Class<? extends DomainEvent> domainEventClass = information.forName((String) data.get("type"));

        DomainEvent nullInstance = domainEventClass.getConstructor().newInstance();

        Method fromPrimitivesMethod = domainEventClass.getMethod(
                "fromPrimitives",
                String.class,
                HashMap.class,
                String.class,
                String.class
        );

        Object domainEvent = fromPrimitivesMethod.invoke(
                nullInstance,
                attributes.get("id"),
                attributes,
                data.get("id"),
                data.get("occurred_on")
        );

        return (DomainEvent) domainEvent;
    }
}
