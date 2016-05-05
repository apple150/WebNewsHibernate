/**
 * Created by Sergey Buglak
 *
 * POJO Comment
 */
package pvt.by.pojos;

import javax.persistence.*;
import java.io.Serializable;

/**
 * POJO Comment
 */

@Entity
@Table(name = "T_COMMENT")
public class Comment implements Serializable {
    private static final long serialVersionUID = 9104176647932545106L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "commentId", unique = true, nullable = false)
    private Integer commentId;

    @Column(name = "F_COMMENT")
    private String comment;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "F_USER_ID")
    private User user;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "F_NEWS_ID")
    private News news;

    public Integer getCommentId() {
        return commentId;
    }
    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public String getComment() {
        return comment;
    }
    public void setComment(String comment) {
        this.comment = comment;
    }

    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
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

        Comment comment1 = (Comment) o;

        if (comment != null ? !comment.equals(comment1.comment) : comment1.comment != null) return false;
        if (commentId != null ? !commentId.equals(comment1.commentId) : comment1.commentId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = commentId != null ? commentId.hashCode() : 0;
        result = 31 * result + (comment != null ? comment.hashCode() : 0);

        return result;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "commentId=" + commentId +
                ", comment='" + comment + '\'' +
                '}';
    }
}
