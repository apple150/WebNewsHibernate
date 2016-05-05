/**
 * Created by Sergey Buglak
 */

package pvt.by.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Command interface
 */
public interface Command {
    public void execute(HttpServletRequest req, HttpServletResponse resp);
}
