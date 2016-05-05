/**
 * Created by Sergey Buglak
 */
package pvt.by.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.hibernate.Transaction;
import org.junit.Assert;
import org.junit.Test;
import pvt.by.dao.exceptions.DaoException;
import pvt.by.dao.implement.NewsDaoImpl;
import pvt.by.dao.interfaces.NewsDao;
import pvt.by.pojos.News;
import pvt.by.utils.HibernateUtil;

import java.util.List;

/**
 * News test
 */
public class NewsTestOld {
    private static final Logger logger = LogManager.getLogger(NewsTestOld.class);
    private NewsDao newsDao = NewsDaoImpl.getNewsDao();

    //CRUD operations with News
    @Test
    public void newsCrudTest() {
        Transaction tx = HibernateUtil.getSession().beginTransaction();
        try {
            newsDao.getNewsCount();
            tx.commit();
        } catch (DaoException e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            HibernateUtil.closeSession();
        }
        Assert.assertTrue(true);
    }

    @Test
    public void myTest() {
        NewsDaoImpl ud = NewsDaoImpl.getNewsDao();
        Transaction tx = null;
        try {
            tx = HibernateUtil.getSession().beginTransaction();
            List<News> ln = ud.getNewsAll();
            System.out.println(ln);
            tx.commit();
        } catch (DaoException e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            HibernateUtil.closeSession();
        }
        Assert.assertTrue(true);
    }
}

