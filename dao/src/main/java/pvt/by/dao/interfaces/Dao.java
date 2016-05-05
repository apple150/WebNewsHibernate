/**
 * Created by Sergey Buglak
 */

package pvt.by.dao.interfaces;

import pvt.by.dao.exceptions.DaoException;

import java.io.Serializable;

/**
 * INTERFACE Dao with CRUD generic interface
 */
public interface Dao<T> {
    void saveOrUpdate(T t) throws DaoException;
    T get(Serializable id) throws DaoException;
    T load(Serializable id) throws DaoException;
    void delete(T t) throws DaoException;
}