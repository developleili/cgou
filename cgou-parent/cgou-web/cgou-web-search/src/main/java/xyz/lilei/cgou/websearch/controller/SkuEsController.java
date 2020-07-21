package xyz.lilei.cgou.websearch.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import xyz.lilei.cgou.common.utils.Page;
import xyz.lilei.cgou.search.feign.SkuEsFeign;
import xyz.lilei.cgou.search.pojo.SkuInfo;

import java.util.Map;

/**
 * @ClassName SkuEsController
 * @Author lilei
 * @Date 06/07/2020 21:50
 * @Version 1.0
 **/
@Controller
@RequestMapping(value = "/search")
public class SkuEsController {

    @Autowired
    private SkuEsFeign skuEsFeign;

    /**
     * 搜索
     * @param searchMap
     * @return
     */
    /**
     * 搜索
     * @param searchMap
     * @return
     */

    @GetMapping("/list")
    public String search(@RequestParam(required = false) Map<String, String> searchMap, Model model) {
        //1.调用搜索微服务的 feign  根据搜索的条件参数 查询 数据
        Map resultmap = skuEsFeign.search(searchMap);
        //2.将数据设置到model中     (模板文件中 根据th:标签数据展示)
        //搜索的结果设置
        model.addAttribute("result", resultmap);

        //3.设置搜索的条件 回显
        model.addAttribute("searchMap",searchMap);

        //拼接url
        String url = url(searchMap);
        model.addAttribute("url",url);

        //创建一个分页的对象  可以获取当前页 和总个记录数和显示的页码(以当前页为中心的5个页码)
        Page<SkuInfo> infoPage = new Page<SkuInfo>(
                Long.valueOf(resultmap.get("total").toString()),
                Integer.valueOf(resultmap.get("pageNum").toString()),
                Integer.valueOf(resultmap.get("pageSize").toString())
        );

        model.addAttribute("page",infoPage);
        //3.返回
        return "search";
    }

    private String url(Map<String, String> searchMap) {
        String url = "/search/list";
        if(searchMap!=null && searchMap.size()>0){
            url+="?";
            for (Map.Entry<String, String> stringStringEntry : searchMap.entrySet()) {
                String key = stringStringEntry.getKey();// keywords / brand  / category
                String value = stringStringEntry.getValue();//华为  / 华为  / 笔记本
                if(key.equals("pageNum")){
                    continue;
                }
                url+=key+"="+value+"&";
            }
            //去掉多余的&
            if(url.lastIndexOf("&")!=-1){
                url =  url.substring(0,url.lastIndexOf("&"));
            }


        }
        return url;
    }
}
