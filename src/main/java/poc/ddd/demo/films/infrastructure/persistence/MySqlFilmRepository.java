package poc.ddd.demo.films.infrastructure.persistence;

import org.springframework.transaction.annotation.Transactional;
import poc.ddd.demo.films.domain.*;
import poc.ddd.demo.shared.domain.FilmScore;
import poc.ddd.demo.shared.domain.Service;
import poc.ddd.demo.shared.domain.criteria.Criteria;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class MySqlFilmRepository implements FilmRepository {

    private final JPAFilmRepository jpaFilmRepository;

    public MySqlFilmRepository(JPAFilmRepository jpaFilmRepository) {
        this.jpaFilmRepository = jpaFilmRepository;
    }

    @Override
    public void save(Film film) {
        jpaFilmRepository.saveAndFlush(toJpaEntity(film));
    }

    @Override
    public Optional<Film> search(FilmId id) {
        return jpaFilmRepository.findById(id.value()).map(entity->Optional.of(toModelEntity(entity))).orElse(Optional.empty());
    }

    @Override
    public List<Film> matching(Criteria criteria) {
        return jpaFilmRepository.findAll().stream().map(this::toModelEntity).collect(Collectors.toList());
    }

    private Film toModelEntity(FilmEntity filmEntity){
        final var filmId = new FilmId(filmEntity.getId());
        final var filmName = new FilmName(filmEntity.getName());
        final var filmDuration = new FilmDuration(filmEntity.getDuration());
        final var score = new FilmScore(filmEntity.getScore());
        return new Film(filmId, filmName, filmDuration, score);
    }

    private FilmEntity toJpaEntity(Film film){
        FilmEntity filmEntity = new FilmEntity();
        filmEntity.setId(film.id().value());
        filmEntity.setName(film.name().value());
        filmEntity.setDuration(film.duration().value());
        filmEntity.setScore(film.score().value());
        return filmEntity;
    }

}
