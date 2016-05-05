/**
 * Created by Sergey Buglak
 */

package pvt.by.dao.interfaces;

import pvt.by.dao.exceptions.DaoException;
import pvt.by.pojos.News;

import java.util.List;

/**
 * INTERFACE NewsDao
 */
public interface NewsDao extends Dao<News> {
    List<News> getNewsRows(int maxResult, int startPosition, String columnName, String sortOrder)
            throws DaoException;
    List<News> getNewsAll() throws DaoException;
    Long getNewsCount() throws DaoException;
}
