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

    //SKU����
    private String name;

    //��Ʒ�۸񣬵�λΪ��Ԫ
    private Long price;

    //�������
    private Integer num;


    //����ʱ��
    private Date createTime;

    //����ʱ��
    private Date updateTime;

    //�Ƿ�Ĭ��
    private String isDefault;

    //SPUID
    private Long spuId;

    //��ĿID
    private Long categoryId;

    //��Ŀ����
    private String categoryName;

    //Ʒ������
    private String brandName;

    //���
    private String spec;

}
