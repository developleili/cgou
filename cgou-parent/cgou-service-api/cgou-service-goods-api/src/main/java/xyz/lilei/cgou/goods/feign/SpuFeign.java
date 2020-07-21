package xyz.lilei.cgou.goods.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import xyz.lilei.cgou.common.entity.Result;
import xyz.lilei.cgou.goods.pojo.Sku;
import xyz.lilei.cgou.goods.pojo.Spu;

import java.util.List;

/**
 * @ClassName SkuFeign
 * @Author lilei
 * @Date 05/07/2020 21:16
 * @Version 1.0
 **/
@FeignClient(name="goods")
@RequestMapping(value = "/spu")
public interface SpuFeign {


    /***
     * 根据SpuID查询Spu信息
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    Result<Spu> findById(@PathVariable(name = "id") Long id);
}
