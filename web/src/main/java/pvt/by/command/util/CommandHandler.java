/**
 * Created by Sergey Buglak
 */

package pvt.by.command.util;

import pvt.by.command.LogoutCommand;
import pvt.by.command.SaveCommentCommand;
import pvt.by.command.SaveNewsCommand;
import pvt.by.command.ShowEditNewsPageCommand;
import pvt.by.command.Command;
import pvt.by.command.DeleteCommentCommand;
import pvt.by.command.DeleteNewsCommand;
import pvt.by.command.ShowEditCommentPageCommand;
import pvt.by.command.ShowNewsPageCommand;
import pvt.by.command.ShowSingleNewsPageCommand;

/**
 * Handle commands for Controller class
 */
public abstract class CommandHandler {
    public static Command handleCommand(String action) {
        Command command;
        if (action == null) {
            command = new ShowNewsPageCommand();
        } else {
            switch (action) {
                case WebConstants.SHOW_NEWS: {
                    command = new ShowSingleNewsPageCommand();
                    break;
                }
                case WebConstants.DEL_COMMENT: {
                    command = new DeleteCommentCommand();
                    break;
                }
                case WebConstants.SAVE_COMMENT: {
                    command = new SaveCommentCommand();
                    break;
                }
                case WebConstants.SHOW_EDIT_COMMENT: {
                    command = new ShowEditCommentPageCommand();
                    break;
                }
                case WebConstants.DEL_NEWS: {
                    command = new DeleteNewsCommand();
                    break;
                }
                case WebConstants.SHOW_EDIT_NEWS: {
                    command = new ShowEditNewsPageCommand();
                    break;
                }
                case WebConstants.SAVE_NEWS: {
                    command = new SaveNewsCommand();
                    break;
                }
                case WebConstants.LOGOUT: {
                    command = new LogoutCommand();
                    break;
                }
                default: {
                    command = new ShowNewsPageCommand();
                }
            }
        }
        return command;
    }
}