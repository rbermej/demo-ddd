package poc.ddd.demo.films.infrastructure;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import org.springframework.http.HttpStatus;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import poc.ddd.demo.films.application.FilmsResponse;
import poc.ddd.demo.films.application.search_by_criteria.SearchFilmsByCriteriaQuery;
import poc.ddd.demo.shared.domain.bus.query.QueryBus;
import poc.ddd.demo.shared.domain.bus.query.QueryHandlerExecutionError;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/films")
@Api(tags = "Films")
public final class FilmsGetRestController {

    private final QueryBus bus;

    public FilmsGetRestController(QueryBus bus) {
        this.bus = bus;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<HashMap<String, Object>> getFilms(
            //@RequestParam @ApiParam("") HashMap<String, Serializable> params
    ) throws QueryHandlerExecutionError {
        HashMap<String, Serializable> params = new HashMap<>(); //FIXME Remove when filters work
        FilmsResponse films = bus.ask(
                new SearchFilmsByCriteriaQuery(
                        parseFilters(params),
                        Optional.ofNullable((String) params.get("order_by")),
                        Optional.ofNullable((String) params.get("order")),
                        Optional.ofNullable((Integer) params.get("limit")),
                        Optional.ofNullable((Integer) params.get("offset"))
                )
        );

        return films.films().stream().map(response -> new HashMap<String, Object>() {{
            put("id", response.id());
            put("name", response.name());
            put("duration", response.duration());
            put("score", response.score());
        }}).collect(Collectors.toList());
    }

    private List<HashMap<String, String>> parseFilters(HashMap<String, Serializable> params) {
        int maxParams = params.size();

        List<HashMap<String, String>> filters = new ArrayList<>();

        for (int possibleFilterKey = 0; possibleFilterKey < maxParams; possibleFilterKey++) {
            if (params.containsKey(String.format("filters[%s][field]", possibleFilterKey))) {
                int key = possibleFilterKey;

                filters.add(new HashMap<String, String>() {{
                    put("field", (String) params.get(String.format("filters[%s][field]", key)));
                    put("operator", (String) params.get(String.format("filters[%s][operator]", key)));
                    put("value", (String) params.get(String.format("filters[%s][value]", key)));
                }});
            }
        }

        return filters;
    }
}
