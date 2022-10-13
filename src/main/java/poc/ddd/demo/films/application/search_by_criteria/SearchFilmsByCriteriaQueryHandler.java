package poc.ddd.demo.films.application.search_by_criteria;

import poc.ddd.demo.films.application.FilmsResponse;
import poc.ddd.demo.shared.domain.Service;
import poc.ddd.demo.shared.domain.bus.query.QueryHandler;
import poc.ddd.demo.shared.domain.criteria.Filters;
import poc.ddd.demo.shared.domain.criteria.Order;

@Service
public final class SearchFilmsByCriteriaQueryHandler implements QueryHandler<SearchFilmsByCriteriaQuery, FilmsResponse> {
    private final FilmsByCriteriaSearcher searcher;

    public SearchFilmsByCriteriaQueryHandler(FilmsByCriteriaSearcher searcher) {
        this.searcher = searcher;
    }

    @Override
    public FilmsResponse handle(SearchFilmsByCriteriaQuery query) {
        Filters filters = Filters.fromValues(query.filters());
        Order order = Order.fromValues(query.orderBy(), query.orderType());

        return searcher.search(filters, order, query.limit(), query.offset());
    }
}
