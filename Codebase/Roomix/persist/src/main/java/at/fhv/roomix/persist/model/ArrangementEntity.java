package at.fhv.roomix.persist.model;

import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "Arrangement", schema = "roomix", catalog = "")
public class ArrangementEntity {
    private int arrangementId;
    private int article;
    private ArticleEntity articleByArticle;
    private Collection<ReservationunitEntity> reservationunitsByArrangementId;

    @Id
    @Column(name = "ArrangementID")
    public int getArrangementId() {
        return arrangementId;
    }

    public void setArrangementId(int arrangementId) {
        this.arrangementId = arrangementId;
    }

    @Basic
    @Column(name = "Article", insertable = false, updatable = false)
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

    @org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @ManyToOne
    @JoinColumn(name = "Article", referencedColumnName = "ArticleID", nullable = false)
    public ArticleEntity getArticleByArticle() {
        return articleByArticle;
    }

    public void setArticleByArticle(ArticleEntity articleByArticle) {
        this.articleByArticle = articleByArticle;
    }

    @org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @OneToMany(mappedBy = "arrangementByArrangement")
    public Collection<ReservationunitEntity> getReservationunitsByArrangementId() {
        return reservationunitsByArrangementId;
    }

    public void setReservationunitsByArrangementId(Collection<ReservationunitEntity> reservationunitsByArrangementId) {
        this.reservationunitsByArrangementId = reservationunitsByArrangementId;
    }
}
