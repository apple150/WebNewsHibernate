/**
 * Created by Sergey Buglak
 */

package pvt.by.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Transaction;
import pvt.by.dao.exceptions.DaoException;
import pvt.by.dao.implement.CommentDaoImpl;
import pvt.by.dao.implement.NewsDaoImpl;
import pvt.by.dao.implement.UserDaoImpl;
import pvt.by.dao.interfaces.CommentDao;
import pvt.by.dao.interfaces.NewsDao;
import pvt.by.dao.interfaces.UserDao;
import pvt.by.dto.CommentDTO;
import pvt.by.pojos.Comment;
import pvt.by.pojos.News;
import pvt.by.pojos.User;
import pvt.by.service.interfaces.CommentService;
import pvt.by.util.ServiceUtil;
import pvt.by.utils.HibernateUtil;

public class CommentServiceImpl implements CommentService {

    private static final String DELETE_COMMENT_EXCEPTION = "deleteComment() exception";
    private static final String ID_IS_NOT_VALID = "Id is not valid";
    private static final String COMMENT_NOT_FOUND = "Comment not found";
    private static final String ADD_COMMENT_EXCEPTION = "addComment() exception";
    private static final String COMMENT_WAS_ADDED_OK = "Comment was added OK";
    private static final String GET_COMMENT_EXCEPTION = "getComment() exception";
    private static CommentServiceImpl instance; // singleton instance
    // Get log4j2 logger
    private static Logger logger = LogManager.getLogger(CommentServiceImpl.class);
    private CommentDao commentDao = CommentDaoImpl.getCommentDao();
    private NewsDao newsDao = NewsDaoImpl.getNewsDao();
    private UserDao userDao = UserDaoImpl.getUserDao();

    private CommentServiceImpl() {
    }

    /*
     * Double-checked singleton
     */
    public static CommentServiceImpl getCommentService() {
        if (instance == null) {
            synchronized (CommentServiceImpl.class) {
                if (instance == null) {
                    instance = new CommentServiceImpl();
                }
            }
        }
        return instance;
    }

    @Override
    public Comment getComment(String idComment) throws Exception {
        Integer id;
        try {
            id = ServiceUtil.stringToPositiveInt(idComment);
        } catch (NumberFormatException e) {
            logger.error(ID_IS_NOT_VALID);
            throw new Exception(e);
        }

        Transaction tx = null;
        Comment comment;
        try {
            tx = HibernateUtil.getSession().beginTransaction();
            comment = commentDao.get(id);
            tx.commit();
            // Check for null
            if (comment != null) {
                return comment;
            } else {
                logger.error(COMMENT_NOT_FOUND + id);
                throw new Exception();
            }
        } catch (DaoException e) {
            logger.error(GET_COMMENT_EXCEPTION);
            tx.rollback();
            throw new Exception(e);
        } finally {
            HibernateUtil.closeSession();
        }
    }

    /**
     * Save or update comment from DTO
     *
     * @throws Exception
     */
    @Override
    public void saveComment(CommentDTO commentDto) throws Exception {
        Integer idNews;
        // Check if id is integer
        try {
            idNews = ServiceUtil.stringToPositiveInt(commentDto.getIdNews());
        } catch (NumberFormatException e) {
            logger.error(ID_IS_NOT_VALID);
            throw new Exception(e);
        }

        Integer idComm;
        // Check if id comment is integer
        try {
            idComm = ServiceUtil.stringToPositiveInt(commentDto.getIdComm());
        } catch (NumberFormatException e) {
            idComm = null;
        }

        // Check for not null text, username
        String text = commentDto.getText();
        String username = commentDto.getUser();
        if (text == null || username == null) {
            return;
        }

        // Add comment
        Transaction tx = null;
        try {
            tx = HibernateUtil.getSession().beginTransaction();
            Comment comment;

            // Try to get comment by it id
            if (idComm != null) {
                // If exist update text and date
                comment = commentDao.get(idComm);
            } else {
                // If not, set to news and user
                comment = new Comment();
                News news = newsDao.get(idNews);
                news.getComment().add(comment);
                comment.setNews(news);

                User user = userDao.getUserByName(username);
                user.getComment().add(comment);
                comment.setUser(user);
            }

            comment.setComment(text);
            //comment.setCommentDate(commentDto.getCommentDate());
            commentDao.saveOrUpdate(comment);
            tx.commit();
            logger.info(COMMENT_WAS_ADDED_OK);
        } catch (DaoException e) {
            tx.rollback();
            logger.error(ADD_COMMENT_EXCEPTION);
        } finally {
            HibernateUtil.closeSession();
        }
    }

    /**
     * Delete comment by id
     */
    @Override
    public void deleteComment(String id) throws Exception {
        Transaction tx = null;
        Integer idNum;
        // Check if id is integer
        try {
            idNum = ServiceUtil.stringToPositiveInt(id);
        } catch (NumberFormatException e) {
            logger.error(ID_IS_NOT_VALID);
            throw new Exception(e);
        }
        // Delete comment by Id
        try {
            tx = HibernateUtil.getSession().beginTransaction();
            Comment comment = commentDao.get(idNum);
            News news = comment.getNews();
            news.getComment().remove(comment);
            User user = comment.getUser();
            user.getComment().remove(comment);
            commentDao.delete(comment);
            tx.commit();
        } catch (DaoException e) {
            tx.rollback();
            logger.error(DELETE_COMMENT_EXCEPTION);
            throw new Exception(e);
        } finally {
            HibernateUtil.closeSession();
        }
    }
}
