package poc.ddd.demo.shared.domain.bus.command;

public interface CommandBus {
    void dispatch(Command command) throws CommandHandlerExecutionError;
}
