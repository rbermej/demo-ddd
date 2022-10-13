package poc.ddd.demo.films.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import poc.ddd.demo.films.domain.Film;

public interface JPAFilmRepository extends JpaRepository<FilmEntity, String> {
}
