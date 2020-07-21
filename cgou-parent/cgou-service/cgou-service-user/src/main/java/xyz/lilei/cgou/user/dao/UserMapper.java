package xyz.lilei.cgou.user.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import tk.mybatis.mapper.common.Mapper;
import xyz.lilei.cgou.user.pojo.User;

/****
 * @Author:lilei
 * @Description:
 * @Date 2020/7/05 21:01
 *****/
public interface UserMapper extends Mapper<User> {

    /***
     * 增加用户积分
     * @param username
     * @param pint
     * @return
     */
    @Update("UPDATE tb_user SET points=points+#{point} WHERE  username=#{username}")
    int addUserPoints(@Param("username") String username, @Param("point") Integer pint);
}
