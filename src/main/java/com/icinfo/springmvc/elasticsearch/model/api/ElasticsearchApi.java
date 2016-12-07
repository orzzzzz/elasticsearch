package com.icinfo.springmvc.elasticsearch.model.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.icinfo.springmvc.client.ClientManage;
import com.icinfo.springmvc.elasticsearch.model.News;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;

import java.math.BigDecimal;
import java.util.Date;


/**
 * 描述：api
 */
public class ElasticsearchApi {
    /**
     * 索引一个文档
     *
     * @param json  保存的json数据
     * @param index 索引
     * @param type  类型
     * @param id    行号
     * @throws Exception
     */
    public static IndexResponse creatIndex(String json, String index, String type, String id) throws Exception {
        Client client = ClientManage.getNodeClient();
        for (int i = 0; i < 10; i++) {
            id = String.valueOf(i + 1);
            News news = new News();
            news.setId(i);
            news.setTitle("第" + i + "条新闻标题");
            news.setContent("第" + i + "条新闻内容");
            news.setAuthor("aaa" + i);
            news.setAge(18);
            news.setMoney(new BigDecimal(150.23));
            news.setCreator("abc");
            news.setCreateTime(new Date());
            ObjectMapper mapper = new ObjectMapper();
            json = mapper.writeValueAsString(news);
            IndexResponse response = client.prepareIndex(index, type, id)
                    .setSource(json)
                    .execute()
                    .actionGet();
        }
        return null;
    }

    /**
     * 搜索文档
     *
     * @param index 索引
     * @param type  类型
     * @param id    行号
     * @return
     * @throws Exception
     */
    public static GetResponse getDocs(String index, String type, String id) throws Exception {
        GetResponse response = ClientManage.getNodeClient().prepareGet()
                .setIndex(index)
                .setType(type)
                .setId(id)
                .setOperationThreaded(false)
                .execute()
                .get();
        return response;
    }

    /**
     * 根据查询条件全局搜索
     *
     * @param index 索引
     * @param type  类型
     * @return
     * @throws Exception
     */
    public static SearchResponse getDocsByQuery(String index, String type) throws Exception {
        SearchResponse response = ClientManage.getNodeClient().prepareSearch("note", "articles").setTypes("article", "article")
                .setQuery("22").setSize(20).setFrom(1).execute().get();
        return response;
    }

    /**
     * 分页查询
     *
     * @param param
     * @param size
     * @param from
     * @return
     * @throws Exception
     */
    public static SearchResponse searchBypage(String param, int size, int from) throws Exception {
        SearchResponse searchResponse = ClientManage.getTransportClient().prepareSearch("note").setIndices("note").setTypes("article")
                .setSize(size).setFrom(from).setQuery(param)
                .execute().actionGet();
        return searchResponse;
    }
}
