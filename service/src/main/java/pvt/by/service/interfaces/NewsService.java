/**
 * Created by Sergey Buglak
 */

package pvt.by.service.interfaces;

import pvt.by.dto.NewsDTO;
import pvt.by.pojos.News;

import java.util.List;

/**
 * INTERFACE NewsService for service layer
 */
public interface NewsService extends Service {
    List<News> getNewsRows(int newsByPage, int startPage, String columnName, String sortOrder);
    Long getNewsCount();
    News getSingleNews(String id) throws Exception;
    void deleteNews(String id) throws Exception;
    void saveNews(NewsDTO newsDto) throws Exception;
}
