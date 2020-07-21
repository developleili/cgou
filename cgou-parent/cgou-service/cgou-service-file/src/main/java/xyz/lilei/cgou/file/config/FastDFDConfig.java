package xyz.lilei.cgou.file.config;

import lombok.extern.slf4j.Slf4j;
import org.csource.fastdfs.ClientGlobal;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

/**
 * @ClassName FastDFDConfig
 * @Description TODO
 * @Author lilei
 * @Date 03/07/2020 08:40
 * @Version 1.0
 **/
@Configuration
@Slf4j
public class FastDFDConfig{

    /***
     * 初始化tracker信息
     */
    static {
        try {
            //获取tracker的配置文件fdfs_client.conf的位置
            log.info("111111");
            String filePath = new ClassPathResource("fdfs_client.conf").getPath();
            //加载tracker配置信息
            ClientGlobal.init(filePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
