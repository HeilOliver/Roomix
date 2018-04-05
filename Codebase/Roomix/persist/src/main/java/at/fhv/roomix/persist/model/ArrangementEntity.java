package at.fhv.roomix.persist.model;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
@javax.persistence.Table(name = "arrangement", schema = "roomix")
public class ArrangementEntity {
    private int arrangementId;

    @Id
    @javax.persistence.Column(name = "ArrangementID")
    public int getArrangementId() {
        return arrangementId;
    }

    public void setArrangementId(int arrangementId) {
        this.arrangementId = arrangementId;
    }

    private int article;

    @Basic
    @javax.persistence.Column(name = "Article")
    public int getArticle() {
        return article;
    }

    public void setArticle(int article) {
        this.article = article;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArrangementEntity that = (ArrangementEntity) o;
        return arrangementId == that.arrangementId &&
                article == that.article;
    }

    @Override
    public int hashCode() {

        return Objects.hash(arrangementId, article);
    }
}
