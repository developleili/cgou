package xyz.lilei.cgou.goods.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import xyz.lilei.cgou.common.entity.Result;
import xyz.lilei.cgou.goods.pojo.Category;

/**
 * @ClassName CategoryFeign
 * @Author lilei
 * @Date 06/07/2020 23:05
 * @Version 1.0
 **/
@FeignClient(name="goods")
@RequestMapping(value = "/category")
public interface CategoryFeign {
    /**
     * 获取分类的对象信息
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result<Category> findById(@PathVariable(name = "id") Integer id);
}
