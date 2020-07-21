package xyz.lilei.cgou.search.service.impl;

import com.alibaba.fastjson.JSON;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.SearchResultMapper;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.aggregation.impl.AggregatedPageImpl;
import org.springframework.stereotype.Service;
import xyz.lilei.cgou.search.pojo.SkuInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @ClassName SearchResultMapperImpl
 * @Author lilei
 * @Date 06/07/2020 11:09
 * @Version 1.0
 **/
@Service
public class SearchResultMapperImpl implements SearchResultMapper {

    @Override
    public <T> AggregatedPage<T> mapResults(SearchResponse response, Class<T> clazz, Pageable pageable) {
        List<T> content = new ArrayList<>();
        if (response.getHits() == null || response.getHits().getTotalHits() < 0){
            return new AggregatedPageImpl<T>(content);
        }
        for (SearchHit hit : response.getHits()) {
            String sourceAsString = hit.getSourceAsString();
            SkuInfo skuInfo = JSON.parseObject(sourceAsString, SkuInfo.class);
            Map<String, HighlightField> highlightFields = hit.getHighlightFields();
            HighlightField highlightField = highlightFields.get("name");

            if (highlightField!=null){
                StringBuffer stringBuffer = new StringBuffer();
                for (Text fragment : highlightField.getFragments()) {
                    stringBuffer.append(fragment.toString());
                }
                skuInfo.setName(stringBuffer.toString());
            }
            content.add((T)skuInfo);
        }
        return new AggregatedPageImpl<T>(content, pageable, response.getHits().getTotalHits(), response.getAggregations(), response.getScrollId());
    }

}
