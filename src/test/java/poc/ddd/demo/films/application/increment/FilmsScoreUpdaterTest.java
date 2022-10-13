package poc.ddd.demo.films.application.increment;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import poc.ddd.demo.evaluations.application.find_avg_score_film.EvaluationsScoreAverageFinder;
import poc.ddd.demo.evaluations.domain.EvaluationFilmId;
import poc.ddd.demo.films.application.FilmResponse;
import poc.ddd.demo.films.application.find.FilmFinder;
import poc.ddd.demo.films.application.find.FindFilmQuery;
import poc.ddd.demo.films.application.find.FindFilmQueryHandler;
import poc.ddd.demo.films.domain.Film;
import poc.ddd.demo.films.domain.FilmId;
import poc.ddd.demo.films.domain.FilmRepository;
import poc.ddd.demo.shared.domain.FilmScore;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FilmsScoreUpdaterTest {

    private FilmsScoreUpdater filmsScoreUpdater;

    private final FilmRepository repository = Mockito.mock(FilmRepository.class);
    private final EvaluationsScoreAverageFinder evaluationsScoreAverageFinder= Mockito.mock(EvaluationsScoreAverageFinder.class);

    @BeforeEach
    void setUp() {
        filmsScoreUpdater = new FilmsScoreUpdater(repository, evaluationsScoreAverageFinder);
    }

    @Test
    void should_update_score() {

        var id = new FilmId("12a772c7-e265-45e7-a327-ba62303f21de");
        var score = new FilmScore(2.6d);
        var film = mock(Film.class);

        when(evaluationsScoreAverageFinder.find(new EvaluationFilmId(id.value()))).thenReturn(score);
        when(repository.search(id)).thenReturn(Optional.of(film));

       filmsScoreUpdater.updateScore(id);

       verify(film).setScore(score);
       verify(repository).save(film);

    }

    @Test
    void should_not_update_score() {

        var id = new FilmId("12a772c7-e265-45e7-a327-ba62303f21de");
        var score = new FilmScore(2.6d);

        when(evaluationsScoreAverageFinder.find(new EvaluationFilmId(id.value()))).thenReturn(score);
        when(repository.search(id)).thenReturn(Optional.empty());

        filmsScoreUpdater.updateScore(id);

        verify(repository, never()).save(Mockito.any(Film.class));

    }

}