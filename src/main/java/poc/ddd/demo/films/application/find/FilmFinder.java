package poc.ddd.demo.films.application.find;

import poc.ddd.demo.films.application.FilmResponse;
import poc.ddd.demo.films.domain.FilmId;
import poc.ddd.demo.films.domain.FilmNotExist;
import poc.ddd.demo.films.domain.FilmRepository;
import poc.ddd.demo.shared.domain.Service;

@Service
public class FilmFinder {
    private final FilmRepository repository;

    public FilmFinder(FilmRepository repository) {
        this.repository = repository;
    }

    public FilmResponse find(FilmId id) throws FilmNotExist {
        return repository.search(id)
                .map(FilmResponse::fromAggregate)
                .orElseThrow(() -> new FilmNotExist(id));
    }
}