/**
 * Created by Sergey Buglak
 */

package pvt.by.dto;

import java.util.Date;

public class CommentDTO {
    private String idComm;
    private String text;
    private Date commentDate;
    private String idNews;
    private String user;
    
    public String getIdComm() {
        return idComm;
    }
    public void setIdComm(String idComm) {
        this.idComm = idComm;
    }
    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }
    public Date getCommentDate() {
        return commentDate;
    }
    public void setCommentDate(Date commentDate) {
        this.commentDate = commentDate;
    }
    public String getIdNews() {
        return idNews;
    }
    public void setIdNews(String idNews) {
        this.idNews = idNews;
    }
    public String getUser() {
        return user;
    }
    public void setUser(String user) {
        this.user = user;
    }
}
