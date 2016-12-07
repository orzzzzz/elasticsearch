package com.icinfo.springmvc.elasticsearch.service.impl;

import com.icinfo.springmvc.elasticsearch.model.Article;
import com.icinfo.springmvc.elasticsearch.service.IJestClientService;
import io.searchbox.client.JestClient;
import io.searchbox.client.JestResult;
import io.searchbox.core.*;
import io.searchbox.indices.CreateIndex;
import io.searchbox.indices.DeleteIndex;
import io.searchbox.indices.IndicesExists;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 描述：jestClient操作Service
 */
@Service
public class JestClientServiceImpl implements IJestClientService {
    /**
     * 日志记录器
     */
    private static final Logger logger = LoggerFactory.getLogger(JestClientServiceImpl.class);

    /**
     * 第三方工具注入
     */
    @Autowired
    private JestClient jestClient;

    int num = 10;

    /**
     * 创建es 索引
     *
     * @param indexName 索引名称
     * @param indexType 索引类型
     */
    public void createIndex(String indexName, String indexType) {
        long start = System.currentTimeMillis();
        try {
            if(deleteIndex(indexName)){
               logger.error("索引删除失败！");
                return;
            }

            JestResult result = jestClient.execute(new CreateIndex.Builder(indexName).build());
            if (result == null || !result.isSucceeded()) {
                throw new Exception(result.getErrorMessage() + "创建索引失败!");
            } else {
                System.out.println("创建索引成功-------------" + indexName);
            }
            //// 创建article数据
            //Bulk.Builder bulkBuilder = new Bulk.Builder().defaultIndex(indexName)
            //        .defaultType(indexType);
            //
            //for (int i = 0; i < num; i++) {
            //    Article article = new Article();
            //    article.setId("888");
            //    article.setTitle("习近平致电祝贺特朗普当选美国总统");
            //    article.setContent("11月9日，国家主席习近平向美国当选总统唐纳德·特朗普致贺电。\n" +
            //            "　　习近平在贺电中指出，作为最大的发展中国家、最大的发达国家、世界前两大经济体，中美两国在维护世界和平稳定、促进全球发展繁荣方面肩负着特殊的重要责任，拥有广泛的共同利益。发展长期健康稳定的中美关系，符合两国人民根本利益，也是国际社会普遍期待。我高度重视中美关系，期待着同你一道努力，秉持不冲突不对抗、相互尊重、合作共赢的原则，拓展两国在双边、地区、全球层面各领域合作，以建设性方式管控分歧，推动中美关系在新的起点上取得更大进展，更好造福两国人民和各国人民。\n" +
            //            "　　国家副主席李源潮致电迈克·彭斯，祝贺他当选美国副总统。");
            //    article.setAuthor("zzz");
            //    article.setAge(18);
            //    article.setTime(new Long(888));
            //    article.setCreateTime(new Date());
            //    article.setScore(new BigDecimal(88.878));
            //    Index.Builder builder1 = new Index.Builder(article);
            //    Index index = builder1.build();
            //
            //    bulkBuilder.addAction(index);
            //}
            //Bulk bulk = bulkBuilder.build();
            //jestClient.execute(bulk);
        } catch (Exception e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        System.out.println("创建索引时间:数据量是  " + num + "记录,共用时间 -->> " + (end - start) + " 毫秒");
    }

    /**
     * 删除索引
     *
     * @param indexName 索引名称
     * @return 成功：true，失败：false
     * @throws Exception
     */
    public boolean deleteIndex(String indexName) throws Exception{
        IndicesExists indicesExists = new IndicesExists.Builder(indexName).build();
        JestResult indicesExistsRlt = jestClient.execute(indicesExists);
        if(!indicesExistsRlt.isSucceeded()){
            logger.error("索引不存在！");
        }
        DeleteIndex deleteIndex = new DeleteIndex.Builder(indexName).build();
        JestResult deleteIndexRlt = jestClient.execute(deleteIndex);
        return deleteIndexRlt.isSucceeded();
    }

    /**
     * 根据id查询具体内容
     *
     * @param indexId 文章id
     * @return 文章
     * @throws Exception
     */
    public Article getArticle(String indexId) throws Exception {
        Get.Builder build = new Get.Builder("articles", indexId);
        Get get = build.build();
        JestResult result = jestClient.execute(get);
        return result.getSourceAsObject(Article.class);
    }

    /**
     * 全文搜索新闻
     *
     * @param param 查询参数
     * @return 查询结果
     */
    public String searchsNews(String param) throws IOException {
        long start = System.currentTimeMillis();
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.queryString(param));

        Search.Builder builder = new Search.Builder(searchSourceBuilder.toString());
        Search search = builder.build();
        //search.addIndex("article");
        //search.addType("article");
        JestResult result = jestClient.execute(search);
        long end = System.currentTimeMillis();
        System.out.println("在" + num + "条记录中,搜索新闻,共用时间 -->> " + (end - start) + " 毫秒");
        return result.getJsonString();
    }

    /**
     * 修改数据
     *
     * @param indexObject 修改内容
     * @param indexId     文章id
     * @param indexName   索引名称
     * @param indexType   索引类型
     * @return 修改结果
     * @throws Exception
     */
    public Boolean updateArticle(Object indexObject, String indexId, String indexName, String indexType) throws Exception {
        Index.Builder builder1 = new Index.Builder(indexObject);
        Index index = builder1.index(indexName).type(indexType).id(indexId).build();
        JestResult result = jestClient.execute(index);
        return result.isSucceeded();
    }

    /**
     * 删除一条数据
     *
     * @param indexId   文章id
     * @param indexName 索引名称
     * @param indexType 索引类型
     * @return 删除结果
     * @throws Exception
     */
    public Boolean deleteArticle(String indexId, String indexName, String indexType) throws Exception {
        Delete.Builder builder = new Delete.Builder(indexId);
        Delete delete = builder.index(indexName).type(indexType).build();
        JestResult result = jestClient.execute(delete);
        return result.isSucceeded();
    }

    /**
     * 分页查询
     *
     * @param param 查询参数
     * @param size  每页大小
     * @param from  当前页码
     * @return
     * @throws Exception
     */
    public JestResult searchBypage(String param, int size, int from) throws Exception {
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchAllQuery());

        Search.Builder builder = new Search.Builder(searchSourceBuilder.toString());
        Search search = builder.setParameter("size", size)
                .setParameter("from", from).addIndex("note").addType("article").build();
        JestResult result = jestClient.execute(search);
        return result;
    }
}
