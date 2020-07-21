package xyz.lilei.cgou.order.service;

import com.github.pagehelper.PageInfo;
import xyz.lilei.cgou.order.pojo.ReturnCause;

import java.util.List;

/****
 * @Author:lilei
 * @Description:
 * @Date 2020/7/04 21:01
 *****/
public interface ReturnCauseService {

    /***
     * ReturnCause多条件分页查询
     * @param returnCause
     * @param page
     * @param size
     * @return
     */
    PageInfo<ReturnCause> findPage(ReturnCause returnCause, int page, int size);

    /***
     * ReturnCause分页查询
     * @param page
     * @param size
     * @return
     */
    PageInfo<ReturnCause> findPage(int page, int size);

    /***
     * ReturnCause多条件搜索方法
     * @param returnCause
     * @return
     */
    List<ReturnCause> findList(ReturnCause returnCause);

    /***
     * 删除ReturnCause
     * @param id
     */
    void delete(Integer id);

    /***
     * 修改ReturnCause数据
     * @param returnCause
     */
    void update(ReturnCause returnCause);

    /***
     * 新增ReturnCause
     * @param returnCause
     */
    void add(ReturnCause returnCause);

    /**
     * 根据ID查询ReturnCause
     * @param id
     * @return
     */
     ReturnCause findById(Integer id);

    /***
     * 查询所有ReturnCause
     * @return
     */
    List<ReturnCause> findAll();
}