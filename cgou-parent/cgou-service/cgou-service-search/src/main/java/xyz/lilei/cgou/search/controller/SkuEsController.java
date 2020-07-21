package xyz.lilei.cgou.search.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xyz.lilei.cgou.common.entity.Result;
import xyz.lilei.cgou.common.entity.StatusCode;
import xyz.lilei.cgou.search.service.SkuEsService;

import java.util.Map;

/**
 * @ClassName SkuEsController
 * @Description TODO
 * @Author lilei
 * @Date 05/07/2020 21:20
 * @Version 1.0
 **/
@RestController
@RequestMapping(value = "/search")
@CrossOrigin
public class SkuEsController {

    @Autowired
    private SkuEsService skuEsService;

    /**
     * 导入数据
     * @return
     */
    @GetMapping("/import")
    public Result search(){
        skuEsService.importSku();
        return new Result(true, StatusCode.OK,"导入数据到索引库中成功！");
    }

    /**
     * 搜索
     * @param searchMap
     * @return
     */
    @PostMapping
    public Map search(@RequestBody(required = false) Map searchMap){
        return  skuEsService.search(searchMap);
    }
}