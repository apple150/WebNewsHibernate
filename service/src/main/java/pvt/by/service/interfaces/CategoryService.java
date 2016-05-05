/**
 * Created by Sergey Buglak
 */

package pvt.by.service.interfaces;

import pvt.by.pojos.Category;

import java.util.List;

/**
 * INTERFACE CategoryService for service layer
 */
public interface CategoryService extends Service {
    List<Category> getCategories();
}
