package xyz.lilei.cgou.search.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * @ClassName SkuEsFeign
 * @Author lilei
 * @Date 06/07/2020 21:34
 * @Version 1.0
 **/
@FeignClient(name="search")
@RequestMapping("/search")
public interface SkuEsFeign {

    /**
     * 搜索
     * @param searchMap
     * @return
     */
    @GetMapping
    Map<String, Object> search(@RequestParam(required = false) Map<String, String> searchMap);
}
