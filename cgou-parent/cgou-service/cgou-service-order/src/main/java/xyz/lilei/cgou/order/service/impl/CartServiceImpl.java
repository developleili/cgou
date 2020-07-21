package xyz.lilei.cgou.order.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import springfox.documentation.spring.web.json.Json;
import xyz.lilei.cgou.common.entity.Result;
import xyz.lilei.cgou.goods.feign.SkuFeign;
import xyz.lilei.cgou.goods.feign.SpuFeign;
import xyz.lilei.cgou.goods.pojo.Sku;
import xyz.lilei.cgou.goods.pojo.Spu;
import xyz.lilei.cgou.order.pojo.OrderItem;
import xyz.lilei.cgou.order.service.CartService;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName CartServiceImpl
 * @Author lilei
 * @Date 12/07/2020 16:26
 * @Version 1.0
 **/
@Service
public class CartServiceImpl implements CartService {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private SkuFeign skuFeign;

    @Autowired
    private SpuFeign spuFeign;

    @Override
    public void add(Integer num, Long id, String userName) {
        if (num <= 0){
            OrderItem order = JSON.parseObject(JSON.toJSONString(redisTemplate.boundHashOps("Cart_" + userName).get(id)), OrderItem.class);
            Integer num1 = order.getNum();
            if (num1 - num <= 0){
                redisTemplate.boundHashOps("Cart_" + userName).delete(id);
            }
            order.setNum(num1 - num);
            redisTemplate.boundHashOps("Cart_" + userName).put(id, order);
        }
        Result<Sku> resultSku  = skuFeign.findById(id);
        if (resultSku == null || !resultSku.isFlag()){
            return;
        }
        Sku sku = resultSku.getData();
        //获取SPU
        Result<Spu> resultSpu = spuFeign.findById(sku.getSpuId());
        // 将sku转换为orderItem
        OrderItem orderItem = sku2OrderItem(sku,resultSpu.getData(), num);
        redisTemplate.boundHashOps("Cart_"+userName).put(id, orderItem);
    }

    /***
     * 查询用户购物车数据
     * @param username
     * @return
     */
    @Override
    public List<OrderItem> list(String username) {
        //查询所有购物车数据
        return JSONObject.parseArray(JSON.toJSONString(redisTemplate.boundHashOps("Cart_" + username).values()), OrderItem.class);
    }

    /***
     * SKU转成OrderItem
     * @param sku
     * @param num
     * @return
     */
    private OrderItem sku2OrderItem(Sku sku, Spu spu, Integer num){
        OrderItem orderItem = new OrderItem();
        orderItem.setSpuId(sku.getSpuId());
        orderItem.setSkuId(sku.getId());
        orderItem.setName(sku.getName());
        orderItem.setPrice(sku.getPrice());
        orderItem.setNum(num);
        orderItem.setMoney(num*orderItem.getPrice());       //单价*数量
        orderItem.setPayMoney(num*orderItem.getPrice());    //实付金额
        orderItem.setImage(sku.getImage());
        orderItem.setWeight(sku.getWeight()*num);           //重量=单个重量*数量

        //分类ID设置
        orderItem.setCategoryId1(spu.getCategory1Id());
        orderItem.setCategoryId2(spu.getCategory2Id());
        orderItem.setCategoryId3(spu.getCategory3Id());
        return orderItem;
    }
}
