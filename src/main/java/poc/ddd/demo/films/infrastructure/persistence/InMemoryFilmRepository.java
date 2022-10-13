package poc.ddd.demo.films.infrastructure.persistence;

import poc.ddd.demo.films.domain.Film;
import poc.ddd.demo.films.domain.FilmId;
import poc.ddd.demo.films.domain.FilmRepository;
import poc.ddd.demo.shared.domain.criteria.Criteria;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public final class InMemoryFilmRepository implements FilmRepository {
    private final HashMap<String, Film> films = new HashMap<>();

    @Override
    public void save(Film film) {
        films.put(film.id().value(), film);
    }

    public Optional<Film> search(FilmId id) {
        return Optional.ofNullable(films.get(id.value()));
    }

    @Override
    public List<Film> matching(Criteria criteria) {
        return null;
    }
}
