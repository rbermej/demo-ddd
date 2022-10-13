package poc.ddd.demo.shared.domain.bus.query;

public interface QueryBus {
    <R> R ask(Query query) throws QueryHandlerExecutionError;
}
