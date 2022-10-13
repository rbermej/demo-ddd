package poc.ddd.demo.films.application.create;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import poc.ddd.demo.films.domain.*;

import static org.mockito.Mockito.verify;

class FilmCreatorTest {

    private FilmCreator filmCreator;

    private final FilmRepository repository = Mockito.mock(FilmRepository.class);

    @BeforeEach
    void setUp() {
        filmCreator = new FilmCreator(repository);
    }

    @Test
    void should_create_a_valid_film() {

        var id = new FilmId("12a772c7-e265-45e7-a327-ba62303f21de");
        var name = new FilmName("Señor de los anillos");
        var duration = new FilmDuration(150);

        filmCreator.create(id, name, duration);

        verify(repository).save(Film.create(id, name, duration));

    }

}