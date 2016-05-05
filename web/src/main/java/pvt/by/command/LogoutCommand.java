/**
 * Created by Sergey Buglak
 */

package pvt.by.command;

import pvt.by.command.util.WebConstants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LogoutCommand implements Command {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) {
        // Set login to null
        req.getSession().setAttribute(WebConstants.LOGIN_ATTR, null);

        // Redirect
        req.setAttribute(WebConstants.REQUEST_HANDLE_DIRECTIVE_ATTR, WebConstants.SEND_REDIRECT);
        req.setAttribute(WebConstants.URI_ATTR, WebConstants.CONTROLLER_URL);
    }
}
