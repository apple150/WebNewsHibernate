/**
 * Created by Sergey Buglak
 */

package pvt.by.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pvt.by.command.util.WebConstants;
import pvt.by.dto.CommentDTO;
import pvt.by.service.impl.CommentServiceImpl;
import pvt.by.service.interfaces.CommentService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

public class SaveCommentCommand implements Command {
    private static final String THERE_WAS_AN_ERROR_WHILE_SAVING_COMMENT = "There was an error while saving comment";
    // Get log4j2 logger
    private static Logger logger = LogManager.getLogger(SaveCommentCommand.class);
    private CommentService commentService = CommentServiceImpl.getCommentService();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) {
        // Get comment text and id of news from request
        Date commentDate = new Date();
        String idComm = req.getParameter(WebConstants.ID_COMM_PARAM);
        String idNews = req.getParameter(WebConstants.ID_NEWS_PARAM);
        String text = req.getParameter(WebConstants.COMMENT_TEXT_PARAM);
        // Get username from session
        String user = (String) req.getSession().getAttribute(WebConstants.LOGIN_ATTR);

        // Put parameters to DTO
        CommentDTO commentDto = new CommentDTO();
        commentDto.setCommentDate(commentDate);
        commentDto.setIdComm(idComm);
        commentDto.setIdNews(idNews);
        commentDto.setText(text);
        commentDto.setUser(user);

        // Call service method
        try {
            commentService.saveComment(commentDto);
            req.setAttribute(WebConstants.REQUEST_HANDLE_DIRECTIVE_ATTR, WebConstants.SEND_REDIRECT);
            req.setAttribute(WebConstants.URI_ATTR, WebConstants.CONTROLLER_URL + WebConstants.DO + "="
                    + WebConstants.SHOW_NEWS + "&" + WebConstants.ID_NEWS_PARAM
                    + "=" + idNews);
        } catch (Exception e) {
            logger.error(THERE_WAS_AN_ERROR_WHILE_SAVING_COMMENT);
            req.setAttribute(WebConstants.ERROR_MESSAGE_ATTR, THERE_WAS_AN_ERROR_WHILE_SAVING_COMMENT);
            req.setAttribute(WebConstants.REQUEST_HANDLE_DIRECTIVE_ATTR, WebConstants.FORWARD);
            req.setAttribute(WebConstants.URI_ATTR, WebConstants.ERROR_PAGE);
        }
    }
}
