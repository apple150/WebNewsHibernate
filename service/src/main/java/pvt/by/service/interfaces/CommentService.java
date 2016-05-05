/**
 * Created by Sergey Buglak
 */

package pvt.by.service.interfaces;

import pvt.by.dto.CommentDTO;
import pvt.by.pojos.Comment;

/**
 * INTERFACE CommentService for service layer
 */
public interface CommentService extends Service {
    void deleteComment(String id) throws Exception;
    void saveComment(CommentDTO commentDto) throws Exception;
    Comment getComment(String id) throws Exception;
}
