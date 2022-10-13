package poc.ddd.demo.evaluations.application.create;

import poc.ddd.demo.shared.domain.bus.command.Command;

public final class CreateEvaluationCommand implements Command {
    private final String id;
    private final Integer score;
    private final String filmId;

    public CreateEvaluationCommand(String id, Integer score, String filmId) {
        this.id = id;
        this.score = score;
        this.filmId = filmId;
    }

    public String id() {
        return id;
    }

    public Integer score() {
        return score;
    }

    public String filmId() {
        return filmId;
    }
}
