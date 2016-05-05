/**
 * Created by Sergey Buglak
 */

package pvt.by.command;

import java.util.Date;

import pvt.by.command.util.WebConstants;
import pvt.by.dto.NewsDTO;
import pvt.by.service.impl.NewsServiceImpl;
import pvt.by.service.interfaces.NewsService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SaveNewsCommand implements Command {
    private static final String ERROR_ADDING_NEWS = "Error adding news";
    private static final String THERE_WAS_ERROR_WHILE_ADDING_NEWS_TO_DB = "There was error while adding news to DB";
    // Get log4j2 logger
    private static Logger logger = LogManager.getLogger(SaveNewsCommand.class);
    private NewsService newsService = NewsServiceImpl.getNewsService();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) {
        // Parse request parameters
        String idNews = req.getParameter(WebConstants.ID_NEWS_PARAM);
        String header = req.getParameter(WebConstants.NEWS_HEADER_PARAM);
        String text = req.getParameter(WebConstants.NEWS_TEXT_PARAM);
        String category = req.getParameter(WebConstants.NEWS_CATEGORY_PARAM);
        String[] tags = req.getParameterValues(WebConstants.NEWS_TAG_PARAM);
        Date date = new Date();
        String user = (String) req.getSession().getAttribute(WebConstants.LOGIN_ATTR);

        // Put parameters to DTO
        NewsDTO newsDto = new NewsDTO();
        newsDto.setCategory(category);
        newsDto.setDate(date);
        newsDto.setHeader(header);
        newsDto.setTags(tags);
        newsDto.setText(text);
        newsDto.setUser(user);
        newsDto.setIdNews(idNews);

        // Call service method
        try {
            newsService.saveNews(newsDto);
            req.setAttribute(WebConstants.REQUEST_HANDLE_DIRECTIVE_ATTR, WebConstants.SEND_REDIRECT);
            req.setAttribute(WebConstants.URI_ATTR, WebConstants.CONTROLLER_URL);
        } catch (Exception e) {
            logger.error(THERE_WAS_ERROR_WHILE_ADDING_NEWS_TO_DB);
            req.setAttribute(WebConstants.ERROR_MESSAGE_ATTR, ERROR_ADDING_NEWS);
            req.setAttribute(WebConstants.REQUEST_HANDLE_DIRECTIVE_ATTR, WebConstants.FORWARD);
            req.setAttribute(WebConstants.URI_ATTR, WebConstants.ERROR_PAGE);
        }
    }
}
