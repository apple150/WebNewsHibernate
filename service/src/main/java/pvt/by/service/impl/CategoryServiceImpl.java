/**
 * Created by Sergey Buglak
 */

package pvt.by.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Transaction;
import pvt.by.dao.exceptions.DaoException;
import pvt.by.dao.implement.CategoryDaoImpl;
import pvt.by.dao.interfaces.CategoryDao;
import pvt.by.pojos.Category;
import pvt.by.service.interfaces.CategoryService;
import pvt.by.utils.HibernateUtil;

import java.util.ArrayList;
import java.util.List;

public class CategoryServiceImpl implements CategoryService {
    private static final String GET_CATEGORIES_EXCEPTION = "Get categories exception";
    private static CategoryServiceImpl instance; // singleton instance
    // Get log4j2 logger
    private static Logger logger = LogManager.getLogger(CategoryServiceImpl.class);

    private CategoryDao categoryDao;
    private CategoryServiceImpl() {
        categoryDao = CategoryDaoImpl.getCategoryDao();
    }

    /*
     * Double-checked singleton
     */
    public static CategoryServiceImpl getCategoryService() {
        if (instance == null) {
            synchronized (CategoryServiceImpl.class) {
                if (instance == null) {
                    instance = new CategoryServiceImpl();
                }
            }
        }
        return instance;
    }

    @Override
    public List<Category> getCategories() {
        Transaction tx = null;
        List<Category> categories = null;
        try {
            tx = HibernateUtil.getSession().beginTransaction();
            categories = categoryDao.getCategories();
            tx.commit();
            return categories;
        } catch (DaoException e) {
            tx.rollback();
            logger.error(GET_CATEGORIES_EXCEPTION);
            return new ArrayList<Category>();
        } finally {
            HibernateUtil.closeSession();
        }
    }
}
