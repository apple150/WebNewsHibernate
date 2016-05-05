/**
 * Created by Sergey Buglak
 */

package pvt.by.dao.implement;

import pvt.by.dao.interfaces.CommentDao;
import pvt.by.pojos.Comment;

/**
 * Custom methods for Comments
 */
public class CommentDaoImpl extends BaseDao<Comment> implements CommentDao {
    private static CommentDaoImpl instance;

    private CommentDaoImpl() {
    }

    /**
     * Get CommentDao double-checked SINGLETON
     *
     * @return CommentDao instance
     */
    public static CommentDaoImpl getCommentDao() {
        if (instance == null) {
            synchronized (CommentDaoImpl.class) {
                if (instance == null) {
                    instance = new CommentDaoImpl();
                }
            }
        }
        return instance;
    }
}
