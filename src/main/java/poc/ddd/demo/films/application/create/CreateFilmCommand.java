package poc.ddd.demo.films.application.create;

import poc.ddd.demo.shared.domain.bus.command.Command;

public final class CreateFilmCommand implements Command {
    private final String id;
    private final String name;
    private final int duration;

    public CreateFilmCommand(String id, String name, int duration) {
        this.id = id;
        this.name = name;
        this.duration = duration;
    }

    public String id() {
        return id;
    }

    public String name() {
        return name;
    }

    public int duration() {
        return duration;
    }
}
