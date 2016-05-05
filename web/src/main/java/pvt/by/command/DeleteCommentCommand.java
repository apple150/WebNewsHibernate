/**
 * Created by Sergey Buglak
 */

package pvt.by.command;

import pvt.by.command.util.WebConstants;
//import pvt.by.service.impl.CommentServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import pvt.by.service.impl.CommentServiceImpl;

public class DeleteCommentCommand implements Command {
    private static final String DELETE_COMMENT_EXCEPTION = "Delete Comment exception.";
    // Get log4j2 logger
    private static Logger logger = LogManager.getLogger(DeleteCommentCommand.class);
    // Get CommentService singleton
    private CommentServiceImpl commentService = CommentServiceImpl.getCommentService();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) {
        // Call service method to delete Comment by its id from request
        try {
            commentService.deleteComment(req.getParameter(WebConstants.ID_COMM_PARAM));
        } catch (Exception e) {
            logger.error(DELETE_COMMENT_EXCEPTION);
        }
        // Forward request
        req.setAttribute(WebConstants.REQUEST_HANDLE_DIRECTIVE_ATTR, WebConstants.SEND_REDIRECT);
        req.setAttribute(WebConstants.URI_ATTR, WebConstants.CONTROLLER_URL + WebConstants.DO + "="
                        + WebConstants.SHOW_NEWS + "&" + WebConstants.ID_NEWS_PARAM
                        + "=" + req.getParameter(WebConstants.ID_NEWS_PARAM));
    }
}
