package xyz.lilei.cgou.search.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.StringTerms;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.sort.SortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;
import xyz.lilei.cgou.common.entity.Result;
import xyz.lilei.cgou.goods.feign.SkuFeign;
import xyz.lilei.cgou.goods.pojo.Sku;
import xyz.lilei.cgou.search.dao.SkuEsMapper;
import xyz.lilei.cgou.search.pojo.SkuInfo;
import xyz.lilei.cgou.search.service.SkuEsService;

import java.util.*;

/**
 * @ClassName SkuEsServiceImpl
 * @Description TODO
 * @Author lilei
 * @Date 05/07/2020 21:19
 * @Version 1.0
 **/
@Service
public class SkuEsServiceImpl implements SkuEsService {
    @Autowired
    private SkuFeign skuFeign;

    @Autowired
    private SkuEsMapper skuEsMapper;


    @Autowired
    private ElasticsearchTemplate esTemplate;

    public Map<String, Object> search(Map<String, String> searchMap) {
        //1.获取关键字的值
        String keywords = searchMap.get("keywords");

        if (StringUtils.isEmpty(keywords)) {
            keywords = "华为";//赋值给一个默认的值
        }
        //创建查询对象 的构建对象
        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
        //设置高亮条件
        nativeSearchQueryBuilder.withHighlightFields(new HighlightBuilder.Field("name"));
        nativeSearchQueryBuilder.withHighlightBuilder(new HighlightBuilder().preTags("<em style\"color:red\">").postTags("</em>"));
        //设置查询的条件
        nativeSearchQueryBuilder.addAggregation(AggregationBuilders.terms("skuBrandNameGroup").field("brandName").size(50));
        nativeSearchQueryBuilder.addAggregation(AggregationBuilders.terms("skuCategoryGroup").field("categoryName").size(50));
        nativeSearchQueryBuilder.addAggregation(AggregationBuilders.terms("skuSpecGroup").field("sepc.keyword").size(100));
        nativeSearchQueryBuilder.withQuery(QueryBuilders.multiMatchQuery(keywords, "name", "brandName", "categoryName"));

        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        if (StringUtils.isNotEmpty(searchMap.get("brand"))){
            boolQueryBuilder.filter(QueryBuilders.termQuery("brandName", searchMap.get("brand")));
        }
        if (StringUtils.isNotEmpty(searchMap.get("category"))){
            boolQueryBuilder.filter(QueryBuilders.termQuery("category", searchMap.get("category")));
        }
        for (String key : searchMap.keySet()) {
            if (key.startsWith("spec_")){
                boolQueryBuilder.filter(QueryBuilders.termQuery("specMap." + key.substring(5)+".keyword", searchMap.get(key)));
            }
        }
        // price : *-500 500-* 100-500
        String price = searchMap.get("price");
        if (StringUtils.isNotEmpty(price)){
            String[] split = price.split("-");
            if (!"*".equals(split[1]) && !"*".equals(split[0]))
                boolQueryBuilder.filter(QueryBuilders.rangeQuery("price" ).from(split[0], true).to(split[1]));
            else if ("*".equals(split[0]))
                boolQueryBuilder.filter(QueryBuilders.rangeQuery("price").lte(split[1]));
            else
                boolQueryBuilder.filter(QueryBuilders.rangeQuery("price").gte(split[0]));
        }
        // 构建分页
        String pageNum = searchMap.get("pageNum");
        if (StringUtils.isNotEmpty(pageNum)){
            String pageSize = searchMap.get("pageSize");
            if (StringUtils.isNotEmpty(pageSize)){
                pageSize = "10";
            }
            nativeSearchQueryBuilder.withPageable(PageRequest.of((Integer.parseInt(pageNum)-1), Integer.parseInt(pageSize)));
        }
        nativeSearchQueryBuilder.withQuery(boolQueryBuilder);
        // 构建排序
        String sortOrder = searchMap.get("sortOrder");
        String sortField = searchMap.get("sortField");
        if (StringUtils.isNotEmpty(sortOrder) && StringUtils.isNotEmpty(sortField)){
            nativeSearchQueryBuilder.withSort(SortBuilders.fieldSort(sortField).order(sortOrder.equals("DESC")?SortOrder.DESC:SortOrder.ASC));
        }
        //构建查询对象
        NativeSearchQuery query = nativeSearchQueryBuilder.build();

        //执行查询
        AggregatedPage<SkuInfo> skuPage = esTemplate.queryForPage(query, SkuInfo.class, new SearchResultMapperImpl());
        StringTerms categoryTerms = (StringTerms)skuPage.getAggregation("skuCategoryGroup");
        StringTerms brandTerms = (StringTerms)skuPage.getAggregation("skuBrandNameGroup");
        StringTerms specTerms = (StringTerms)skuPage.getAggregation("skuSpecGroup");
        List<String> categoryList = getTargetList(categoryTerms);
        List<String> brandTermList = getTargetList(brandTerms);
        Map<String, Set<String>> specMap = getEsSpecMap(specTerms);
        //6.返回结果
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("categoryList", categoryList);
        resultMap.put("brandTermList", brandTermList);
        resultMap.put("specMap", specMap);
        resultMap.put("rows", skuPage.getContent());
        resultMap.put("total", skuPage.getTotalElements());
        resultMap.put("totalPages", skuPage.getTotalPages());
        return resultMap;
    }

    private Map<String, Set<String>> getEsSpecMap(StringTerms specTerms) {
        Map<String, Set<String>> specMap = new HashMap<>();
        Set<String> specSet = new HashSet<>();
        if (specTerms!=null){
            for (StringTerms.Bucket bucket : specTerms.getBuckets()) {
                specSet.add(bucket.getKeyAsString());
            }
        }
        for (String spec : specSet) {
            Map<String, String> map = JSON.parseObject(spec, Map.class);
            for (Map.Entry<String, String> entry : map.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                Set<String> specOption = specMap.get(key);
                if (specOption==null){
                    specOption = new HashSet<>();
                }
                specOption.add(value);
                specMap.put(key, specOption);
            }
        }
        return specMap;
    }

    private List<String> getTargetList(StringTerms stringTerms) {
        ArrayList<String> targetList = new ArrayList<>();
        if (stringTerms != null){
            for (StringTerms.Bucket bucket : stringTerms.getBuckets()) {
                String keyAsString = bucket.getKeyAsString();
                targetList.add(keyAsString);
            }
        }
        return targetList;
    }

    /**
     * 导入sku数据到es
     */
    @Override
    public void importSku(){
        //调用cgou-service-goods微服务
        Result<List<Sku>> skuListResult = skuFeign.findByStatus("1");
        //将数据转成search.Sku
        List<SkuInfo> skuInfos=  JSON.parseArray(JSON.toJSONString(skuListResult.getData()),SkuInfo.class);
        for(SkuInfo skuInfo:skuInfos){
            Map<String, Object> specMap= JSON.parseObject(skuInfo.getSpec()) ;
            skuInfo.setSpecMap(specMap);
        }
        skuEsMapper.saveAll(skuInfos);
    }
}
