/**
 * Created by Sergey Buglak
 */

package pvt.by.command;

import pvt.by.command.util.WebConstants;
import pvt.by.pojos.News;
import pvt.by.service.impl.NewsServiceImpl;
import pvt.by.util.ServiceUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/*
 * Show news page as default action
 * Added pagination
 */
public class ShowNewsPageCommand implements Command {
    // Default value of newsByPage
    private static final int DEFAULT_NEWS_BY_PAGE = 3;

    // Name of the attribute which contains all news
    private static final String NEWS_ATTR = "news";
    private static final String PAGINATION_START_PAGE = "startPage";
    private static final String PAGINATION_NEWS_BY_PAGE = "newsByPage";
    private static final String PAGINATION_LAST_PAGE = "lastPage";

    // Get NewsService singleton
    private NewsServiceImpl newsService = NewsServiceImpl.getNewsService();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) {
        // Get pagination parameters
        int newsByPage;
        int startPage;
        // Needed here to set default pagination newsByPage
        int newsCount = (int) (newsService.getNewsCount() + 0);

        // Get int pagination parameters.
        // Get news by page count
        try {
            newsByPage = ServiceUtil.stringToPositiveInt(req.getParameter(PAGINATION_NEWS_BY_PAGE));
        } catch (NumberFormatException e) {
            newsByPage = DEFAULT_NEWS_BY_PAGE;
        }
        // Get start page
        try {
            startPage = ServiceUtil.stringToPositiveInt(req.getParameter(PAGINATION_START_PAGE));
        } catch (NumberFormatException e) {
            startPage = 1;
        }

        // Calculate last page
        int lastPage = newsCount / newsByPage;
        if (newsCount % newsByPage != 0) {
            lastPage++;
        }

        // Check if startPage over total pages
        if (startPage > lastPage) {
            startPage = lastPage;
        }

        //Get sort order
        String columnName = req.getParameter(WebConstants.SORT_COLUMN_NAME_PARAM);
        if (columnName == null) {
            columnName = WebConstants.SORT_TITLE;
        }

        String sortOrder = req.getParameter(WebConstants.SORT_ORDER_PARAM);
        if (sortOrder == null) {
            sortOrder = WebConstants.SORT_ORDER_DESC;
        }

        // Get all news (pages = 1,2,3...)
        List<News> news = newsService.getNewsRows(newsByPage, startPage, columnName, sortOrder);

        // Attach them to request
        req.setAttribute(NEWS_ATTR, news);

        // Attach pagination parameters
        req.setAttribute(PAGINATION_NEWS_BY_PAGE, newsByPage);
        req.setAttribute(PAGINATION_START_PAGE, startPage);
        req.setAttribute(PAGINATION_LAST_PAGE, lastPage);

        // Attach sort parameters
        req.setAttribute(WebConstants.SORT_COLUMN_NAME_PARAM, columnName);
        req.setAttribute(WebConstants.SORT_ORDER_PARAM, sortOrder);

        // Forward request
        req.setAttribute(WebConstants.REQUEST_HANDLE_DIRECTIVE_ATTR, WebConstants.FORWARD);
        req.setAttribute(WebConstants.URI_ATTR, WebConstants.NEWS_PAGE);
    }
}