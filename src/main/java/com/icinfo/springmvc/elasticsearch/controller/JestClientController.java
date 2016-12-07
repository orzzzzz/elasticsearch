package com.icinfo.springmvc.elasticsearch.controller;

import com.icinfo.springmvc.elasticsearch.model.Article;
import com.icinfo.springmvc.elasticsearch.service.IJestClientService;
import io.searchbox.client.JestResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.math.BigDecimal;

/**
 * 描述：使用第三方工具jestClient
 */
@Controller
@RequestMapping("/")
public class JestClientController {
    /**
     * service注入
     */
    @Autowired
    private IJestClientService jestClientService;

    /**
     * 创建索引
     *
     * @param indexName 索引名称
     * @param indexType 索引类型
     * @throws Exception
     */
    @RequestMapping(value = "/createindex", method = RequestMethod.GET)
    @ResponseBody
    public void createIndex(@RequestParam(name = "indexName") String indexName,
                            @RequestParam(name = "indexType") String indexType) throws Exception {
        jestClientService.createIndex(indexName, indexType);
    }

    /**
     * 在所有索引中查询
     *
     * @param query 查询条件
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "/search", produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String search(@RequestParam("q") String query) throws IOException {
        String articles = jestClientService.searchsNews(query);
        //ModelAndView mv = new ModelAndView();
        //mv.setViewName("search");
        //mv.addObject("articles", articles);
        return articles;
    }

    /**
     * 获得文章详情
     *
     * @param docId 文章id
     * @return 文章内容
     * @throws Exception
     */
    @RequestMapping(value = "getarticle", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getArticle(@RequestParam(name = "id") String docId) throws Exception {
        Article article = jestClientService.getArticle(docId);
        return article.toString();
    }

    /**
     * 修改
     *
     * @param indexName 索引名称
     * @param indexType 索引类型
     * @param docId     文章ID
     * @return 修改结果
     * @throws Exception
     */
    @RequestMapping(value = "/updatearticle", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String updateArtcle(@RequestParam(name = "index") String indexName,
                               @RequestParam(name = "type") String indexType,
                               @RequestParam(name = "docId") String docId) throws Exception {

        Article article = new Article();
        article.setId("888");
        article.setTitle("习近平致电祝贺特朗普当选美国总统");
        article.setContent("11月9日，国家主席习近平向美国当选总统唐纳德·特朗普致贺电。\n" +
                "　　习近平在贺电中指出，作为最大的发展中国家、最大的发达国家、世界前两大经济体，中美两国在维护世界和平稳定、促进全球发展繁荣方面肩负着特殊的重要责任，拥有广泛的共同利益。发展长期健康稳定的中美关系，符合两国人民根本利益，也是国际社会普遍期待。我高度重视中美关系，期待着同你一道努力，秉持不冲突不对抗、相互尊重、合作共赢的原则，拓展两国在双边、地区、全球层面各领域合作，以建设性方式管控分歧，推动中美关系在新的起点上取得更大进展，更好造福两国人民和各国人民。\n" +
                "　　国家副主席李源潮致电迈克·彭斯，祝贺他当选美国副总统。");
        article.setAuthor("zzz");
        article.setAge(18);
        article.setTime(new Long(888));
        //article.setCreateTime(new Date());
        article.setScore(new BigDecimal(88.878));
        Boolean aBoolean = jestClientService.updateArticle(article, docId, indexName, indexType);
        return aBoolean == true ? "修改成功" : "修改失败";
    }

    /**
     * 删除数据
     *
     * @param indexName 索引名称
     * @param indexType 索引类型
     * @param docId     文章ID
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/deletearticle", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String deleteArticle(@RequestParam(name = "index") String indexName,
                                @RequestParam(name = "type") String indexType,
                                @RequestParam(name = "docId") String docId) throws Exception {
        Boolean aBoolean = jestClientService.deleteArticle(docId, indexName, indexType);
        return aBoolean == true ? "删除成功" : "删除失败";
    }

    /**
     * 分页查询
     *
     * @param param 查询参数
     * @param size  每页大小
     * @param from  页数
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/page", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String page(@RequestParam(name = "q") String param,
                       @RequestParam(name = "size") int size,
                       @RequestParam(name = "from") int from) throws Exception {
        JestResult result = jestClientService.searchBypage(param, size, from);
        return result.getSourceAsString();
    }
}
