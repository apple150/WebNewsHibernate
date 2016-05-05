/**
 * Created by Sergey Buglak
 */

package pvt.by.dao.implement;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import pvt.by.dao.exceptions.DaoException;
import pvt.by.dao.interfaces.CategoryDao;
import pvt.by.pojos.Category;
import pvt.by.utils.HibernateUtil;

import java.util.List;

/**
 * Custom methods for Category
 */
public class CategoryDaoImpl extends BaseDao<Category> implements CategoryDao {
    private static final String GET_ALL_CATEGORIES_EXCEPTION = "Get all categories exception";

    private static final String GET_ALL_CATEGORIES_OK = "Get all categories OK";

    private static CategoryDaoImpl instance;

    // Get log4j2 logger
    private static Logger logger = LogManager.getLogger(CategoryDaoImpl.class);

    private CategoryDaoImpl() {}

    /**
     * Get CategoryDaoImpl double-checked SINGLETON
     *
     * @return CategoryDaoImpl instance
     */
    public static CategoryDaoImpl getCategoryDao() {
        if (instance == null) {
            synchronized (CategoryDaoImpl.class) {
                if (instance == null) {
                    instance = new CategoryDaoImpl();
                }
            }
        }
        return instance;
    }

    @Override
    public List<Category> getCategories() throws DaoException {
        String hql = "FROM Category";
        Query query = HibernateUtil.getSession().createQuery(hql);
        try {
            List<Category> categories = query.list();
            logger.info(GET_ALL_CATEGORIES_OK);
            return categories;
        } catch (HibernateException e) {
            logger.info(GET_ALL_CATEGORIES_EXCEPTION);
            throw new DaoException(e);
        }
    }
}
