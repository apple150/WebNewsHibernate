/**
 * Created by Sergey Buglak
 */
package pvt.by.dao.interfaces;

import pvt.by.dao.exceptions.DaoException;
import pvt.by.pojos.Category;

import java.util.List;

/**
 * INTERFACE CategoryDao
 */
public interface CategoryDao extends Dao<Category> {
    List<Category> getCategories() throws DaoException;
}