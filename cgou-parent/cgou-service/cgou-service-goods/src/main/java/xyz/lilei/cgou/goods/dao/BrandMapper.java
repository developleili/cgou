package xyz.lilei.cgou.goods.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;
import xyz.lilei.cgou.goods.pojo.Brand;

import java.util.List;

/**
 * @ClassName BrandMapper
 * @Description TODO
 * @Author jack
 * @Date 02/07/2020 00:45
 * @Version 1.0
 **/
public interface BrandMapper extends Mapper<Brand> {

    /**
     * 查询分类对应的品牌集合
     */
    @Select("SELECT tb.* FROM tb_category_brand tcb join tb_brand tb on tb.id = tcb.brand_id where tcb.category_id = #{categaryId}")
    List<Brand> findByCategoryId(@Param("categoryId") Integer categoryId);

}