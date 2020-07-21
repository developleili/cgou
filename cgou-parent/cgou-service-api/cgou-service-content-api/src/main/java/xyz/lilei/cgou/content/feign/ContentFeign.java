package xyz.lilei.cgou.content.feign;

import com.github.pagehelper.PageInfo;
import jdk.nashorn.internal.objects.annotations.Getter;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import xyz.lilei.cgou.common.entity.Result;
import xyz.lilei.cgou.content.pojo.Content;

import java.util.List;

/****
 * @Author:admin
 * @Description:
 * @Date 2019/6/18 13:58
 *****/
@FeignClient(name="content")
@RequestMapping("/content")
public interface ContentFeign {

    /***
     * @param content
     * @param page
     * @param size
     * @return
     */
    @PostMapping(value = "/search/{page}/{size}" )
    Result<PageInfo> findPage(@RequestBody(required = false) Content content, @PathVariable("page") int page, @PathVariable("size")  int size);

    /***
     * @return
     */
    @GetMapping(value = "/search/{page}/{size}" )
    Result<PageInfo> findPage(@PathVariable("page")  int page, @PathVariable("size")  int size);

    /***

     * @param content
     * @return
     */
    @PostMapping(value = "/search" )
    Result<List<Content>> findList(@RequestBody(required = false) Content content);

    /***

     * @param id
     * @return
     */
    @DeleteMapping(value = "/{id}" )
    Result delete(@PathVariable("id") Long id);

    /***

     * @param content
     * @param id
     * @return
     */
    @PutMapping(value="/{id}")
    Result update(@RequestBody Content content,@PathVariable("id") Long id);

    /***

     * @param content
     * @return
     */
    @PostMapping
    Result add(@RequestBody Content content);

    /***

     * @param id
     * @return
     */
    @GetMapping("/{id}")
    Result<Content> findById(@PathVariable("id") Long id);

    /***

     * @return
     */
    @GetMapping
    Result<List<Content>> findAll();

    @GetMapping("/findByCategoryId")
    Result<List<Content>> findByCategoryId(@RequestParam("categoryId") Long categoryId);
}