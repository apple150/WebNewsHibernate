/**
 * Created by Sergey Buglak
 */

package pvt.by.dao.implement;

import pvt.by.dao.interfaces.NewsDetailDao;
import pvt.by.pojos.NewsDetail;

/**
 * Custom methods for NewsDetail
 */
public class NewsDetailDaoImpl extends BaseDao<NewsDetail> implements NewsDetailDao {
    private static NewsDetailDaoImpl instance;

    private NewsDetailDaoImpl() {}

    /**
     * Get NewsDetailDao double-checked SINGLETON
     *
     * @return NewsDetailDao instance
     */

    public static NewsDetailDaoImpl getNewsDetailDao() {
        if (instance == null) {
            synchronized (NewsDetailDaoImpl.class) {
                if (instance == null) {
                    instance = new NewsDetailDaoImpl();
                }
            }
        }
        return instance;
    }
}