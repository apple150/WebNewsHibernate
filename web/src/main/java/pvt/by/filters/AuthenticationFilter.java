/**
 * Created by Sergey Buglak
 */

package pvt.by.filters;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pvt.by.command.util.WebConstants;
import pvt.by.service.impl.UserServiceImpl;
import pvt.by.service.interfaces.UserService;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Class AuthenticationFilter
 */
public class AuthenticationFilter implements Filter {
    // Get service SINGLETON
    private static final UserService userService = UserServiceImpl.getUserService();
    private static final String PERMISSIONS_FETCH_EXCEPTION = "Permissions fetch exception";
    // Get log4j2 logger
    private static Logger logger = LogManager.getLogger(AuthenticationFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        HttpSession session = req.getSession();

        if (session.getAttribute(WebConstants.LOGIN_ATTR) == null) {
            String login = req.getParameter(WebConstants.LOGIN_PARAM);
            String password = req.getParameter(WebConstants.PASS_PARAM);
            // Call isLoginSuccess from service layer
            if (userService.isLoginSuccess(login, password)) {
                // Save username to session
                session.setAttribute(WebConstants.LOGIN_ATTR, login);
                filterChain.doFilter(servletRequest, servletResponse);
            } else {
                req.getRequestDispatcher(WebConstants.LOGIN_PAGE).forward(req, resp);
                //resp.sendRedirect(WebConstants.LOGIN_PAGE);
            }
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {}
}