package com.icinfo.springmvc.common.util;

/**
 * 描述：字符串工具类
 *
 * @author zhangmengwen
 * @date 2016/10/25
 */
public class StringUtils extends org.apache.commons.lang3.StringUtils {

    /**
     * 拼接字符串
     *
     * @param param
     * @return
     */
    public static String assemblyString(String... param) {
        StringBuilder stringBuilder = new StringBuilder();
        String[] params = param;
        for (int i = 0, len = params.length; i < len; i++) {
            stringBuilder.append(params[i]);
        }
        return stringBuilder.toString();
    }
}
