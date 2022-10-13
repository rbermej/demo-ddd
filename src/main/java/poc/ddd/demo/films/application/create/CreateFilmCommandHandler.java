package poc.ddd.demo.films.application.create;

import poc.ddd.demo.films.domain.FilmDuration;
import poc.ddd.demo.films.domain.FilmId;
import poc.ddd.demo.films.domain.FilmName;
import poc.ddd.demo.shared.domain.Service;
import poc.ddd.demo.shared.domain.bus.command.CommandHandler;

@Service
public class CreateFilmCommandHandler implements CommandHandler<CreateFilmCommand> {
    private final FilmCreator creator;

    public CreateFilmCommandHandler(FilmCreator creator) {
        this.creator = creator;
    }

    @Override
    public void handle(CreateFilmCommand command) {
        var id = new FilmId(command.id());
        var name = new FilmName(command.name());
        var duration = new FilmDuration(command.duration());

        creator.create(id, name, duration);
    }
}
