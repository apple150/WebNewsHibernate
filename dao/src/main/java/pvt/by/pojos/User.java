/**
 * Created by Sergey Buglak
 *
 * POJO User - News (ONE-TO-MANY)
 */

package pvt.by.pojos;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * POJO User
 */

@Entity
//@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
@Table(name = "T_USER")
public class User implements Serializable {
    private static final long serialVersionUID = 5770564891438688918L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userId", unique=true, nullable = false)
    private Integer userId;

    @Column(name = "F_NAME")
    private String name;

    @Column(name = "F_SUR_NAME")
    private String surName;

    @Column(name = "F_EMAIL")
    private String email;

    @Column(name = "F_PASSWORD")
    private String password;

    //MANY-TO-ONE
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "F_ROLE_ID")
    private Role role;      //MANY-TO-ONE

    //ONE-TO-MANY
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<News> news = new HashSet<>(); //ONE-TO-MANY

    //ONE-TO-MANY
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Comment> comment = new HashSet<>(); //ONE-TO-MANY

    public Integer getUserId() {
        return userId;
    }
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getSurName() {
        return surName;
    }
    public void setSurName(String surName) {
        this.surName = surName;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }
    public void setRole(Role role) {
        this.role = role;
    }

    public Set<News> getNews() {
        return news;
    }
    public void setNews(Set<News> news) {
        this.news = news;
    }

    public Set<Comment> getComment() {
        return comment;
    }
    public void setComment(Set<Comment> comment) {
        this.comment = comment;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (email != null ? !email.equals(user.email) : user.email != null) return false;
        if (name != null ? !name.equals(user.name) : user.name != null) return false;
        if (password != null ? !password.equals(user.password) : user.password != null) return false;
        if (surName != null ? !surName.equals(user.surName) : user.surName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (surName != null ? surName.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", name='" + name + '\'' +
                ", surName='" + surName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
