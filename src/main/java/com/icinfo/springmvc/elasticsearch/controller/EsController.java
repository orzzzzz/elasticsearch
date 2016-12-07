package com.icinfo.springmvc.elasticsearch.controller;

import com.icinfo.springmvc.elasticsearch.model.api.ElasticsearchApi;
import io.searchbox.client.JestResult;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.EscapedErrors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 描述：es控制器
 */
@Controller
@RequestMapping("/es")
public class EsController {
    private static final Logger logger = LoggerFactory.getLogger(EscapedErrors.class);

    /**
     * 获取数据
     *
     * @param index
     * @param type
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/getdocs", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getDocs(@RequestParam(name = "index", required = true) String index,
                        @RequestParam(name = "type", required = true) String type,
                        @RequestParam(name = "id", required = false) String id) throws Exception{
        GetResponse docs = ElasticsearchApi.getDocs(index, type, id);
        return docs.getSourceAsString();
    }

    @RequestMapping(value = "page", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String page(@RequestParam(name = "q") String param,
                       @RequestParam(name = "size") int size,
                       @RequestParam(name = "from") int from) throws Exception {
        SearchResponse result = ElasticsearchApi.searchBypage(param, size, from);
        return result.getHits().toString();
    }
}
