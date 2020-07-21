package xyz.lilei.cgou.gateway.web.filter;

import org.apache.commons.lang.StringUtils;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpCookie;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @ClassName AuthorizeFilter
 * @Author lilei
 * @Date 08/07/2020 22:28
 * @Version 1.0
 **/
@Component
public class AuthorizeFilter implements GlobalFilter, Ordered {

    //令牌头名字
    private static final String AUTHORIZE_TOKEN = "Authorization";

    //用户登陆请求地址
    private static final String USER_LOGIN_URL = "http://localhost:9200/oauth/login";

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 获取request, response对象
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();
        // 获取请求的路径
        String path = request.getURI().getPath();

        //如果是登录、goods等开放的微服务[这里的goods部分开放],则直接放行,这里不做完整演示，完整演示需要设计一套权限系统
        if (URLFilter.hasAuthorize(path)) {
            //放行
            return chain.filter(exchange);
        }
        // 获取头文件的令牌信息
        String token = request.getHeaders().getFirst(AUTHORIZE_TOKEN);

        if (StringUtils.isEmpty(token)){
            token = request.getQueryParams().getFirst(AUTHORIZE_TOKEN);
        }
        // 从cookie中获取jwt
        if (StringUtils.isEmpty(token)){
            HttpCookie first = request.getCookies().getFirst(AUTHORIZE_TOKEN);
            if (first!=null){
                token = first.getValue();
            }
        }
        //如果为空，则输出错误代码
        if (StringUtils.isEmpty(token)) {
            //设置方法不允许被访问，405错误代码
            return needAuthorization(USER_LOGIN_URL+"?FROM="+request.getURI(), exchange);
        }
        try {
            //Claims claims = JwtUtil.parseJWT(token);
            //将令牌数据添加到头文件中
            request.mutate().header(AUTHORIZE_TOKEN, "Bearer "+token);
        } catch (Exception e) {
            e.printStackTrace();
            //解析失败，响应401错误
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            return response.setComplete();
        }
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }

    /**
     * 响应设置
     */
    public Mono<Void> needAuthorization(String url, ServerWebExchange exchange){
        ServerHttpResponse response  = exchange.getResponse();
        response.setStatusCode(HttpStatus.SEE_OTHER);
        response.getHeaders().set("Location",url);
        return exchange.getResponse().setComplete();
    }
}
