/**
 * Created by Sergey Buglak
 */
package pvt.by.test;

import pvt.by.dao.exceptions.DaoException;
import pvt.by.dao.implement.*;
import pvt.by.pojos.News;
import pvt.by.utils.HibernateUtil;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Transaction;
import org.junit.Assert;
import org.junit.Test;

/**
 * News test
 */
public class NewsTest {
    // Get log4j2 logger
    private static final Logger logger = LogManager.getLogger(NewsTest.class);
    private static NewsDaoImpl newsDao = NewsDaoImpl.getNewsDao();
    private static NewsDetailDaoImpl newsDetailDao = NewsDetailDaoImpl.getNewsDetailDao();
    private static UserDaoImpl userDao = UserDaoImpl.getUserDao();
    private static CategoryDaoImpl categoryDao = CategoryDaoImpl.getCategoryDao();
    private static CommentDaoImpl commentDao = CommentDaoImpl.getCommentDao();
    private static TagDaoImpl tagDao = TagDaoImpl.getTagDao();

    //CRUD operations with News
    @Test
    public void newsCrudTest() {
        Transaction tx = null;
        News news = TestUtil.getNews();
        //Save News to base
        try {
            logger.info("Saving News to base: " + news);
            tx = HibernateUtil.getSession().beginTransaction();
            newsDao.saveOrUpdate(news);
            tx.commit();
            HibernateUtil.closeSession();
            logger.info("Saved OK");
        } catch (DaoException e) {
            tx.rollback();
            logger.error("Save News to DB exception.");
        } finally {
            tx = null;
        }

        //Get assigned id
        Integer newsId = news.getNewsId();

        //Then get News from DB and compare
        News newsFromDB = null;
        try {
            logger.info("Get News from DB: ");
            tx = HibernateUtil.getSession().beginTransaction();
            newsFromDB = newsDao.get(newsId);
            tx.commit();
            HibernateUtil.closeSession();
            logger.info("News from DB: " + newsFromDB);
        } catch (DaoException e) {
            tx.rollback();
            logger.error("Get News from DB exception");
        } finally {
            tx = null;
        }

        Assert.assertEquals("News not equals after save() and get()", news, newsFromDB);
        newsFromDB = null;

        //Update News
        String newText = "New Text";
        try {
            logger.info("Update News: ");
            tx = HibernateUtil.getSession().beginTransaction();
            news = newsDao.get(newsId);
            news.setDocument(newText);
            logger.info("Update by: " + news);
            newsDao.saveOrUpdate(news);
            tx.commit();
            HibernateUtil.closeSession();
        } catch (DaoException e) {
            tx.rollback();
            logger.error("Update News exception.");
        } finally {
            tx = null;
        }

        //Get updated News
        try {
            logger.info("Get updated News: ");
            tx = HibernateUtil.getSession().beginTransaction();
            newsFromDB = newsDao.get(newsId);
            tx.commit();
            logger.info("News from DB: " + newsFromDB);
            HibernateUtil.closeSession();
        } catch (DaoException e) {
            tx.rollback();
            logger.error("Get News after update exception");
        } finally {
            tx = null;
        }
        Assert.assertEquals("News not equals after update() and get()", news, newsFromDB);
        Assert.assertTrue("News has not been updated", newsFromDB.getDocument().equals(newText));

        //delete News
        try {
            tx = HibernateUtil.getSession().beginTransaction();
            newsDao.delete(news);
            tx.commit();
            HibernateUtil.closeSession();
            logger.info("News was successful deleted.");
        } catch (DaoException e) {
            tx.rollback();
            logger.error("Deleting News from DB exception.");
        } finally {
            tx = null;
        }
    }
}
