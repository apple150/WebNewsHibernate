/**
 * Created by Sergey Buglak
 */

package pvt.by.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pvt.by.command.util.WebConstants;
import pvt.by.service.impl.NewsServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteNewsCommand implements Command {
    private static final String DELETE_NEWS_EXCEPTION = "Delete news exception";
    // Get log4j2 logger
    private static Logger logger = LogManager.getLogger(DeleteNewsCommand.class);
    // Get NewsService singleton
    private NewsServiceImpl newsService = NewsServiceImpl.getNewsService();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) {
        // Call service method to delete News by its id from request
        try {
            newsService.deleteNews(req.getParameter(WebConstants.ID_NEWS_PARAM));
        } catch (Exception e) {
            logger.error(DELETE_NEWS_EXCEPTION);
        }
        // Forward request
        req.setAttribute(WebConstants.REQUEST_HANDLE_DIRECTIVE_ATTR, WebConstants.SEND_REDIRECT);
        req.setAttribute(WebConstants.URI_ATTR, WebConstants.CONTROLLER_URL);
    }
}
