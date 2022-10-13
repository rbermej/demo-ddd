package poc.ddd.demo.films.application.search_by_criteria;

import poc.ddd.demo.films.application.FilmResponse;
import poc.ddd.demo.films.application.FilmsResponse;
import poc.ddd.demo.films.domain.FilmRepository;
import poc.ddd.demo.shared.domain.Service;
import poc.ddd.demo.shared.domain.criteria.Criteria;
import poc.ddd.demo.shared.domain.criteria.Filters;
import poc.ddd.demo.shared.domain.criteria.Order;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
public final class FilmsByCriteriaSearcher {
    private final FilmRepository repository;

    public FilmsByCriteriaSearcher(FilmRepository repository) {
        this.repository = repository;
    }

    public FilmsResponse search(Filters filters, Order order, Optional<Integer> limit, Optional<Integer> offset) {

        var criteria = new Criteria(filters, order, limit, offset);

        return new FilmsResponse(
                repository.matching(criteria)
                        .stream()
                        .map(FilmResponse::fromAggregate)
                        .collect(Collectors.toList())
        );
    }
}
