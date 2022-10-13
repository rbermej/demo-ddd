package poc.ddd.demo.films.application.increment;

import poc.ddd.demo.evaluations.application.find_avg_score_film.EvaluationsScoreAverageFinder;
import poc.ddd.demo.evaluations.domain.EvaluationFilmId;
import poc.ddd.demo.films.domain.FilmId;
import poc.ddd.demo.films.domain.FilmRepository;
import poc.ddd.demo.shared.domain.Service;

@Service
public class FilmsScoreUpdater {
    private final FilmRepository repository;
    private final EvaluationsScoreAverageFinder evaluationsScoreAverageFinder;

    public FilmsScoreUpdater(FilmRepository repository, EvaluationsScoreAverageFinder evaluationsScoreAverageFinder) {
        this.repository = repository;
        this.evaluationsScoreAverageFinder = evaluationsScoreAverageFinder;
    }

    public void updateScore(FilmId id) {
        final var score = evaluationsScoreAverageFinder.find(new EvaluationFilmId(id.value()));
        final var film = repository.search(id);
        if (film.isPresent()) {
            film.get().setScore(score);
            repository.save(film.get());
        }
    }
}
