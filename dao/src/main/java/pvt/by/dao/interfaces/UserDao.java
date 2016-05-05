/**
 * Created by Sergey Buglak
 */

package pvt.by.dao.interfaces;

import pvt.by.dao.exceptions.DaoException;
import pvt.by.pojos.User;

import java.util.List;

/**
 * INTERFACE UserDao
 */
public interface UserDao extends Dao<User> {
    List<User> getAllUsers() throws DaoException;
    User getUserByName(String username) throws DaoException;
    User getUserByEmail(String email) throws DaoException;
}