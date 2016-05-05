/**
 * Created by Sergey Buglak
 */

package pvt.by.dao.implement;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;

import pvt.by.dao.exceptions.DaoException;
import pvt.by.dao.interfaces.NewsDao;
import pvt.by.pojos.News;
import pvt.by.utils.HibernateUtil;

import java.util.List;

/*
 * Custom methods for News
 */
public class NewsDaoImpl extends BaseDao<News> implements NewsDao {
    private static final String GET_NEWS_COUNT_EXCEPTION = "Get News count exception.";
    private static final String GET_NEWS_COUNT_OK = "Get News count OK.";
    private static final String GET_ALL_NEWS_EXCEPTION = "Get All News exception.";
    private static final String GET_ALL_NEWS_OK = "Get All News OK.";
    private static NewsDaoImpl instance;
    // Get log4j2 logger
    private static Logger logger = LogManager.getLogger(NewsDaoImpl.class);

    private NewsDaoImpl() {}

    /**
     * Get NewsDaoImpl double-checked SINGLETON
     *
     * @return NewsDaoImpl instance
     */
    public static NewsDaoImpl getNewsDao() {
        if (instance == null) {
            synchronized (NewsDaoImpl.class) {
                if (instance == null) {
                    instance = new NewsDaoImpl();
                }
            }
        }
        return instance;
    }

    /**
     * Get "maxResult count News starts with startPosition"
     *
     * @param columnName - name of the column by which will be sorted
     * @param sortOrder  - "asc" for ascending or "desc" for "descending
     * @return List of News
     * @throws DaoException
     */
    @Override
    public List<News> getNewsRows(int maxResult, int startPosition,
                                  String columnName, String sortOrder) throws DaoException {
        String hql = "FROM News ORDER BY " + columnName + " " + sortOrder;
        Query query = HibernateUtil.getSession().createQuery(hql);
        // Pagination
        query.setFirstResult(startPosition);
        query.setMaxResults(maxResult);
        try {
            List<News> news = query.list();
            logger.info(GET_ALL_NEWS_OK);
            return news;
        } catch (HibernateException e) {
            logger.info(GET_ALL_NEWS_EXCEPTION);
            throw new DaoException(e);
        }
    }

    @Override
    public List<News> getNewsAll() throws DaoException {
        try {
            String hql = "FROM News";
//            String hql = "FROM News ORDER BY newsId ASC";
            Query query = HibernateUtil.getSession().createQuery(hql);
            List<News> results = query.list();
            logger.info(GET_NEWS_COUNT_OK);
            return results;
        } catch (HibernateException e) {
            logger.error(GET_NEWS_COUNT_EXCEPTION);
            throw new DaoException(e);
        }
    }

    /**
     * Get total count of news
     *
     * @return News count
     * @throws DaoException
     */
    @Override
    public Long getNewsCount() throws DaoException {
        try {
            String hql = "SELECT count(N) FROM News N";
            Query query = HibernateUtil.getSession().createQuery(hql);
            Long results = (Long) query.uniqueResult();
            logger.info(GET_NEWS_COUNT_OK);
            return results;
        } catch (HibernateException e) {
            logger.error(GET_NEWS_COUNT_EXCEPTION);
            throw new DaoException(e);
        }
    }
}