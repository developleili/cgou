package xyz.lilei.cgou.content.feign;

import com.github.pagehelper.PageInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import xyz.lilei.cgou.common.entity.Result;
import xyz.lilei.cgou.content.pojo.ContentCategory;

import java.util.List;

/****
 * @Author:admin
 * @Description:
 * @Date 2019/6/18 13:58
 *****/
@FeignClient(name="content")
@RequestMapping("/contentCategory")
public interface ContentCategoryFeign {

    /***
     * ContentCategory��ҳ��������ʵ��
     * @param contentCategory
     * @param page
     * @param size
     * @return
     */
    @PostMapping(value = "/search/{page}/{size}" )
    Result<PageInfo> findPage(@RequestBody(required = false) ContentCategory contentCategory, @PathVariable("page")  int page, @PathVariable("page")  int size);

    /***
     * ContentCategory��ҳ����ʵ��
     * @param page:��ǰҳ
     * @param size:ÿҳ��ʾ������
     * @return
     */
    @GetMapping(value = "/search/{page}/{size}" )
    Result<PageInfo> findPage(@PathVariable("page") int page, @PathVariable("size")  int size);

    /***
     * ����������Ʒ������
     * @param contentCategory
     * @return
     */
    @PostMapping(value = "/search" )
    Result<List<ContentCategory>> findList(@RequestBody(required = false) ContentCategory contentCategory);

    /***
     * ����IDɾ��Ʒ������
     * @param id
     * @return
     */
    @DeleteMapping(value = "/{id}" )
    Result delete(@PathVariable("id") Long id);

    /***
     * �޸�ContentCategory����
     * @param contentCategory
     * @param id
     * @return
     */
    @PutMapping(value="/{id}")
    Result update(@RequestBody ContentCategory contentCategory,@PathVariable("id") Long id);

    /***
     * @param contentCategory
     * @return
     */
    @PostMapping
    Result add(@RequestBody ContentCategory contentCategory);

    /***
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    Result<ContentCategory> findById(@PathVariable("id") Long id);

    /***
     * @return
     */
    @GetMapping
    Result<List<ContentCategory>> findAll();
}