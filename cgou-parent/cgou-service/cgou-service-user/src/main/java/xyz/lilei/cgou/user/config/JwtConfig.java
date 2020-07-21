package xyz.lilei.cgou.user.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import xyz.lilei.cgou.common.component.TokenDecode;

/**
 * @ClassName JwtConfig
 * @Author lilei
 * @Date 12/07/2020 21:57
 * @Version 1.0
 **/
@Configuration
public class JwtConfig {

    @Bean
    public TokenDecode tokenDecode(){
        return new TokenDecode();
    }
}
