package poc.ddd.demo.films.application.create;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import poc.ddd.demo.films.domain.FilmDuration;
import poc.ddd.demo.films.domain.FilmId;
import poc.ddd.demo.films.domain.FilmName;

import static org.mockito.Mockito.verify;

class CreateFilmCommandHandlerTest {

    private CreateFilmCommandHandler createFilmCommandHandler;

    private FilmCreator creator = Mockito.mock(FilmCreator.class);

    @BeforeEach
    void setUp() {
        createFilmCommandHandler = new CreateFilmCommandHandler(creator);
    }

    @Test
    void should_create_a_valid_film() {

        var id = "12a772c7-e265-45e7-a327-ba62303f21de";
        var name = "Señor de los anillos";
        var duration = 150;

        createFilmCommandHandler.handle(new CreateFilmCommand(id, name, duration));

        verify(creator).create(new FilmId(id), new FilmName(name), new FilmDuration(duration));

    }

}