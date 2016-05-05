/**
 * Created by Sergey Buglak
 */

package pvt.by.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Transaction;
import pvt.by.dao.exceptions.DaoException;
import pvt.by.dao.implement.TagDaoImpl;
import pvt.by.dao.interfaces.TagDao;
import pvt.by.pojos.Tag;
import pvt.by.service.interfaces.TagService;
import pvt.by.utils.HibernateUtil;

import java.util.ArrayList;
import java.util.List;

public class TagServiceImpl implements TagService {

    private static final String GET_TAGS_EXCEPTION = "getTags() exception";
    private static final String GET_TAGS_OK = "getTags() OK";
    private static TagServiceImpl instance; // singleton
    // Get log4j2 logger
    private static Logger logger = LogManager.getLogger(TagServiceImpl.class);
    private TagDao tagDao;

    private TagServiceImpl() {
        tagDao = TagDaoImpl.getTagDao(); // load Tag DAO
    }

    /*
     * Double-checked singleton
     */
    public static TagServiceImpl getTagService() {
        if (instance == null) {
            synchronized (TagServiceImpl.class) {
                if (instance == null) {
                    instance = new TagServiceImpl();
                }
            }
        }
        return instance;
    }

    @Override
    public List<Tag> getTags() {
        Transaction tx = null;
        List<Tag> tags;
        try {
            tx = HibernateUtil.getSession().beginTransaction();
            tags = tagDao.getTags();
            tx.commit();
            logger.info(GET_TAGS_OK);
            return tags;
        } catch (DaoException e) {
            logger.error(GET_TAGS_EXCEPTION);
            return new ArrayList<Tag>();
        } finally {
            HibernateUtil.closeSession();
        }
    }
}
