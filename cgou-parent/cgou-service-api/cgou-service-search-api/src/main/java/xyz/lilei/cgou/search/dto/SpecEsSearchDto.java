package xyz.lilei.cgou.search.dto;

import com.github.pagehelper.Page;
import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName SearchDto
 * @Description TODO
 * @Author lilei
 * @Date 06/07/2020 10:20
 * @Version 1.0
 **/
@Data
public class SpecEsSearchDto extends Page implements Serializable {

    private Long id;

    //SKU名称
    private String name;

    //商品价格，单位为：元
    private Long price;

    //库存数量
    private Integer num;


    //创建时间
    private Date createTime;

    //更新时间
    private Date updateTime;

    //是否默认
    private String isDefault;

    //SPUID
    private Long spuId;

    //类目ID
    private Long categoryId;

    //类目名称
    private String categoryName;

    //品牌名称
    private String brandName;

    //规格
    private String spec;

}
