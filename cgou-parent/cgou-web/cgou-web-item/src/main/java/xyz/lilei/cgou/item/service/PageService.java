package xyz.lilei.cgou.item.service;

/**
 * @ClassName PageService
 * @Author lilei
 * @Date 06/07/2020 23:09
 * @Version 1.0
 **/
public interface PageService {
    /**
     * 根据商品的ID 生成静态页
     * @param spuId
     */
    public void createPageHtml(Long spuId) ;

    void deleteHtml(Long id);
}
