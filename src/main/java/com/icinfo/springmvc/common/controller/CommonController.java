package com.icinfo.springmvc.common.controller;

import com.icinfo.springmvc.common.util.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 描述：公共控制器
 *
 * @author zhangmengwen
 * @date 2016/10/25
 */
@Controller
public class CommonController {
    /**
     * 跳转页面
     *
     * @param path 文件夹
     * @param file 文件名
     * @return 路径
     */
    @RequestMapping(value = "/page/{path}/{file}", method = RequestMethod.GET)
    public String toPage(@PathVariable("path") String path,
                         @PathVariable("file") String file) {
        return StringUtils.assemblyString("page", "/", path, "/", file);
    }
}
