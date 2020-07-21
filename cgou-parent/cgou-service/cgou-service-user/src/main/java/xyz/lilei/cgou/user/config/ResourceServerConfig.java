package xyz.lilei.cgou.user.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

/**
 * @ClassName ResourceServerConfig
 * @Author lilei
 * @Date 11/07/2020 23:38
 * @Version 1.0
 **/
@Configuration
@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true) // 激活方法上的PreAuthorize注解
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    // 公钥
    private static  final  String PUBLIC_KEY = "public.key";

    /**
     * 定义JwtTokenStore
     */
    @Bean
    public TokenStore tokenStore(JwtAccessTokenConverter jwtAccessTokenConverter) {
        return new JwtTokenStore(jwtAccessTokenConverter);
    }

    /**
     * 定义JJwtAccessTokenConverter
     */
    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter(){
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setVerifierKey(getPublicKey());
        return converter;
    }

    /**
     * 获取非对称加密公钥
     */
    private String getPublicKey() {
        Resource resource = new ClassPathResource(PUBLIC_KEY);
        try{
            InputStreamReader inputStreamResource = new InputStreamReader(resource.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(inputStreamResource);
            return bufferedReader.lines().collect(Collectors.joining("\n"));
        }catch (Exception ioe){
            return null;
        }
    }

    /**
     * HTTP安全配置
     */
    @Override
    public void configure(HttpSecurity http) throws Exception {
        // 所有请求都必须认证通过
        http.authorizeRequests()
                .antMatchers(
                        "/user/add" //配置地址放行
                        ,"/user/load/*"
                ).permitAll()
                .anyRequest()
                .authenticated(); //其它地址需要认证授权
    }
}
