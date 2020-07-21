package xyz.lilei.cgou.item.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.lilei.cgou.common.entity.Result;
import xyz.lilei.cgou.common.entity.StatusCode;
import xyz.lilei.cgou.item.service.PageService;

/**
 * @ClassName SkuEsController
 * @Author lilei
 * @Date 06/07/2020 21:50
 * @Version 1.0
 **/
@RestController
@RequestMapping("/page")
public class PageController {

    @Autowired
    private PageService pageService;

    /**
     * 生成静态页面
     * @param id
     * @return
     */
    @RequestMapping("/createHtml/{id}")
    public Result createHtml(@PathVariable(name="id") Long id){
        pageService.createPageHtml(id);
        return new Result(true, StatusCode.OK,"ok");
    }

    @RequestMapping("/deleteHtml/{id}")
    public Result deleteHtml(@PathVariable(name="id") Long id){
        pageService.deleteHtml(id);
        return new Result(true, StatusCode.OK,"ok");
    }
}
