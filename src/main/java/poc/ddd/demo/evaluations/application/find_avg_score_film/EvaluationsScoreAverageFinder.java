package poc.ddd.demo.evaluations.application.find_avg_score_film;

import poc.ddd.demo.evaluations.application.EvaluationResponse;
import poc.ddd.demo.evaluations.domain.EvaluationFilmId;
import poc.ddd.demo.evaluations.domain.EvaluationId;
import poc.ddd.demo.evaluations.domain.EvaluationIdNotExist;
import poc.ddd.demo.evaluations.domain.EvaluationRepository;
import poc.ddd.demo.shared.domain.FilmScore;
import poc.ddd.demo.shared.domain.Service;

@Service
public class EvaluationsScoreAverageFinder {
    private final EvaluationRepository repository;

    public EvaluationsScoreAverageFinder(EvaluationRepository repository) {
        this.repository = repository;
    }

    public FilmScore find(EvaluationFilmId evaluationFilmId) throws EvaluationIdNotExist {
        return new FilmScore(
                repository.getAverageEvaluationScoreFilm(evaluationFilmId)
                        .map(FilmScore::value)
                        .orElse(0d));
    }
}