/**
 * Created by Sergey Buglak
 */

package pvt.by.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pvt.by.command.util.WebConstants;
import pvt.by.pojos.News;
import pvt.by.service.impl.NewsServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowSingleNewsPageCommand implements Command {
    private static final String USER_NOT_FOUND_ERROR_STRING = "User not found";
    private static final String NO_SUCH_NEWS_WITH_THAT_ID = "No such News with that Id";
    // Get log4j2 logger
    private static Logger logger = LogManager.getLogger(ShowSingleNewsPageCommand.class);
    private NewsServiceImpl newsService;

    public ShowSingleNewsPageCommand() {
        newsService = NewsServiceImpl.getNewsService();
    }

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) {
        logger.error("getParameterId - "+req.getParameter(WebConstants.ID_NEWS_PARAM));
        try {
            News news = newsService.getSingleNews(req.getParameter(WebConstants.ID_NEWS_PARAM));
//            News news = newsService.getSingleNews("0");
            req.setAttribute(WebConstants.NEWS_ATTR, news);
            // Forward request
            req.setAttribute(WebConstants.REQUEST_HANDLE_DIRECTIVE_ATTR, WebConstants.FORWARD);
            req.setAttribute(WebConstants.URI_ATTR, WebConstants.SHOW_NEWS_PAGE);
        } catch (Exception e) {
            logger.error(NO_SUCH_NEWS_WITH_THAT_ID+" - "+req.getParameter(WebConstants.ID_NEWS_PARAM));
            req.setAttribute(WebConstants.ERROR_MESSAGE_ATTR, USER_NOT_FOUND_ERROR_STRING);
            req.setAttribute(WebConstants.REQUEST_HANDLE_DIRECTIVE_ATTR, WebConstants.FORWARD);
            req.setAttribute(WebConstants.URI_ATTR, WebConstants.ERROR_PAGE);
        }
    }
}
