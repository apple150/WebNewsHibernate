/**
 * Created by Sergey Buglak
 *
 * POJO News - User (MANY-TO-ONE)
 */

package pvt.by.pojos;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * POJO News
 */

@Entity
@Table(name = "T_NEWS")
public class News implements Serializable {
    private static final long serialVersionUID = 6925953737671604837L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "newsId", unique = true, nullable = false)
    private Integer newsId;

    @Column(name = "F_TITLE")
    private String title;

    @Column(name = "F_ANNOTATION")
    private String annotation;

    @Column(name = "F_DOCUMENT")
    private String document;

    //ONE-TO-ONE
    @OneToOne(mappedBy = "news", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private NewsDetail newsDetail;  //ONE-TO-ONE

    //MANY-TO-ONE
    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "F_CATEGORY_ID")
    private Category category;      //MANY-TO-ONE

    //MANY-TO-ONE
    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "F_USER_ID")
    private User user;              //MANY-TO-ONE

    //ONE-TO-MANY
    @OneToMany(mappedBy = "news" , cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<Comment> comment;   //ONE-TO-MANY

    //MANY-TO-MANY
    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name="T_NEWS_TAG", joinColumns = @JoinColumn(name = "F_NEWS_ID"), inverseJoinColumns = @JoinColumn(name = "F_TAG_ID"))
    private Set<Tag> tag = new HashSet<Tag>(); //MANY-TO-MANY

    public Integer getNewsId() {
        return newsId;
    }
    public void setNewsId(Integer newsId) {
        this.newsId = newsId;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getAnnotation() {
        return annotation;
    }
    public void setAnnotation(String annotation) {
        this.annotation = annotation;
    }

    public String getDocument() {
        return document;
    }
    public void setDocument(String document) {
        this.document = document;
    }

    public NewsDetail getNewsDetail() {
        return newsDetail;
    }
    public void setNewsDetail(NewsDetail newsDetail) {
        this.newsDetail = newsDetail;
    }

    public Category getCategory() {
        return category;
    }
    public void setCategory(Category category) {
        this.category = category;
    }

    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }

    public Set<Tag> getTag() {
        return tag;
    }
    public void setTag(Set<Tag> tag) {
        this.tag = tag;
    }

    public Set<Comment> getComment() {
        return comment;
    }
    public void setComment(Set<Comment> comment) {
        this.comment = comment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        News news = (News) o;

        if (newsId != null ? !newsId.equals(news.newsId) : news.newsId != null) return false;
        if (title != null ? !title.equals(news.title) : news.title != null) return false;
        if (annotation != null ? !annotation.equals(news.annotation) : news.annotation != null) return false;
        if (document != null ? !document.equals(news.document) : news.document != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = newsId != null ? newsId.hashCode() : 0;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (annotation != null ? annotation.hashCode() : 0);
        result = 31 * result + (document != null ? document.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "News{" +
                "newsId=" + newsId +
                ", title='" + title + '\'' +
                ", annotation='" + annotation + '\'' +
                ", document='" + document + '\'' +
                '}';
    }
}
