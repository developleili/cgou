package xyz.lilei.cgou.order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.lilei.cgou.common.component.TokenDecode;
import xyz.lilei.cgou.common.entity.Result;
import xyz.lilei.cgou.common.entity.StatusCode;
import xyz.lilei.cgou.order.pojo.OrderItem;
import xyz.lilei.cgou.order.service.CartService;

import java.util.List;

/**
 * @ClassName CartController
 * @Author lilei
 * @Date 12/07/2020 16:39
 * @Version 1.0
 **/
@RestController
@CrossOrigin
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private TokenDecode tokenDecode;


    /***
     * 加入购物车
     * @param num:购买的数量
     * @param id：购买的商品(SKU)ID
     * @return
     */
    @RequestMapping(value = "/add")
    public Result add(Integer num, Long id){
        //用户名
        String userName = tokenDecode.getUserInfo().get("userName");
        //将商品加入购物车
        cartService.add(num,id,userName);
        return new Result(true, StatusCode.OK,"加入购物车成功！");
    }

    /***
     * 查询用户购物车列表
     * @return
     */
    @GetMapping(value = "/list")
    public Result list(){
        //用户名
        String userName = tokenDecode.getUserInfo().get("userName");
        List<OrderItem> orderItems = cartService.list(userName);
        return new Result(true,StatusCode.OK,"购物车列表查询成功！",orderItems);
    }
}
