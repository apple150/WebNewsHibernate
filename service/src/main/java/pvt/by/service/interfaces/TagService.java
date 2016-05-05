/**
 * Created by Sergey Buglak
 */

package pvt.by.service.interfaces;

import pvt.by.pojos.Tag;

import java.util.List;

/**
 * INTERFACE TagService for service layer
 */
public interface TagService extends Service {
    List<Tag> getTags();
}
