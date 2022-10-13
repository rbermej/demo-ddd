package poc.ddd.demo.evaluations.infrastructure.controller;

import io.swagger.annotations.Api;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import poc.ddd.demo.evaluations.application.EvaluationResponse;
import poc.ddd.demo.evaluations.application.find.FindEvaluationQuery;
import poc.ddd.demo.shared.domain.bus.query.QueryBus;
import poc.ddd.demo.shared.domain.bus.query.QueryHandlerExecutionError;

@RestController
@RequestMapping(value = "/evaluations")
@Api(tags = "Evaluations")
public final class EvaluationGetRestController {
    private final QueryBus bus;

    public EvaluationGetRestController(QueryBus bus) {
        this.bus = bus;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public Response getEvaluation(@PathVariable String id) throws QueryHandlerExecutionError {
        EvaluationResponse evaluationResponse = bus.ask(new FindEvaluationQuery(id));

        return new Response(evaluationResponse);
    }

    class Response {
        private final String id;
        private final Float score;
        private final String filmId;

        public Response(EvaluationResponse film) {
            this.id = film.id();
            this.score = film.score();
            this.filmId = film.filmId();
        }

        public String getId() {
            return id;
        }

        public Float getScore() {
            return score;
        }

        public String getFilmId() {
            return filmId;
        }
    }

}
