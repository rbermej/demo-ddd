package poc.ddd.demo.films.domain;

import poc.ddd.demo.shared.domain.criteria.Criteria;

import java.util.List;
import java.util.Optional;

public interface FilmRepository {
    void save(Film film);

    Optional<Film> search(FilmId id);

    List<Film> matching(Criteria criteria);

}
