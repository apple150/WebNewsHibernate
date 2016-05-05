/**
 * Created by Sergey Buglak
 */

package pvt.by.command.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * Handle requests
 */
public abstract class RequestHandler {
    private static final Logger logger = LogManager.getLogger(RequestHandler.class);

    public static void handleRequest(HttpServletRequest req, HttpServletResponse resp) {
        String directive = (String) req.getAttribute(WebConstants.REQUEST_HANDLE_DIRECTIVE_ATTR);
        switch (directive) {
            case WebConstants.FORWARD: {
                RequestDispatcher dispatcher = req.getRequestDispatcher((String) req.getAttribute(WebConstants.URI_ATTR));
                try {
                    dispatcher.forward(req, resp);
                } catch (ServletException e) {
                    e.printStackTrace();
                    logger.error(WebConstants.SERVLET_EX);
                } catch (IOException e) {
                    logger.error(WebConstants.IO_EX);
                }
                break;
            }
            case WebConstants.SEND_REDIRECT:
            default: {
                try {
                    resp.sendRedirect((String) req.getAttribute(WebConstants.URI_ATTR));
                } catch (IOException e) {
                    logger.error(WebConstants.IO_EX);
                }
            }
        }
    }
}