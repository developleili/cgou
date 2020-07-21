package xyz.lilei.cgou.common.interceptor;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * @ClassName FeignInterceptor
 * @Author lilei
 * @Date 12/07/2020 17:06
 * @Version 1.0
 **/
public class FeignInterceptor implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate requestTemplate) {
        //使用RequestContextHolder工具获取request相关变量
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes==null){
            return;
        }
        // 取出request
        HttpServletRequest request = attributes.getRequest();
        Enumeration<String> headerNames = request.getHeaderNames();
        if (headerNames == null){
            return;
        }
        while (headerNames.hasMoreElements()){
            String element = headerNames.nextElement();
            String header = request.getHeader(element);
            requestTemplate.header(element, header);
        }
    }
}
