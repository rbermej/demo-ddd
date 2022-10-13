package poc.ddd.demo.films.infrastructure.persistence;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name="films")
public class FilmEntity {

    @Id
    @Column(length = 36, nullable = false)
    private String id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int duration;

    @Column(nullable = false, precision = 2)
    private double score;

    public FilmEntity() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FilmEntity that = (FilmEntity) o;
        return duration == that.duration && Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(score, that.score);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, duration, score);
    }
}
