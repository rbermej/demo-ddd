package poc.ddd.demo.films.infrastructure;


import io.swagger.annotations.Api;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import poc.ddd.demo.films.application.create.CreateFilmCommand;
import poc.ddd.demo.shared.domain.bus.command.CommandBus;
import poc.ddd.demo.shared.domain.bus.command.CommandHandlerExecutionError;
import poc.ddd.demo.shared.infrastructure.validation.Validator;

import java.io.Serializable;
import java.util.HashMap;

@RestController
@RequestMapping(value = "/films")
@Api(tags="Films")
public final class FilmPostRestController {
    private final CommandBus bus;
    private final HashMap<String, String> rules = new HashMap<>() {{
        put("id", "required|not_empty|uuid");
        put("name", "required|not_empty|string");
        put("duration", "required|not_empty|int");
    }};

    public FilmPostRestController(CommandBus bus) {
        this.bus = bus;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void postFilm(@RequestBody HashMap<String, Serializable> request) throws Exception {
        Validator.validate(request, rules);
        createFilm(request);
    }

    private void createFilm(HashMap<String, Serializable> request) throws CommandHandlerExecutionError {
        bus.dispatch(new CreateFilmCommand(
                request.get("id").toString(),
                request.get("name").toString(),
                Integer.valueOf(request.get("duration").toString())
        ));
    }
}
