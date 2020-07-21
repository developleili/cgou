package xyz.lilei.cgou.business.service;
import xyz.lilei.cgou.business.pojo.Activity;
import com.github.pagehelper.PageInfo;
import java.util.List;
/****
 * @Author:lilei
 * @Description:
 * @Date 2020/7/04 21:01
 *****/
public interface ActivityService {

    /***
     * Activity多条件分页查询
     * @param activity
     * @param page
     * @param size
     * @return
     */
    PageInfo<Activity> findPage(Activity activity, int page, int size);

    /***
     * Activity分页查询
     * @param page
     * @param size
     * @return
     */
    PageInfo<Activity> findPage(int page, int size);

    /***
     * Activity多条件搜索方法
     * @param activity
     * @return
     */
    List<Activity> findList(Activity activity);

    /***
     * 删除Activity
     * @param id
     */
    void delete(Integer id);

    /***
     * 修改Activity数据
     * @param activity
     */
    void update(Activity activity);

    /***
     * 新增Activity
     * @param activity
     */
    void add(Activity activity);

    /**
     * 根据ID查询Activity
     * @param id
     * @return
     */
     Activity findById(Integer id);

    /***
     * 查询所有Activity
     * @return
     */
    List<Activity> findAll();
}
