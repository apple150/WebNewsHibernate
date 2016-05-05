/**
 * Created by Sergey Buglak
 *
 * POJO NewsDetail - News (ONE-TO-ONE)
 */

package pvt.by.pojos;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Date;

/**
 * POJO NewsDetail
 */

@Entity
@Table(name="T_NEWS_DETAIL")
public class NewsDetail implements Serializable {
    private static final long serialVersionUID = -6425148682808323574L;

    //ONE-TO-ON with News (foreign key)
    @Id
    @OneToOne
    @JoinColumn(name="newsId")
    private News news; //ONE-TO-ON

    @Column(name="F_AUTHOR")
    private String author;

    @Column(name="F_AGENCY")
    private String agency;

    @Column(name="F_DRELEASE")
    private Date drelease;

    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAgency() {
        return agency;
    }
    public void setAgency(String agency) {
        this.agency = agency;
    }

    public Date getDrelease() {
        return drelease;
    }
    public void setDrelease(Date drelease) {
        this.drelease = drelease;
    }

    public News getNews() {
        return news;
    }
    public void setNews(News news) {
        this.news = news;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NewsDetail that = (NewsDetail) o;

        if (agency != null ? !agency.equals(that.agency) : that.agency != null) return false;
        if (author != null ? !author.equals(that.author) : that.author != null) return false;
        if (drelease != null ? !drelease.equals(that.drelease) : that.drelease != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = author != null ? author.hashCode() : 0;
        result = 31 * result + (agency != null ? agency.hashCode() : 0);
        result = 31 * result + (drelease != null ? drelease.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "NewsDetail{" +
                "agency='" + agency + '\'' +
                ", author='" + author + '\'' +
                ", drelease=" + drelease +
                '}';
    }
}
