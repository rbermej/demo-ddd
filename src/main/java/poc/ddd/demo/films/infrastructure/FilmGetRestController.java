package poc.ddd.demo.films.infrastructure;

import io.swagger.annotations.Api;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import poc.ddd.demo.films.application.FilmResponse;
import poc.ddd.demo.films.application.find.FindFilmQuery;
import poc.ddd.demo.shared.domain.bus.query.QueryBus;
import poc.ddd.demo.shared.domain.bus.query.QueryHandlerExecutionError;

@RestController
@RequestMapping(value = "/films")
@Api(tags = "Films")
public final class FilmGetRestController {
    private final QueryBus bus;

    public FilmGetRestController(QueryBus bus) {
        this.bus = bus;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Response getFilm(@PathVariable String id) throws QueryHandlerExecutionError {
        FilmResponse filmResponse = bus.ask(new FindFilmQuery(id));

        return new Response(filmResponse);
    }

    class Response {
        private final String id;
        private final String name;
        private final Integer duration;
        private final Double score;

        public Response(FilmResponse film) {
            this.id = film.id();
            this.name = film.name();
            this.duration = film.duration();
            this.score = film.score();
        }

        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public Integer getDuration() {
            return duration;
        }

        public Double getScore() {
            return score;
        }
    }

}
