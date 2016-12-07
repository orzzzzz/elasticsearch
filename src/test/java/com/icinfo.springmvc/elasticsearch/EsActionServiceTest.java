package com.icinfo.springmvc.elasticsearch;

import com.icinfo.springmvc.common.service.impl.EsActionServiceImpl;
import com.icinfo.springmvc.elasticsearch.model.News;
import com.icinfo.springmvc.elasticsearch.service.impl.JestClientServiceImpl;
import io.searchbox.client.JestResult;
import org.junit.Ignore;
import org.junit.Test;

import java.util.List;

/**
 * 描述：es操作接口实现测试
 */
public class EsActionServiceTest {
    private JestClientServiceImpl searchService = new JestClientServiceImpl();
    /**
     * 创建news索引
     */
    @Test
    @Ignore
    public void buildSearchIndex() throws Exception{
        searchService.createIndex("articles","article");
    }

    @Test
    @Ignore
    public void testDeleteArticle() throws Exception {
        Boolean aBoolean = searchService.deleteArticle("707", "article", "article");
        System.out.println(aBoolean==true?"删除成功":"删除失败");
    }

    @Test
    @Ignore
    public void testSearchBypage() throws Exception {
        JestResult result = searchService.searchBypage("", 20, 1);
        System.out.println(result.isSucceeded());
    }
}
