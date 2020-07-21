package xyz.lilei.cgou.oauth.service;

import xyz.lilei.cgou.oauth.util.AuthToken;

/**
 * @ClassName AuthService
 * @Author lilei
 * @Date 11/07/2020 17:58
 * @Version 1.0
 **/
public interface AuthService {
    /***
     * 授权认证方法
     */
    AuthToken login(String username, String password, String clientId, String clientSecret);
}
