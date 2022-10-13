package poc.ddd.demo.evaluations.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface JPAEvaluationRepository extends JpaRepository<EvaluationEntity, String> {

    @Query(value = "SELECT AVG(e.score) FROM evaluations e WHERE e.film_id = :id", nativeQuery = true)
    Double getAverageEvaluationScoreFilm(@Param("id") String id);

}
