/**
 * Created by Sergey Buglak
 */

package pvt.by.dao.implement;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;
import pvt.by.dao.exceptions.DaoException;
import pvt.by.dao.interfaces.UserDao;
import pvt.by.pojos.User;
import pvt.by.utils.HibernateUtil;

import java.util.List;

/**
 * Custom methods for User
 */
public class UserDaoImpl extends BaseDao<User> implements UserDao {
    private static final String GET_ALL_USERS_OK = "getAllUsers() Ok";
    private static final String GET_ALL_USERS_EXCEPTION = "getAllUsers() exception";
    private static final String GET_USER_BY_NAME_EXCEPTION = "getUserByName() Exception";
    private static final String GET_USER_BY_NAME_OK = "getUserByName() OK";
    private static UserDaoImpl instance;
    // Get log4j2 logger
    private static Logger logger = LogManager.getLogger(UserDaoImpl.class);

    private UserDaoImpl() {}

    /**
     * Get UserDaoImpl double-checked SINGLETON
     *
     * @return UserDaoImpl instance
     */
    public static UserDaoImpl getUserDao() {
        if (instance == null) {
            synchronized (UserDaoImpl.class) {
                if (instance == null) {
                    instance = new UserDaoImpl();
                }
            }
        }
        return instance;
    }

    /**
     * Get User by username
     */
    @SuppressWarnings("unchecked")
    @Override
    public User getUserByName(String username) throws DaoException {
        Criteria criteria = HibernateUtil.getSession().createCriteria(User.class);
        criteria.add(Restrictions.eq("email", username));
        try {
            List<User> users = (List<User>) criteria.list();
            logger.info(GET_USER_BY_NAME_OK);
            return users.get(0);
        } catch (HibernateException he) {
            logger.error(GET_USER_BY_NAME_EXCEPTION);
            throw new DaoException(he);
        }
    }

    /**
     * Get User by username
     */
    @SuppressWarnings("unchecked")
    @Override
    public User getUserByEmail(String email) throws DaoException {
        Criteria criteria = HibernateUtil.getSession().createCriteria(User.class);
        criteria.add(Restrictions.eq("email", email));
        try {
            List<User> users = (List<User>) criteria.list();
            //2015.03.26 Comment Yuliy Slabko
            //If param is uniq than we should use uniqueResult() like in this example
            //List<User> users = (List<User>) criteria.uniqueResult();
            logger.info(GET_USER_BY_NAME_OK);
            return users.get(0);
        } catch (HibernateException he) {
            logger.error(GET_USER_BY_NAME_EXCEPTION);
            throw new DaoException(he);
        }
    }

    /**
     * Get all Users
     *
     * @return returns List of all Users
     * @throws DaoException
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<User> getAllUsers() throws DaoException {
        Criteria criteria = HibernateUtil.getSession().createCriteria(User.class);
        try {
            List<User> users = (List<User>) criteria.list();
            logger.info(GET_ALL_USERS_OK);
            return users;
        } catch (HibernateException he) {
            logger.error(GET_ALL_USERS_EXCEPTION);
            throw new DaoException(he);
        }
    }
}
