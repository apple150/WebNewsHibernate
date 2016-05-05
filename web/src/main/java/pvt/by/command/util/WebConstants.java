/**
 * Created by Sergey Buglak
 */

package pvt.by.command.util;

/**
 * String constants for service module
 */
public class WebConstants {
    /**
     * Name of the parameter to decide what to do for CommandHandler
     */
    public static final String DO = "do";
    // Special, to transfer DO value via attributes and parameters
    public static final String DO_PARAM = "doParam";

    // Possible values of DO parameter
    /**
     * Show single news (DO parameter value)
     */
    public static final String SHOW_NEWS = "show";
    /**
     * Delete single comment (DO parameter value)
     */
    public static final String DEL_COMMENT = "delComm";
    /**
     * Delete single news (DO parameter value)
     */
    public static final String DEL_NEWS = "del";
    /**
     * Show add single news page (DO parameter value)
     */
    public static final String SHOW_EDIT_NEWS = "showEditNews";
    /**
     * Add single news (DO parameter value)
     */
    public static final String SAVE_NEWS = "save";
    /**
     * Save or update comment (DO parameter value)
     */
    public static final String SAVE_COMMENT = "saveComm";
    /**
     * Show edit comment page (DO parameter value)
     */
    public static final String SHOW_EDIT_COMMENT = "showEditComm";
    /**
     * Logout (DO parameter value)
     */
    public static final String LOGOUT = "logout";
    
    // Parameter names
    /**
     * Parameter that contains login
     */
    public static final String LOGIN_PARAM = "login";
    /**
     * Parameter that contains password
     */
    public static final String PASS_PARAM = "pass";
    /**
     * Parameter that contains Id of comment
     */
    public static final String ID_COMM_PARAM = "idComm";
    /**
     * Parameter that contains Id of news
     */
    public static final String ID_NEWS_PARAM = "id";

    // Attribute names
    /**
     * Attribute that contains list of tags
     */
    public static final String TAGS_ATTR = "tags";
    /**
     * Attribute that contains list of categories
     */
    public static final String CATEGORIES_ATTR = "categories";
    /**
     * Attribute that contains list of news
     */
    public static final String NEWS_ATTR = "news";

    // Parameters of News pojo
    /**
     * Parameter that contains News header
     */
    public static final String NEWS_HEADER_PARAM = "header";
    /**
     * Parameter that contains News category id
     */
    public static final String NEWS_CATEGORY_PARAM = "category";
    /**
     * Parameter that contains News tag id
     */
    public static final String NEWS_TAG_PARAM = "tags";
    /**
     * Parameter that contains News text
     */
    public static final String NEWS_TEXT_PARAM = "text";

    //Sorting order parameters
    /**
     * Parameter that contains by which column will be sorted
     */
    public static final String SORT_COLUMN_NAME_PARAM = "column";
    //Possible values
    /**
     * Column - Date
     */
    public static final String SORT_DATE = "newsDetail.drelease";
    /**
     * Column - Header
     */
    public static final String SORT_HEADER = "header";
    /**
     * Column - Title
     */
    public static final String SORT_TITLE = "title";
    /**
     * Column - Category
     */
    public static final String SORT_CATEGORY = "category";
    /**
     * Column - Author
     */
    public static final String SORT_AUTHOR = "user";
    
    /**
     * Parameter that contains sort order
     */
    public static final String SORT_ORDER_PARAM = "sortOrder";
    /**
     * Order - Ascending
     */
    public static final String SORT_ORDER_ASC = "asc";
    /**
     * Order - Descending
     */
    public static final String SORT_ORDER_DESC = "desc";
    
    // Parameters of Comment pojo
    /**
     * Parameter that contains Comment text
     */
    public static final String COMMENT_TEXT_PARAM = "text";

    // URL for controller
    public static final String CONTROLLER_URL = "?";
    /**
     * Login page URL
     */
//    public static final String LOGIN_PAGE = "index.html";
    public static final String LOGIN_PAGE = "login.jsp";
    // News page
    public static final String NEWS_PAGE = "news.jsp";
    // Show single News page
    public static final String SHOW_NEWS_PAGE = "shownews.jsp";
    // Add or edit single News page
    public static final String EDIT_NEWS_PAGE = "editnews.jsp";
    // Edit comment page
    public static final String EDIT_COMMENT_PAGE = "editcomment.jsp";
    // Error page
    public static final String ERROR_PAGE = "error.jsp";
    public static final String ERROR_MESSAGE_ATTR = "errorMessage";
    // Exception strings
    public static final String IO_EX = "IO Exception.";
    public static final String INVALID_ID_EX = "Invalid category id.";
    public static final String SERVLET_EX = "Servlet exception.";

    // Constants to interact with RequestHandler
    // Name of attr that contains URI to redirect or forward
    public static final String URI_ATTR = "URI";
    // Name of attr that set to send or to forward request
    public static final String REQUEST_HANDLE_DIRECTIVE_ATTR = "RequestHandleDirective";
    // Values of REQUEST_HANDLE_DIRECTIVE_ATTR
    public static final String SEND_REDIRECT = "sendRedirect";
    public static final String FORWARD = "forward";

    // Authentication
    // Authenticated user name
    public static final String LOGIN_ATTR = "login";
    // Permissions
    public static final String AUTH_CREATE_COMMENT = "createComment";
    public static final String AUTH_READ_COMMENT = "readComment";
    public static final String AUTH_UPDATE_COMMENT = "updateComment";
    public static final String AUTH_DELETE_COMMENT = "deleteComment";
    public static final String AUTH_CREATE_NEWS = "createNews";
    public static final String AUTH_READ_NEWS = "readNews";
    public static final String AUTH_UPDATE_NEWS = "updateNews";
    public static final String AUTH_DELETE_NEWS = "deleteNews";
}
