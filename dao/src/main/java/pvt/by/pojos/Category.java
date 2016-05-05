/**
 * Created by Sergey Buglak
 *
 * POJO Category - News (MANY-TO-ONE)
 */

package pvt.by.pojos;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

/**
 * POJO Category
 */

@Entity
@Table(name = "T_CATEGORY")
public class Category implements Serializable {
    private static final long serialVersionUID = -7263917834303296047L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "categoryId", unique = true, nullable = false)
    private Integer categoryId;

    @Column(name = "F_CATEGORY_NAME")
    private String categoryName;

    //ONE-TO-MANY
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<News> news; //ONE-TO-MANY

    public Integer getCategoryId() {
        return categoryId;
    }
    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Set<News> getNews() {
        return news;
    }
    public void setNews(Set<News> news) {
        this.news = news;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Category category = (Category) o;

        if (categoryId != null ? !categoryId.equals(category.categoryId) : category.categoryId != null) return false;
        if (categoryName != null ? !categoryName.equals(category.categoryName) : category.categoryName != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = categoryId != null ? categoryId.hashCode() : 0;
        result = 31 * result + (categoryName != null ? categoryName.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Category{" +
                "categoryId=" + categoryId +
                ", categoryName='" + categoryName + '\'' +
                '}';
    }
}
