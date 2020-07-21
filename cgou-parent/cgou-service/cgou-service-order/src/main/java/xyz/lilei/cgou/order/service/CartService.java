package xyz.lilei.cgou.order.service;

import io.swagger.models.auth.In;
import xyz.lilei.cgou.order.pojo.OrderItem;

import java.util.List;

/**
 * @ClassName CartService
 * @Author lilei
 * @Date 12/07/2020 16:25
 * @Version 1.0
 **/
public interface CartService {

    /**
     * 添加购物车
     */
    void add(Integer num, Long id, String userName);

    /***
     * 查询用户的购物车数据
     * @param username
     * @return
     */
    List<OrderItem> list(String username);
}
