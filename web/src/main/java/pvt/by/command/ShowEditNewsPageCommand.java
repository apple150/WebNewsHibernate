/**
 * Created by Sergey Buglak
 */

package pvt.by.command;

import pvt.by.command.util.WebConstants;
import pvt.by.pojos.Category;
import pvt.by.pojos.News;
import pvt.by.pojos.Tag;
import pvt.by.service.impl.CategoryServiceImpl;
import pvt.by.service.impl.NewsServiceImpl;
import pvt.by.service.impl.TagServiceImpl;
import pvt.by.service.impl.UserServiceImpl;
import pvt.by.service.interfaces.CategoryService;
import pvt.by.service.interfaces.NewsService;
import pvt.by.service.interfaces.TagService;
import pvt.by.service.interfaces.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ShowEditNewsPageCommand implements Command {
    // Get UserService singleton
    UserService userService = UserServiceImpl.getUserService();
    CategoryService categoryService = CategoryServiceImpl.getCategoryService();
    TagService tagService = TagServiceImpl.getTagService();
    NewsService newsService = NewsServiceImpl.getNewsService();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) {
        String idNews = req.getParameter(WebConstants.ID_NEWS_PARAM);

        // Get single news if exist
        News news = null;
        try {
            news = newsService.getSingleNews(idNews);
        } catch (Exception e) {
        }
        // create proxy if news not exist
        if (news == null) {
            news = new News();
        }

        // Get Categories list
        List<Category> categories = categoryService.getCategories();
        // Get Tags list
        List<Tag> tags = tagService.getTags();

        // Attach lists to request
        req.setAttribute(WebConstants.NEWS_ATTR, news);
        req.setAttribute(WebConstants.CATEGORIES_ATTR, categories);
        req.setAttribute(WebConstants.TAGS_ATTR, tags);

        // Forward request
        req.setAttribute(WebConstants.REQUEST_HANDLE_DIRECTIVE_ATTR, WebConstants.FORWARD);
        req.setAttribute(WebConstants.URI_ATTR, WebConstants.EDIT_NEWS_PAGE);
    }
}
