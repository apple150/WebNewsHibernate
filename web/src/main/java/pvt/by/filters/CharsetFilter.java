/**
 * Created by Sergey Buglak
 */

package pvt.by.filters;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import java.io.IOException;

/**
 * Servlet CharsetFilter encoding Filter
 */
public class CharsetFilter implements Filter {
    private static final String REQUEST_ENCODING = "requestEncoding";
    private static final String DEFAULT_ENCODING = "UTF-8";
    private static final String DEFAULT_ENCODING_MESSAGE_LOG = "Using default encoding instead.";
    // Get log4j2 logger
    private static final Logger logger = LogManager.getLogger(CharsetFilter.class); //Getting a log4j logger
    private String encoding;

    /*
    Get encoding from the web.xml or set it to default encoding
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        encoding = filterConfig.getInitParameter(REQUEST_ENCODING);
        if (encoding == null) {
            encoding = DEFAULT_ENCODING;
            logger.info(DEFAULT_ENCODING_MESSAGE_LOG);
        }
    }

    /*
    Setting character encoding
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        servletRequest.setCharacterEncoding(encoding);
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
    }
}