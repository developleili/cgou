package xyz.lilei.cgou.sleuth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import xyz.lilei.cgou.sleuth.web.MyTagsProvider;

/**
 * @ClassName MyTagsProvider
 * @Author lilei
 * @Date 25/07/2020 10:20
 * @Version 1.0
 **/
@Configuration
public class MvcConfig {
    /**
     * 将MyTagsProvider注入
     *
     * @return
     */
    @Bean
    public MyTagsProvider myTagsProvider() {
        return new MyTagsProvider();
    }
}
