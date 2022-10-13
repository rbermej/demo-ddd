package poc.ddd.demo.evaluations.infrastructure.persistence;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name="evaluations")
public class EvaluationEntity {

    @Id
    @Column(length = 36, nullable = false)
    private String id;

    @Column(nullable = false, scale = 2)
    private Integer score;

    @Column(name = "film_id", length = 36, nullable = false)
    private String filmId;

    public EvaluationEntity() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getFilmId() {
        return filmId;
    }

    public void setFilmId(String filmId) {
        this.filmId = filmId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EvaluationEntity that = (EvaluationEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(score, that.score) && Objects.equals(filmId, that.filmId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, score, filmId);
    }
}
