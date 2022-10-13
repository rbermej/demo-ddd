package poc.ddd.demo.films.application.find;


import poc.ddd.demo.films.application.FilmResponse;
import poc.ddd.demo.films.domain.FilmId;
import poc.ddd.demo.films.domain.FilmNotExist;
import poc.ddd.demo.shared.domain.Service;
import poc.ddd.demo.shared.domain.bus.query.QueryHandler;

@Service
public class FindFilmQueryHandler implements QueryHandler<FindFilmQuery, FilmResponse> {
    private final FilmFinder finder;

    public FindFilmQueryHandler(FilmFinder finder) {
        this.finder = finder;
    }

    @Override
    public FilmResponse handle(FindFilmQuery query) throws FilmNotExist {
        return finder.find(new FilmId(query.id()));
    }
}
