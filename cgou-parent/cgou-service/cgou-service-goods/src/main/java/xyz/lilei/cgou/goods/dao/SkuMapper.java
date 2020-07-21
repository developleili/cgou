package xyz.lilei.cgou.goods.dao;

import org.apache.ibatis.annotations.Update;
import tk.mybatis.mapper.common.Mapper;
import xyz.lilei.cgou.goods.pojo.Sku;
import xyz.lilei.cgou.order.pojo.OrderItem;

/****
 * @Author:admin
 * @Description:Sku的Dao
 * @Date 2019/6/14 0:12
 *****/
public interface SkuMapper extends Mapper<Sku> {

    /**
     * 递减库存
     * @param orderItem
     * @return
     */
    @Update("UPDATE tb_sku SET num=num-#{num},sale_num=sale_num+#{num} WHERE id=#{skuId} AND num>=#{num}")
    int decrCount(OrderItem orderItem);

    @Update("UPDATE tb_sku SET num=num+#{num},sale_num=sale_num-#{num} WHERE id=#{skuId}")
    int incrCount(String skuId, Integer num);
}
