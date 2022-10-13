package poc.ddd.demo.films.application.find;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import poc.ddd.demo.films.application.FilmResponse;
import poc.ddd.demo.films.application.FilmsResponse;
import poc.ddd.demo.films.domain.*;
import poc.ddd.demo.shared.domain.FilmScore;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class FindFilmQueryHandlerTest {

    private FindFilmQueryHandler findFilmQueryHandler;

    private final FilmFinder finder = Mockito.mock(FilmFinder.class);

    @BeforeEach
    void setUp() {
        findFilmQueryHandler = new FindFilmQueryHandler(finder);
    }

    @Test
    void should_return_film() {

        var id = "12a772c7-e265-45e7-a327-ba62303f21de";
        var name = "Señor de los anillos";
        var duration = 150;
        var score = 5.8d;

        FilmResponse filmResponseExpected = new FilmResponse(id, name, duration, score);

        when(finder.find(new FilmId(id))).thenReturn(filmResponseExpected);

        final var filmResponse = findFilmQueryHandler.handle(new FindFilmQuery(id));

        assertThat(filmResponse).isEqualTo(filmResponseExpected);

    }

}