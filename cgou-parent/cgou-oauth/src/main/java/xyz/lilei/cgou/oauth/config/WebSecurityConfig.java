package xyz.lilei.cgou.oauth.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @ClassName WebSecurityConfig
 * @Author lilei
 * @Date 11/07/2020 15:53
 * @Version 1.0
 **/
@Configuration
@EnableWebSecurity
@Order(-1)
@Slf4j
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    /****
     *
     * @param http
     * @throws Exception
     */
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .httpBasic()        //启用Http基本身份验证
                .and()
                .formLogin()       //启用表单身份验证
                .loginPage("/oauth/login")
                .loginProcessingUrl("/user/login")
                .and()
                .authorizeRequests()    //限制基于Request请求访问
                .anyRequest()
                .authenticated();       //其他请求都需要经过验证

    }
    /**
     * ���԰�ȫ���ص�url
     */
    @Override
    public void configure(WebSecurity web) {
        log.info("web 加载成功:{}", web.toString());
        web.ignoring().antMatchers(
                        "/user/login",
                        "/user/logout","/oauth/login","/css/**","/data/**","/fonts/**","/img/**","/js/**");
    }

    /**
     * ����Ȩ����֤�������
     */
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    /***
     * ����BCryptPasswordEncoder��������б���
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}
