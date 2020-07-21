package xyz.lilei.cgou.search.dao;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;
import xyz.lilei.cgou.search.pojo.SkuInfo;

/**
 * @ClassName SkuEsMapper
 * @Description TODO
 * @Author lilei
 * @Date 05/07/2020 21:17
 * @Version 1.0
 **/
@Repository
public interface SkuEsMapper extends ElasticsearchRepository<SkuInfo, Long> {
}
