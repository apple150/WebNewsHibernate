/**
 * Created by Sergey Buglak
 */

package pvt.by.dao.interfaces;

import pvt.by.dao.exceptions.DaoException;
import pvt.by.pojos.Tag;

import java.util.List;

/**
 * INTERFACE TagDao
 */
public interface TagDao extends Dao<Tag> {
    List<Tag> getTags() throws DaoException;
}
