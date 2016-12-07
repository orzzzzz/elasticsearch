package com.icinfo.springmvc.common.util;

import com.icinfo.framework.tools.utils.HttpClientUtil;
import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * Http请求工具封装.
 * <p>
 * Created by wangxiao on 2016/9/26.
 */
public class HttpUtils extends HttpClientUtil {
    /**
     * 获取 request 中所有参数
     *
     * @param request http请求
     * @return
     */
    public static Map<String, Object> getAllParam(HttpServletRequest request) {
        Map map = new HashMap();
        Enumeration paramNames = request.getParameterNames();
        while (paramNames.hasMoreElements()) {
            String paramName = (String) paramNames.nextElement();

            String[] paramValues = request.getParameterValues(paramName);

            if (paramValues.length == 1) {
                String paramValue = paramValues[0];
                if (paramValue.length() != 0) {
                    map.put(paramName, paramValue);
                }
            }
        }

        return map;
    }

    /**
     * 判断是否Ajax请求
     *
     * @param request 请求
     * @return ajax请求：true 否则 false
     */
    public static boolean isAjax(HttpServletRequest request) {
        // 判定是否为异步请求，非异步请求直接进入error页面
        return (request.getHeader("accept").indexOf("application/json") > -1
                || (request.getHeader("X-Requested-With") != null
                && request.getHeader("X-Requested-With").indexOf("XMLHttpRequest") > -1));
    }

    /**
     * 异步请求异常处理
     *
     * @param response http响应对象
     * @throws Exception
     */
    public static void writeError(HttpServletResponse response, String errorInfo) throws Exception {
        PrintWriter writer = null;
        try {
            // 设置响应字符集
            response.setContentType("text/html; charset=utf-8");
            // 设置HTTP响应状态码为500（服务器异常）
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            writer = response.getWriter();
            writer.write(errorInfo);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }
}
