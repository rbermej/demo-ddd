package poc.ddd.demo.films.application.find;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import poc.ddd.demo.films.application.FilmResponse;
import poc.ddd.demo.films.domain.*;
import poc.ddd.demo.shared.domain.FilmScore;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.Mockito.when;

class FilmFinderTest {

    private FilmFinder filmFinder;

    private final FilmRepository repository = Mockito.mock(FilmRepository.class);

    @BeforeEach
    void setUp() {
        filmFinder = new FilmFinder(repository);
    }

    @Test
    void should_return_film() {

        var id = new FilmId("12a772c7-e265-45e7-a327-ba62303f21de");
        var name = new FilmName("Señor de los anillos");
        var duration = new FilmDuration(150);
        var score = new FilmScore(5.8d);

        Film film = new Film(id, name, duration, score);

        when(repository.search(id)).thenReturn(Optional.of(film));

        final var filmResponse = filmFinder.find(id);

        assertThat(filmResponse).isEqualTo(new FilmResponse(id.value(), name.value(), duration.value(), score.value()));

    }

    @Test
    void should_trown_fil_not_exist_exception() {

        var id = new FilmId("12a772c7-e265-45e7-a327-ba62303f21de");

        when(repository.search(id)).thenReturn(Optional.empty());

        Throwable thrown = catchThrowable(() -> filmFinder.find(id));

        assertThat(thrown)
                .isInstanceOf(FilmNotExist.class)
                .hasMessage(String.format("The film <%s> doesn't exist", id.value()));

    }

}