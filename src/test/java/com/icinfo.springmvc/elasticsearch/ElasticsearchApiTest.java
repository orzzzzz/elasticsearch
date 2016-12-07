package com.icinfo.springmvc.elasticsearch;

import com.icinfo.springmvc.elasticsearch.model.api.ElasticsearchApi;
import io.searchbox.client.JestResult;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.highlight.HighlightField;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Map;

/**
 * 描述：api测试
 */
public class ElasticsearchApiTest{

    /**
     * 索引一个文档测试
     *
     * @throws Exception
     */
    @Test
    @Ignore
    public void testCreatIndex() throws Exception {
        String index = "twitter";
        String type = "tweet";
        String id = "";
        String json = "";
        ElasticsearchApi.creatIndex(json, index, type, id);

    }

    /**
     * 搜索文档测试
     *
     * @throws Exception
     */
    @Test
    @Ignore
    public void testGetDocs() throws Exception {
        String index = "twitter";
        String type = "tweet";
        String id = "2";
        GetResponse docs = ElasticsearchApi.getDocs(index, type, id);
        //ObjectMapper mapper = new ObjectMapper();
        //News news = mapper.readValue(docs.sourceAsString(), News.class);
        System.out.println(docs.sourceAsString());
    }

    /**
     * 根据查询条件全局搜索测试
     *
     * @throws Exception
     */
    @Test
    @Ignore
    public void testGetDocsByQuery() throws Exception {
        String index = "note";
        String type = "article";
        SearchResponse docsByQuery = ElasticsearchApi.getDocsByQuery(index, type);
        System.out.println(docsByQuery.toString());
        for (SearchHit hit : docsByQuery.getHits()) {
            Map<String, HighlightField> result = hit.highlightFields();
            System.out.println(hit.toString());
        }
    }
}
