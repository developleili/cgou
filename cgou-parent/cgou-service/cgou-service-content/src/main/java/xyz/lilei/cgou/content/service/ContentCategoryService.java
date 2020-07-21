package xyz.lilei.cgou.content.service;
import xyz.lilei.cgou.content.pojo.ContentCategory;
import com.github.pagehelper.PageInfo;
import java.util.List;
/****
 * @Author:admin
 *
 * @Date 2019/6/14 0:16
 *****/
public interface ContentCategoryService {

    /***
     * @param contentCategory
     * @param page
     * @param size
     * @return
     */
    PageInfo<ContentCategory> findPage(ContentCategory contentCategory, int page, int size);

    /***
     * @param page
     * @param size
     * @return
     */
    PageInfo<ContentCategory> findPage(int page, int size);

    /***
     * @param contentCategory
     * @return
     */
    List<ContentCategory> findList(ContentCategory contentCategory);

    /***
     * @param id
     */
    void delete(Long id);

    /***
     * @param contentCategory
     */
    void update(ContentCategory contentCategory);

    /***
     * @param contentCategory
     */
    void add(ContentCategory contentCategory);

    /**
     * @param id
     * @return
     */
     ContentCategory findById(Long id);

    /***
     * @return
     */
    List<ContentCategory> findAll();
}
