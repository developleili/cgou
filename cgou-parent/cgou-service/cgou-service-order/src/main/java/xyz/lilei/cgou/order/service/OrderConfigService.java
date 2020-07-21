package xyz.lilei.cgou.order.service;

import com.github.pagehelper.PageInfo;
import xyz.lilei.cgou.order.pojo.OrderConfig;

import java.util.List;

/****
 * @Author:lilei
 * @Description:
 * @Date 2020/7/04 21:01
 *****/
public interface OrderConfigService {

    /***
     * OrderConfig多条件分页查询
     * @param orderConfig
     * @param page
     * @param size
     * @return
     */
    PageInfo<OrderConfig> findPage(OrderConfig orderConfig, int page, int size);

    /***
     * OrderConfig分页查询
     * @param page
     * @param size
     * @return
     */
    PageInfo<OrderConfig> findPage(int page, int size);

    /***
     * OrderConfig多条件搜索方法
     * @param orderConfig
     * @return
     */
    List<OrderConfig> findList(OrderConfig orderConfig);

    /***
     * 删除OrderConfig
     * @param id
     */
    void delete(Integer id);

    /***
     * 修改OrderConfig数据
     * @param orderConfig
     */
    void update(OrderConfig orderConfig);

    /***
     * 新增OrderConfig
     * @param orderConfig
     */
    void add(OrderConfig orderConfig);

    /**
     * 根据ID查询OrderConfig
     * @param id
     * @return
     */
     OrderConfig findById(Integer id);

    /***
     * 查询所有OrderConfig
     * @return
     */
    List<OrderConfig> findAll();
}
