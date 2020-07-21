package xyz.lilei.cgou.search.service;

import java.util.Map;

/**
 * @ClassName SkuService
 * @Description TODO
 * @Author lilei
 * @Date 05/07/2020 21:19
 * @Version 1.0
 **/
public interface SkuEsService {

    /***
     * 导入SKU数据
     */
    void importSku();

    /***
     * 搜索
     * @param searchMap
     * @return
     */
    Map<String, Object> search(Map<String, String> searchMap);
}
