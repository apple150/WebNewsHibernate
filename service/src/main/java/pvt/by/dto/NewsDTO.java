/**
 * Created by Sergey Buglak
 */

package pvt.by.dto;

import java.util.Arrays;
import java.util.Date;

public class NewsDTO {
    private String idNews;
    private String header;
    private String text;
    private String category;
    private String[] tags;
    private Date date;
    private String user;
    private String agency;
    private String author;

    public String getIdNews() {
        return idNews;
    }

    public void setIdNews(String idNews) {
        this.idNews = idNews;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String[] getTags() {
        return tags;
    }

    public void setTags(String[] tags) {
        this.tags = tags;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getAgency() {
        return agency;
    }

    public void setAgency(String agency) {
        this.agency = agency;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "NewsDTO{" +
                "idNews='" + idNews + '\'' +
                ", header='" + header + '\'' +
                ", text='" + text + '\'' +
                ", category='" + category + '\'' +
                ", tags=" + Arrays.toString(tags) +
                ", date=" + date +
                ", user='" + user + '\'' +
                ", agency='" + agency + '\'' +
                ", author='" + author + '\'' +
                '}';
    }
}
