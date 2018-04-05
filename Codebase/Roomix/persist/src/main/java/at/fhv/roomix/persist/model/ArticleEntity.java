package at.fhv.roomix.persist.model;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
@javax.persistence.Table(name = "article", schema = "roomix")
public class ArticleEntity {
    private int articleId;

    @Id
    @javax.persistence.Column(name = "ArticleID")
    public int getArticleId() {
        return articleId;
    }

    public void setArticleId(int articleId) {
        this.articleId = articleId;
    }

    private String articleDescription;

    @Basic
    @javax.persistence.Column(name = "ArticleDescription")
    public String getArticleDescription() {
        return articleDescription;
    }

    public void setArticleDescription(String articleDescription) {
        this.articleDescription = articleDescription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArticleEntity that = (ArticleEntity) o;
        return articleId == that.articleId &&
                Objects.equals(articleDescription, that.articleDescription);
    }

    @Override
    public int hashCode() {

        return Objects.hash(articleId, articleDescription);
    }
}
