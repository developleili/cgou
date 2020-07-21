package xyz.lilei.cgou.content.controller;

import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xyz.lilei.cgou.common.entity.Result;
import xyz.lilei.cgou.common.entity.StatusCode;
import xyz.lilei.cgou.content.pojo.ContentCategory;
import xyz.lilei.cgou.content.service.ContentCategoryService;

import java.util.List;

/****
 * @Author:admin
 * @Description:
 * @Date 2019/6/14 0:18
 *****/

@RestController
@RequestMapping("/contentCategory")
@CrossOrigin
public class ContentCategoryController {

    @Autowired
    private ContentCategoryService contentCategoryService;

    /***

     * @param contentCategory
     * @param page
     * @param size
     * @return
     */
    @PostMapping(value = "/search/{page}/{size}" )
    public Result<PageInfo> findPage(@RequestBody(required = false)  ContentCategory contentCategory, @PathVariable  int page, @PathVariable  int size){
        PageInfo<ContentCategory> pageInfo = contentCategoryService.findPage(contentCategory, page, size);
        return new Result(true,StatusCode.OK,"成功",pageInfo);
    }

    /***
     * @return
     */
    @GetMapping(value = "/search/{page}/{size}" )
    public Result<PageInfo> findPage(@PathVariable  int page, @PathVariable  int size){

        PageInfo<ContentCategory> pageInfo = contentCategoryService.findPage(page, size);
        return new Result<PageInfo>(true, StatusCode.OK,"成功",pageInfo);
    }

    /***

     * @param contentCategory
     * @return
     */
    @PostMapping(value = "/search" )
    public Result<List<ContentCategory>> findList(@RequestBody(required = false)  ContentCategory contentCategory){

        List<ContentCategory> list = contentCategoryService.findList(contentCategory);
        return new Result<List<ContentCategory>>(true,StatusCode.OK,"成功",list);
    }

    /***

     * @param id
     * @return
     */
    @DeleteMapping(value = "/{id}" )
    public Result delete(@PathVariable Long id){

        contentCategoryService.delete(id);
        return new Result(true,StatusCode.OK,"成功");
    }

    /***

     * @param contentCategory
     * @param id
     * @return
     */
    @PutMapping(value="/{id}")
    public Result update(@RequestBody  ContentCategory contentCategory,@PathVariable Long id){

        contentCategory.setId(id);

        contentCategoryService.update(contentCategory);
        return new Result(true,StatusCode.OK,"成功");
    }

    /***

     * @param contentCategory
     * @return
     */
    @PostMapping
    public Result add(@RequestBody   ContentCategory contentCategory){

        contentCategoryService.add(contentCategory);
        return new Result(true,StatusCode.OK,"成功");
    }

    /***

     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result<ContentCategory> findById(@PathVariable Long id){

        ContentCategory contentCategory = contentCategoryService.findById(id);
        return new Result<ContentCategory>(true,StatusCode.OK,"成功",contentCategory);
    }

    /***

     * @return
     */
    @GetMapping
    public Result<List<ContentCategory>> findAll(){

        List<ContentCategory> list = contentCategoryService.findAll();
        return new Result<List<ContentCategory>>(true, StatusCode.OK,"成功",list) ;
    }
}
