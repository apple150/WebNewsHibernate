/**
 * Created by Sergey Buglak
 */

package pvt.by.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pvt.by.command.util.WebConstants;
import pvt.by.pojos.Comment;
import pvt.by.service.impl.CommentServiceImpl;
import pvt.by.service.interfaces.CommentService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowEditCommentPageCommand implements Command {
    private static final String THERE_WAS_AN_ERROR_WHILE_FETCH_COMMENT = "There was an error while fetch comment";
    // Get log4j2 logger
    private static Logger logger = LogManager.getLogger(ShowEditCommentPageCommand.class);
    private CommentService commentService = CommentServiceImpl.getCommentService();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) {
        String idComm = req.getParameter(WebConstants.ID_COMM_PARAM);
        try {
            Comment comment = commentService.getComment(idComm);
            req.setAttribute(WebConstants.ID_NEWS_PARAM, comment.getNews().getNewsId());
            req.setAttribute(WebConstants.COMMENT_TEXT_PARAM, comment.getComment());
            req.setAttribute(WebConstants.ID_COMM_PARAM, idComm);
            // Forward request
            req.setAttribute(WebConstants.REQUEST_HANDLE_DIRECTIVE_ATTR, WebConstants.FORWARD);
            req.setAttribute(WebConstants.URI_ATTR, WebConstants.EDIT_COMMENT_PAGE);
        } catch (Exception e) {
            logger.error(THERE_WAS_AN_ERROR_WHILE_FETCH_COMMENT);
            req.setAttribute(WebConstants.ERROR_MESSAGE_ATTR, THERE_WAS_AN_ERROR_WHILE_FETCH_COMMENT);
            req.setAttribute(WebConstants.REQUEST_HANDLE_DIRECTIVE_ATTR, WebConstants.FORWARD);
            req.setAttribute(WebConstants.URI_ATTR, WebConstants.ERROR_PAGE);
        }
    }
}
