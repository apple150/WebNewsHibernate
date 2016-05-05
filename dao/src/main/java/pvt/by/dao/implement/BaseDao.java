/**
 * Created by Sergey Buglak
 */

package pvt.by.dao.implement;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import pvt.by.dao.exceptions.DaoException;
import pvt.by.dao.interfaces.Dao;
import pvt.by.utils.HibernateUtil;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

/**
 * CRUD DAO operations
 */
public class BaseDao<T> implements Dao<T> {
    private static final String SAVE_OR_UPDATE_OK = "saveOrUpdate(t): OK";
    private static final String ERROR_SAVE_OR_UPDATE = "Error saveOrUpdate NEWS in Dao";
    private static final String GET_ID_OK = "get(id) OK";
    private static final String ERROR_GET_ID = "Error get(id)";
    private static final String LOAD_ID_OK = "load(id) OK";
    private static final String ERROR_LOAD_ID = "Error load ID";
    private static final String DELETE_OK = "delete(t) OK";
    private static final String ERROR_DELETE = "Error delete(t)";
    // Get log4j2 logger
    private static Logger logger = LogManager.getLogger(BaseDao.class);

    public BaseDao() {
    }

    @Override
    public void saveOrUpdate(T t) throws DaoException {
        try {
            HibernateUtil.getSession().saveOrUpdate(t);
            logger.info(SAVE_OR_UPDATE_OK);
        } catch (HibernateException e) {
            e.printStackTrace();
            logger.error(ERROR_SAVE_OR_UPDATE);
            throw new DaoException(e);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public T get(Serializable id) throws DaoException {
        T t = null;
        try {
            t = (T) HibernateUtil.getSession().get(getPersistentClass(), id);
            logger.info(GET_ID_OK);
        } catch (HibernateException e) {
            logger.error(ERROR_GET_ID);
            throw new DaoException(e);
        }
        return t;
    }

    @SuppressWarnings("unchecked")
    @Override
    public T load(Serializable id) throws DaoException {
        T t = null;
        try {
            t = (T) HibernateUtil.getSession().load(getPersistentClass(), id);
            logger.info(LOAD_ID_OK);
        } catch (HibernateException e) {
            logger.error(ERROR_LOAD_ID);
            throw new DaoException(e);
        }
        return t;
    }

    @Override
    public void delete(T t) throws DaoException {
        try {
            HibernateUtil.getSession().delete(t);
            logger.info(DELETE_OK);
        } catch (HibernateException e) {
            logger.error(ERROR_DELETE);
            throw new DaoException(e);
        }
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    private Class getPersistentClass() {
        return (Class<T>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0];
    }
}