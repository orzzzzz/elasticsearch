package com.icinfo.springmvc.elasticsearch.service;


import com.icinfo.springmvc.elasticsearch.model.Article;
import io.searchbox.client.JestResult;

import java.io.IOException;

/**
 * 描述：jestClient操作接口
 */
public interface IJestClientService {
    /**
     * 创建es 索引
     *
     * @param indexName 索引名称
     * @param indexType 索引类型
     */
    void createIndex(String indexName, String indexType) throws Exception;

    /**
     * 根据id查询具体内容
     *
     * @param indexId 文章id
     * @return 文章
     * @throws Exception
     */
    Article getArticle(String indexId) throws Exception;

    /**
     * 全文搜索新闻
     *
     * @param param 查询参数
     * @return 查询结果
     */
    String searchsNews(String param) throws IOException;

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
    Boolean updateArticle(Object indexObject, String indexId, String indexName, String indexType) throws Exception;

    /**
     * 删除一条数据
     *
     * @param indexId   文章id
     * @param indexName 索引名称
     * @param indexType 索引类型
     * @return 删除结果
     * @throws Exception
     */
    Boolean deleteArticle(String indexId, String indexName, String indexType) throws Exception;

    /**
     * 分页查询
     *
     * @param param 查询参数
     * @param size  每页大小
     * @param from  当前页码
     * @return
     * @throws Exception
     */
    JestResult searchBypage(String param, int size, int from) throws Exception;
}
