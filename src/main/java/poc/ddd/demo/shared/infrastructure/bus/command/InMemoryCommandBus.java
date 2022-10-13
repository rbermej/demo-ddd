package poc.ddd.demo.shared.infrastructure.bus.command;

import org.springframework.context.ApplicationContext;
import poc.ddd.demo.shared.domain.Service;
import poc.ddd.demo.shared.domain.bus.command.Command;
import poc.ddd.demo.shared.domain.bus.command.CommandBus;
import poc.ddd.demo.shared.domain.bus.command.CommandHandler;
import poc.ddd.demo.shared.domain.bus.command.CommandHandlerExecutionError;

@Service
public final class InMemoryCommandBus implements CommandBus {
    private final CommandHandlersInformation information;
    private final ApplicationContext context;

    public InMemoryCommandBus(CommandHandlersInformation information, ApplicationContext context) {
        this.information = information;
        this.context = context;
    }

    @Override
    public void dispatch(Command command) throws CommandHandlerExecutionError {
        try {
            Class<? extends CommandHandler> commandHandlerClass = information.search(command.getClass());

            var handler = context.getBean(commandHandlerClass);

            handler.handle(command);
        } catch (Throwable error) {
            throw new CommandHandlerExecutionError(error);
        }
    }
}
