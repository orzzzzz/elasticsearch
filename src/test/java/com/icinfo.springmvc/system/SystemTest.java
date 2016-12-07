package com.icinfo.springmvc.system;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述：TODO
 *
 * @author zhangmengwen
 * @date 2016/10/28
 */
public class SystemTest {
    public static void main(String[] args) throws Exception{
        List<String> list = new ArrayList<>();
        for(int i= 0 ; i<10;i++){
            list.add(i+"0");
        }
        ObjectMapper mapper = new ObjectMapper();
        System.out.println(mapper.writeValueAsString(list));
    }
}
