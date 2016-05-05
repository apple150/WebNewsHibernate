/**
 * Created by Sergey Buglak
 */

package pvt.by.test;

import java.util.Date;
import java.util.Random;

import pvt.by.pojos.*;

public class TestUtil {
    private static final String COMMENT_TEXT = "Comment Text ";
    private static final String CATEGORY_NAME = "Category Name ";
    private static final String NEWS_HEADER = "News Header ";
    private static final String NEWS_ANNOTATION = "News Annotation ";
    private static final String NEWS_TEXT = "News Text ";
    private static final String ROLE_NAME = "Role Name ";
    private static final String TAG_NAME = "Tag Name ";
    private static final String USERNAME = "Username ";
    private static final String USER_PASSWORD = "Password ";
    private static final String NEWSDETAIL_AUTHOR = "Author ";
    private static final String NEWSDETAIL_AGENCY = "Agency ";
    private static final String NEWSDETAIL_DATE = "Date ";
//    private static final String ROLE_NUM = "Date ";

    private static int categoryCount = 0;
    private static int commentCount = 0;
    private static int newsCount = 0;
    private static int newsAnnot = 0;
    private static int roleCount = 0;
    private static int tagCount = 0;
    private static int userCount = 0;
    private static int roleNumber = 10;

    private static Random rnd = new Random();

    public static Category getCategory() {
        Category category = new Category();
        category.setCategoryName(CATEGORY_NAME + categoryCount++);
        return category;
    }

    public static Comment getComment() {
        Comment comment = new Comment();
        comment.setComment(COMMENT_TEXT + commentCount++);
        return comment;
    }

    public static News getNews() {
        News news = new News();
        news.setTitle(NEWS_HEADER + newsCount++);
        news.setAnnotation(NEWS_ANNOTATION + newsAnnot++);
        news.setDocument((NEWS_TEXT + newsCount++));
        return news;
    }

    public static Role getRole() {
        Role role = new Role();
        role.setRole(roleNumber);
        role.setNameRole(ROLE_NAME + roleCount++);
        return role;
    }

    public static NewsDetail getNewsDetail() {
        NewsDetail newsDetail = new NewsDetail();
        newsDetail.setAuthor(NEWSDETAIL_AUTHOR + 1);
        newsDetail.setAgency(NEWSDETAIL_AGENCY + 1);
        newsDetail.setDrelease(new Date());
        return newsDetail;
    }
    public static Tag getTag() {
        Tag tag = new Tag();
        tag.setTagName(TAG_NAME + tagCount++);
        return tag;
    }

    public static User getUser() {
        User user = new User();
        user.setPassword(USER_PASSWORD + userCount);
        user.setName(USERNAME + userCount++);
        user.setSurName(USERNAME + userCount++);
        return user;
    }
}
