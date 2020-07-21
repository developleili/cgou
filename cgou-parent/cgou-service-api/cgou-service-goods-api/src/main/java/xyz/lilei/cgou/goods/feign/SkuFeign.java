package xyz.lilei.cgou.goods.feign;

import com.alibaba.fastjson.JSONObject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import xyz.lilei.cgou.common.entity.Result;
import xyz.lilei.cgou.goods.pojo.Sku;

import java.util.List;

/**
 * @ClassName SkuFeign
 * @Author lilei
 * @Date 05/07/2020 21:16
 * @Version 1.0
 **/
@FeignClient(name="goods")
@RequestMapping(value = "/sku")
public interface SkuFeign {

    /***
     * 根据审核状态查询Sku
     * @param status
     * @return
     */
    @GetMapping("/status/{status}")
    Result<List<Sku>> findByStatus(@PathVariable("status") String status);

    /**
     * 根据条件搜索
     * @param sku
     * @return
     */
    @PostMapping(value = "/search" )
    Result<List<Sku>> findList(@RequestBody(required = false) Sku sku);

    /***
     * 根据ID查询SKU信息
     * @param id : sku的ID
     */
    @GetMapping(value = "/{id}")
    Result<Sku> findById(@PathVariable(value = "id", required = true) Long id);

    /***
     * 库存递减
     * @param username
     * @return
     */
    @PostMapping(value = "/decr/count")
    Result decrCount(@RequestParam(value = "username") String username);


    /***
     * 库存回滚
     * @param request
     * @return
     */
    @PostMapping(value = "/incr/count")
    void incrCount(@RequestBody JSONObject request);
}
