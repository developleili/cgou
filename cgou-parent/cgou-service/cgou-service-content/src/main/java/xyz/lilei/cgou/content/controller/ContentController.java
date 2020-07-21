package xyz.lilei.cgou.content.controller;

import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xyz.lilei.cgou.common.entity.Result;
import xyz.lilei.cgou.common.entity.StatusCode;
import xyz.lilei.cgou.content.pojo.Content;
import xyz.lilei.cgou.content.service.ContentService;

import java.util.List;

/****
 * @Author:admin
 * @Description:
 * @Date 2019/6/14 0:18
 *****/

@RestController
@RequestMapping("/content")
@CrossOrigin
public class ContentController {

    @Autowired
    private ContentService contentService;

    /***
     * @param content
     * @param page
     * @param size
     * @return
     */
    @PostMapping(value = "/search/{page}/{size}" )
    public Result<PageInfo> findPage(@RequestBody(required = false)  Content content, @PathVariable  int page, @PathVariable  int size){
        PageInfo<Content> pageInfo = contentService.findPage(content, page, size);
        return new Result(true, StatusCode.OK,"成功",pageInfo);
    }



    @GetMapping(value = "/search/{page}/{size}" )
    public Result<PageInfo> findPage(@PathVariable  int page, @PathVariable  int size){
        PageInfo<Content> pageInfo = contentService.findPage(page, size);
        return new Result<PageInfo>(true,StatusCode.OK,"成功",pageInfo);
    }

    /***
     * @param content
     * @return
     */
    @PostMapping(value = "/search" )
    public Result<List<Content>> findList(@RequestBody(required = false)  Content content){
        List<Content> list = contentService.findList(content);
        return new Result<List<Content>>(true,StatusCode.OK,"成功",list);
    }

    /***
     * @param id
     * @return
     */
    @DeleteMapping(value = "/{id}" )
    public Result delete(@PathVariable Long id){
        contentService.delete(id);
        return new Result(true,StatusCode.OK,"成功");
    }

    /***
     * @param content
     * @param id
     * @return
     */
    @PutMapping(value="/{id}")
    public Result update(@RequestBody  Content content,@PathVariable Long id){
        content.setId(id);
        contentService.update(content);
        return new Result(true,StatusCode.OK,"成功");
    }

    /***
     * @param content
     * @return
     */
    @PostMapping
    public Result add(@RequestBody   Content content){
        contentService.add(content);
        return new Result(true,StatusCode.OK,"成功");
    }

    /***
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result<Content> findById(@PathVariable Long id){
        Content content = contentService.findById(id);
        return new Result<Content>(true,StatusCode.OK,"成功",content);
    }

    @GetMapping("/findByCategoryId")
    Result<List<Content>> findByCategoryId(@RequestParam("categoryId") Long categoryId){
        List<Content> list = contentService.findByCategoryId(categoryId);
        return new Result<List<Content>>(true,StatusCode.OK,"成功",list);
    }

    /***
     * �
     * @return
     */
    @GetMapping
    public Result<List<Content>> findAll(){
        List<Content> list = contentService.findAll();
        return new Result<List<Content>>(true, StatusCode.OK,"成功",list) ;
    }
}
