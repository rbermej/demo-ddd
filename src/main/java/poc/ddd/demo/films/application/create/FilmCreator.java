package poc.ddd.demo.films.application.create;

import poc.ddd.demo.films.domain.*;
import poc.ddd.demo.shared.domain.Service;
import poc.ddd.demo.shared.domain.bus.event.EventBus;

@Service
public class FilmCreator {

    private final FilmRepository repository;

    public FilmCreator(FilmRepository repository) {
        this.repository = repository;
    }

    public void create(FilmId id, FilmName name, FilmDuration duration) {
        repository.save(Film.create(id, name, duration));
    }
}
