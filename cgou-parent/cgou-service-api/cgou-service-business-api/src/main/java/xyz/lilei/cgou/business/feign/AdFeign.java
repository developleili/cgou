package xyz.lilei.cgou.business.feign;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import xyz.lilei.cgou.business.pojo.Ad;
import xyz.lilei.cgou.common.entity.Result;
import com.github.pagehelper.PageInfo;

import java.util.List;

/****
 * @Author:lilei
 * @Description:
 * @Date 2020/7/04 21:01
 *****/
@FeignClient(name="business")
@RequestMapping("/ad")
public interface AdFeign {

    /***
     * Ad分页条件搜索实现
     * @param ad
     * @param page
     * @param size
     * @return
     */
    @PostMapping(value = "/search/{page}/{size}" )
    Result<PageInfo> findPage(@RequestBody(required = false) Ad ad, @PathVariable int page, @PathVariable int size);

    /***
     * Ad分页搜索实现
     * @param page:当前页
     * @param size:每页显示多少条
     * @return
     */
    @GetMapping(value = "/search/{page}/{size}" )
    Result<PageInfo> findPage(@PathVariable int page, @PathVariable int size);

    /***
     * 多条件搜索品牌数据
     * @param ad
     * @return
     */
    @PostMapping(value = "/search" )
    Result<List<Ad>> findList(@RequestBody(required = false) Ad ad);

    /***
     * 根据ID删除品牌数据
     * @param id
     * @return
     */
    @DeleteMapping(value = "/{id}" )
    Result delete(@PathVariable Integer id);

    /***
     * 修改Ad数据
     * @param ad
     * @param id
     * @return
     */
    @PutMapping(value="/{id}")
    Result update(@RequestBody Ad ad, @PathVariable Integer id);

    /***
     * 新增Ad数据
     * @param ad
     * @return
     */
    @PostMapping
    Result add(@RequestBody Ad ad);

    /***
     * 根据ID查询Ad数据
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    Result<Ad> findById(@PathVariable Integer id);

    /***
     * 查询Ad全部数据
     * @return
     */
    @GetMapping
    Result<List<Ad>> findAll();
}