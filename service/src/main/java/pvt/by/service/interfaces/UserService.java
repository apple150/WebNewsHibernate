/**
 * Created by Sergey Buglak
 */

package pvt.by.service.interfaces;

/**
 * INTERFACE TagService for service layer
 */
public interface UserService extends Service {
    boolean isLoginSuccess(String login, String pass);
}
