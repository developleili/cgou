package xyz.lilei.cgou.item.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.lilei.cgou.common.entity.Result;

/**
 * @ClassName PageFeign
 * @Author lilei
 * @Date 06/07/2020 23:55
 * @Version 1.0
 **/
@FeignClient("item")
@RestController
@RequestMapping("/page")
public interface PageFeign {

    @RequestMapping("/createHtml/{id}")
    Result createHtml(@PathVariable(name="id") Long id);

    @RequestMapping("/deleteHtml/{id}")
    Result deleteHtml(@PathVariable(name="id") Long id);
}
