package poc.ddd.demo.films.domain;

import poc.ddd.demo.shared.domain.DomainError;

public final class FilmNotExist extends DomainError {
    public FilmNotExist(FilmId id) {
        super("film_not_exist", String.format("The film <%s> doesn't exist", id.value()));
    }
}
