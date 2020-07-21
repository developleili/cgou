package xyz.lilei.cgou.content.pojo;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.lang.Long;
import java.lang.String;
import java.lang.Integer;
/****
 * @Author:admin
 * @Description:Content����
 * @Date 2019/6/14 19:13
 *****/
@Table(name="tb_content")
@Data
public class Content implements Serializable{

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
	private Long id;//

    @Column(name = "category_id")
	private Long categoryId;//������ĿID

    @Column(name = "title")
	private String title;//���ݱ���

    @Column(name = "url")
	private String url;//����

    @Column(name = "pic")
	private String pic;//ͼƬ����·��

    @Column(name = "status")
	private String status;//״̬,0��Ч��1��Ч

    @Column(name = "sort_order")
	private Integer sortOrder;//����

}
