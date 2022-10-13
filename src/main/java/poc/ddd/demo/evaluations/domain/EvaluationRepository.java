package poc.ddd.demo.evaluations.domain;

import poc.ddd.demo.shared.domain.FilmScore;
import poc.ddd.demo.shared.domain.criteria.Criteria;

import java.util.List;
import java.util.Optional;

public interface EvaluationRepository {
    void save(Evaluation evaluation);

    Optional<Evaluation> search(EvaluationId id);

    Optional<FilmScore> getAverageEvaluationScoreFilm(EvaluationFilmId filmId);
}
