/**
 * Created by Sergey Buglak
 */

package pvt.by.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Transaction;
import pvt.by.dao.exceptions.DaoException;
import pvt.by.dao.implement.*;
import pvt.by.dao.interfaces.*;
import pvt.by.dto.NewsDTO;
import pvt.by.pojos.*;
import pvt.by.service.interfaces.NewsService;
import pvt.by.util.ServiceUtil;
import pvt.by.utils.HibernateUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class NewsServiceImpl implements NewsService {
    private static final String FAILED_TO_FETCH_ALL_NEWS_ROWS = "Failed to fetch allNewsRows.";
    private static final String GET_NEWS_COUNT_EXCEPTION = "getNewsCount() exception";
    private static final String USER_NOT_FOUND_ID = "User not found @id = ";
    private static final String GET_SINGLE_NEWS_EXCEPTION = "Get single News exception.";
    private static final String INVALID_ID_EXCEPTION = "Invalid id exception";
    private static final String DELETE_NEWS_EXCEPTION = "Delete News exception";
    private static final String PARSING_INTS_FROM_DTO_EXCEPTION = "Parsing ints from DTO exception";
    private static final String USER_IS_NULL = "User is null";
    private static final String TEXT_IS_NULL = "Text is null";
    private static final String HEADER_IS_NULL = "Header is null";
    private static NewsServiceImpl instance; // singleton instance
    // Get log4j2 logger
    private static Logger logger = LogManager.getLogger(NewsServiceImpl.class);
    private NewsDao newsDao = NewsDaoImpl.getNewsDao();
    private CategoryDao categoryDao = CategoryDaoImpl.getCategoryDao();
    private NewsDetailDao newsDetailDao = NewsDetailDaoImpl.getNewsDetailDao();
    private UserDao userDao = UserDaoImpl.getUserDao();
    private TagDao tagDao = TagDaoImpl.getTagDao();

    private NewsServiceImpl() {}

    /**
     * Double-checked singleton
     */
    public static NewsServiceImpl getNewsService() {
        if (instance == null) {
            synchronized (NewsServiceImpl.class) {
                if (instance == null) {
                    instance = new NewsServiceImpl();
                }
            }
        }
        return instance;
    }

    @Override
    public void saveNews(NewsDTO newsDto) throws Exception {
        Integer categoryIdFromDto;
        Integer[] tagsIdsFromDto = null;
        try {
            // Get category Integer
            categoryIdFromDto = ServiceUtil.stringToPositiveInt(newsDto.getCategory());
            // Get tags Integers
            String[] strTags = newsDto.getTags();
            if (strTags != null) {
                tagsIdsFromDto = new Integer[strTags.length];
                for (int i = 0; i < strTags.length; i++) {
                    tagsIdsFromDto[i] = ServiceUtil.stringToPositiveInt(strTags[i]);
                }
            }
        } catch (NumberFormatException e) {
            logger.error(PARSING_INTS_FROM_DTO_EXCEPTION);
            throw new Exception(e);
        }

        // Check for not null header, text, username
        String headerFromDto = newsDto.getHeader();
        if (headerFromDto == null) {
            logger.error(HEADER_IS_NULL);
            throw new Exception();
        }

        String textFromDto = newsDto.getText();
        if (textFromDto == null) {
            logger.error(TEXT_IS_NULL);
            throw new Exception();
        }

        String userFromDto = newsDto.getUser();
        if (userFromDto == null) {
            logger.error(USER_IS_NULL);
            throw new Exception();
        }

        Integer idNewsFromDto;
        // Check if idNews is integer
        try {
            idNewsFromDto = ServiceUtil.stringToPositiveInt(newsDto.getIdNews());
        } catch (NumberFormatException e) {
            idNewsFromDto = null;
        }

        Transaction tx = null;
        // Save news
        try {
            tx = HibernateUtil.getSession().beginTransaction();
            News news = null;
            NewsDetail newsDetail = null;
            // Try to get news by it id
            if (idNewsFromDto != null) {
                // If exist, update
                news = newsDao.get(idNewsFromDto);
                // Fill news NewsDetail
                newsDetail = newsDetailDao.get(idNewsFromDto);
            } else {
                // If not, create
                news = new News();
                newsDetail = new NewsDetail();
                news.setNewsDetail(newsDetail);
                newsDetail.setNews(news);

                // Set News and NewsDetail if it is necessary
                if (newsDetail.getDrelease() == null) {
                    newsDetail.setDrelease(newsDto.getDate());
                }
                if (newsDto.getAuthor() == null) {
                    newsDetail.setAuthor("Артем Шрайбман");
                }
                if (newsDto.getAgency() == null) {
                    newsDetail.setAgency("TUT.BY");
                }
                if (news.getAnnotation() == null) {
                    news.setAnnotation("Тестовая Аннотация");
                }

                // Create user
                User user = userDao.getUserByName(userFromDto);
                user.getNews().add(news);
                news.setUser(user);
            }
            // Add tags to news
            if (tagsIdsFromDto != null) {
                for (Tag tag : tagDao.getTags()) {
                    for (int i = 0; i < tagsIdsFromDto.length; i++) {
                        if (tag.getTagId() == tagsIdsFromDto[i]) {
                            tag = tagDao.get(tagsIdsFromDto[i]);
                            tag.getNews().add(news);
                            news.getTag().add(tag);
                            break;
                        }
                        tag.getNews().remove(news);
                        news.getTag().remove(tag);
                    }
                }
            } else {
                news.getTag().clear();
            }

            // Set category
            Category categoryFromDto = categoryDao.get(categoryIdFromDto);
            categoryFromDto.getNews().add(news);
            news.setCategory(categoryFromDto);

            news.setTitle(headerFromDto);
            news.setDocument(textFromDto);

            //System.out.println("***** POJO  NEWS: " + news);
            //Save News In DB
            newsDao.saveOrUpdate(news);
            tx.commit();
            logger.info("News was added OK");
        } catch (Exception e) {
            tx.rollback();
            logger.error("addNews() exception");
            e.printStackTrace();
            throw new Exception(e);
        } finally {
            HibernateUtil.closeSession();
        }
    }

    /**
     * Delete single news by Id
     */
    @Override
    public void deleteNews(String id) throws Exception {
        Transaction tx = null;
        Integer idNum;
        // Check if id is integer
        try {
            idNum = ServiceUtil.stringToPositiveInt(id);
        } catch (NumberFormatException e) {
            logger.error(INVALID_ID_EXCEPTION);
            throw new Exception(e);
        }
        // Delete news by id
        try {
            tx = HibernateUtil.getSession().beginTransaction();
            News news = newsDao.get(idNum);

            Category category = news.getCategory();
            category.getNews().remove(news);

            User user = news.getUser();
            user.getNews().remove(news);

            Set<Tag> tagSet = news.getTag();
            for (Tag tag : tagSet) {
                tag.getNews().remove(news);
            }

            newsDao.delete(news);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            logger.error(DELETE_NEWS_EXCEPTION);
        }
    }

    /**
     * Get all news rows from DB via NewsDaoImpl singleton
     */
    @Override
    public List<News> getNewsRows(int newsByPage, int startPage, String columnName, String sortOrder) {
        Transaction tx = null;
        List<News> news;

        // Calculate start position
        int startPosition = newsByPage * (startPage - 1);
        try {
            tx = HibernateUtil.getSession().beginTransaction();
            news = newsDao.getNewsRows(newsByPage, startPosition, columnName, sortOrder);
            tx.commit();
            return news;
        } catch (DaoException e) {
            tx.rollback();
            logger.error(FAILED_TO_FETCH_ALL_NEWS_ROWS);
            return new ArrayList<News>();
        } finally {
            HibernateUtil.closeSession();
        }
    }

    @Override
    public Long getNewsCount() {
        Transaction tx = null;
        Long newsCount = 0L;
        try {
            tx = HibernateUtil.getSession().beginTransaction();
            newsCount = newsDao.getNewsCount();
            tx.commit();
            return newsCount;
        } catch (DaoException e) {
            logger.error(GET_NEWS_COUNT_EXCEPTION);
            tx.rollback();
            return newsCount;
        } finally {
            HibernateUtil.closeSession();
        }
    }

    @Override
    public News getSingleNews(String stringId) throws Exception {
        Integer id;
        try {
            id = ServiceUtil.stringToPositiveInt(stringId);
        } catch (NumberFormatException e) {
            logger.error(INVALID_ID_EXCEPTION);
            throw new Exception(e);
        }
        Transaction tx = null;
        News news;
        try {
            tx = HibernateUtil.getSession().beginTransaction();
            news = newsDao.get(id);
            tx.commit();
            // Check for null
            if (news != null) {
//                Do sort
//                TreeSet<Comment> treeSetNews = new TreeSet<Comment>();
//                        new Comparator<Comment>() {
//                            @Override
//                            public int compare(Comment o1, Comment o2) {
//                                return o1.getCommentDate().compareTo(
//                                        o2.getCommentDate());
//                            }
//                        });
//                treeSetNews.addAll(news.getComment());
//                news.setComment(treeSetNews);
                return news;
            } else {
                logger.error(USER_NOT_FOUND_ID + id);
                throw new Exception();
            }
        } catch (DaoException e) {
            logger.error(GET_SINGLE_NEWS_EXCEPTION);
            tx.rollback();
            throw new Exception(e);
        } finally {
            HibernateUtil.closeSession();
        }
    }
}
