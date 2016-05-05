/**
 * Created by Sergey Buglak
 *
 * POJO Tag - News  (MANY-TO-MANY)
 */
package pvt.by.pojos;

import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * POJO Tag
 */

@Entity
@Table(name = "T_TAG")
public class Tag implements Serializable {
    private static final long serialVersionUID = -2021640385532286492L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
    @Column(name = "tagId", unique = true, nullable = false)
    private Integer tagId;

    @Column(name = "F_TAG_NAME")
    private String tagName;

    @ManyToMany(mappedBy = "tag")
    private Set<News> news = new HashSet<News>();

    public Set<News> getNews() {
        return news;
    }

    public void setNews(Set<News> news) {
        this.news = news;
    }

    public Integer getTagId() {
        return tagId;
    }
    public void setTagId(Integer tagId) {
        this.tagId = tagId;
    }

    public String getTagName() {
        return tagName;
    }
    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public Set<News> getTag() {
        return news;
    }
    public void setTag(Set<News> tag) {
        this.news = news;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tag tag1 = (Tag) o;

        if (tagId != null ? !tagId.equals(tag1.tagId) : tag1.tagId != null) return false;
        if (tagName != null ? !tagName.equals(tag1.tagName) : tag1.tagName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = tagId != null ? tagId.hashCode() : 0;
        result = 31 * result + (tagName != null ? tagName.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Tag{" +
                "tagId=" + tagId +
                ", tagName='" + tagName + '\'' +
                '}';
    }
}
