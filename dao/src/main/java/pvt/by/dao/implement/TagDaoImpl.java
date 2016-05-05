/**
 * Created by Sergey Buglak
 */

package pvt.by.dao.implement;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import pvt.by.dao.exceptions.DaoException;
import pvt.by.dao.interfaces.TagDao;
import pvt.by.pojos.Tag;
import pvt.by.utils.HibernateUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Custom methods for TagDao
 */
public class TagDaoImpl extends BaseDao<Tag> implements TagDao {
    private static final String GET_TAGS_OK = "Get tags OK";
    private static final String GET_LIST_TAGS_BY_NEWS_ID_EXCEPTION = "Get List Tags by News Id Exception.";
    private static final String GET_LIST_TAGS_BY_NEWS_ID_OK = "Get List Tags by News Id OK.";
    private static final String GET_TAGS_EXCEPTION = "getTags() exception";
    private static TagDaoImpl instance;
    // Get log4j2 logger
    private static Logger logger = LogManager.getLogger(TagDaoImpl.class);

    private TagDaoImpl() {}

    /**
     * Get TagDaoImpl double-checked SINGLETON
     *
     * @return TagDaoImpl instance
     */
    public static TagDaoImpl getTagDao() {
        if (instance == null) {
            synchronized (TagDaoImpl.class) {
                if (instance == null) {
                    instance = new TagDaoImpl();
                }
            }
        }
        return instance;
    }

    /**
     * Get all Tags
     */
    @Override
    public List<Tag> getTags() throws DaoException {
        try {
            String hql = "FROM Tag";
            Query query = HibernateUtil.getSession().createQuery(hql);
            List<Tag> tags = query.list();
            logger.info(GET_TAGS_OK);
            return tags;
        } catch (HibernateException e) {
            logger.error(GET_TAGS_EXCEPTION);
            throw new DaoException(e);
        }
    }

    /**
     * Get Tags by id News
     *
     * @param idNews
     * @return List Of Tags
     * @throws DaoException
     */
    public List<Tag> getTagsByIdNews(Integer idNews) throws DaoException {
        try {
            String hql = "SELECT T.tagName FROM News N JOIN N.tag T WHERE N.newsId=:newsId";
            Query query = HibernateUtil.getSession().createQuery(hql);
            query.setParameter("newsId", idNews);
            // Get List of tags
            List<String> results = query.list();
            List<Tag> tags = new ArrayList<Tag>();
            // Map list of tagNames to List of Tags objects with initiaded
            // tagName
            for (String result : results) {
                Tag tag = new Tag();
                tag.setTagName(result);
                tags.add(tag);
            }
            logger.info(GET_LIST_TAGS_BY_NEWS_ID_OK);
            return tags;
        } catch (HibernateException e) {
            logger.error(GET_LIST_TAGS_BY_NEWS_ID_EXCEPTION);
            throw new DaoException(e);
        }
    }
}