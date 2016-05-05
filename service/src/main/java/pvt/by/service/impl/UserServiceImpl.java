/**
 * Created by Sergey Buglak
 */

package pvt.by.service.impl;

import org.hibernate.Transaction;
import pvt.by.dao.implement.UserDaoImpl;
import pvt.by.dao.interfaces.UserDao;
import pvt.by.pojos.User;
import pvt.by.service.interfaces.UserService;
import pvt.by.utils.HibernateUtil;

import java.util.List;

public class UserServiceImpl implements UserService {
    private static UserServiceImpl instance; // singleton
    private UserDao userDao = UserDaoImpl.getUserDao(); // load User DAO

    private UserServiceImpl() {}

    /**
     * Double-checked singleton
     */
    public static UserServiceImpl getUserService() {
        if (instance == null) {
            synchronized (UserServiceImpl.class) {
                if (instance == null) {
                    instance = new UserServiceImpl();
                }
            }
        }
        return instance;
    }

    /**
     * Look for user&password and returns true if found
     */
    @Override
    public boolean isLoginSuccess(String username, String pass) {
        Transaction tx = null;
        List<User> users = null;
        // users will be initialized as List or method returns false
        try {
            tx = HibernateUtil.getSession().beginTransaction();
            users = userDao.getAllUsers();
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            return false;
        } finally {
            HibernateUtil.closeSession();
        }
        // Iterate thru Users to find suitable
        for (User user : users) {
            if (user.getEmail().equals(username) && user.getPassword().equals(pass)) {
                return true;
            }
        }
        return false;
    }
}