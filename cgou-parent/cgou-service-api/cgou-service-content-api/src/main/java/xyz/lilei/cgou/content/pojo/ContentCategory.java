package xyz.lilei.cgou.content.pojo;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.lang.Long;
import java.lang.String;
/****
 * @Author:admin
 * @Description:ContentCategory����
 * @Date 2019/6/14 19:13
 *****/
@Table(name="tb_content_category")
@Data
public class ContentCategory implements Serializable{

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
	private Long id;//��ĿID

    @Column(name = "name")
	private String name;//��������




}
