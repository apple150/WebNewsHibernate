/**
 * Created by Sergey Buglak
 */

package pvt.by;

import org.hibernate.Session;
import org.hibernate.Transaction;
import pvt.by.dao.exceptions.DaoException;
import pvt.by.dao.implement.NewsDaoImpl;
import pvt.by.dao.implement.UserDaoImpl;
import pvt.by.pojos.News;
import pvt.by.pojos.User;
import pvt.by.utils.HibernateUtil;

import java.util.List;

/**
 * Created for Tests
 */
public class MainClass {
    public static void main(String[] args) {
        HibernateUtil util = HibernateUtil.getHibernateUtil();
        NewsDaoImpl ud = NewsDaoImpl.getNewsDao();
        Transaction tx = null;
        try {
            tx = HibernateUtil.getSession().beginTransaction();
            List<News> ln = ud.getNewsAll();
            tx.commit();
        } catch (DaoException e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            HibernateUtil.closeSession();
        }
    }
}
