package poc.ddd.demo.evaluations.infrastructure.persistence;

import poc.ddd.demo.evaluations.domain.Evaluation;
import poc.ddd.demo.evaluations.domain.EvaluationFilmId;
import poc.ddd.demo.evaluations.domain.EvaluationId;
import poc.ddd.demo.evaluations.domain.EvaluationRepository;
import poc.ddd.demo.shared.domain.FilmScore;

import java.util.HashMap;
import java.util.Optional;
import java.util.OptionalDouble;

public final class InMemoryEvaluationRepository implements EvaluationRepository {
    private final HashMap<String, Evaluation> evaluations = new HashMap<>();

    @Override
    public void save(Evaluation evaluation) {
        evaluations.put(evaluation.id().value(), evaluation);
    }

    @Override
    public Optional<Evaluation> search(EvaluationId id) {
        return Optional.ofNullable(evaluations.get(id.value()));
    }

    @Override
    public Optional<FilmScore> getAverageEvaluationScoreFilm(EvaluationFilmId filmId) {

        final OptionalDouble average = evaluations.values()
                .stream()
                .filter(evaluation -> evaluation.filmId().equals(filmId))
                .mapToInt(evaluation -> evaluation.score().value())
                .average();

        if (average.isEmpty()){
            return Optional.empty();
        }

        return Optional.of(new FilmScore(average.getAsDouble()));
    }

}
