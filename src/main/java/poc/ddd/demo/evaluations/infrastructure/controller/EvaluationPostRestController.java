package poc.ddd.demo.evaluations.infrastructure.controller;


import io.swagger.annotations.Api;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import poc.ddd.demo.evaluations.application.create.CreateEvaluationCommand;
import poc.ddd.demo.shared.domain.bus.command.CommandBus;
import poc.ddd.demo.shared.domain.bus.command.CommandHandlerExecutionError;
import poc.ddd.demo.shared.infrastructure.validation.Validator;

import java.io.Serializable;
import java.util.HashMap;

@RestController
@RequestMapping(value = "/evaluations")
@Api(tags = "Evaluations")
public final class EvaluationPostRestController {
    private final CommandBus bus;
    private final HashMap<String, String> rules = new HashMap<>() {{
        put("id", "required|not_empty|uuid");
        put("score", "required|not_empty|int");
        put("filmId", "required|not_empty|uuid");
    }};

    public EvaluationPostRestController(CommandBus bus) {
        this.bus = bus;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void postEvaluation(@RequestBody HashMap<String, Serializable> request) throws Exception {
        Validator.validate(request, rules);
        createEvaluation(request);
    }

    private void createEvaluation(HashMap<String, Serializable> request) throws CommandHandlerExecutionError {
        bus.dispatch(new CreateEvaluationCommand(
                request.get("id").toString(),
                Integer.valueOf(request.get("score").toString()),
                request.get("filmId").toString()
        ));
    }

}
