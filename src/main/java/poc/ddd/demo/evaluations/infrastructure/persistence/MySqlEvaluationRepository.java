package poc.ddd.demo.evaluations.infrastructure.persistence;

import org.springframework.transaction.annotation.Transactional;
import poc.ddd.demo.evaluations.domain.*;
import poc.ddd.demo.shared.domain.FilmScore;
import poc.ddd.demo.shared.domain.Service;

import java.util.Optional;

@Service
@Transactional
public class MySqlEvaluationRepository implements EvaluationRepository {

    private final JPAEvaluationRepository jpaEvaluationRepository;

    public MySqlEvaluationRepository(JPAEvaluationRepository jpaEvaluationRepository) {
        this.jpaEvaluationRepository = jpaEvaluationRepository;
    }

    @Override
    public void save(Evaluation evaluation) {
        jpaEvaluationRepository.saveAndFlush(toJpaEntity(evaluation));
    }

    @Override
    public Optional<Evaluation> search(EvaluationId id) {
        return jpaEvaluationRepository.findById(id.value()).map(entity -> Optional.of(toModelEntity(entity))).orElse(Optional.empty());
    }

    @Override
    public Optional<FilmScore> getAverageEvaluationScoreFilm(EvaluationFilmId filmId) {
        return Optional.of(new FilmScore(jpaEvaluationRepository.getAverageEvaluationScoreFilm(filmId.value())));
    }

    private Evaluation toModelEntity(EvaluationEntity evaluationEntity) {
        final var evaluationId = new EvaluationId(evaluationEntity.getId());
        final var evaluationScore = new EvaluationScore(evaluationEntity.getScore());
        final var evaluationFilmId = new EvaluationFilmId(evaluationEntity.getFilmId());
        return Evaluation.create(evaluationId, evaluationScore, evaluationFilmId);
    }

    private EvaluationEntity toJpaEntity(Evaluation evaluation) {
        EvaluationEntity filmEntity = new EvaluationEntity();
        filmEntity.setId(evaluation.id().value());
        filmEntity.setScore(evaluation.score().value());
        filmEntity.setFilmId(evaluation.filmId().value());
        return filmEntity;
    }

}
