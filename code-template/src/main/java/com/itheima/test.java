package com.itheima;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.itheima.code.RoleResDto;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @ClassName test
 * @Author lilei
 * @Date 08/07/2020 16:52
 * @Version 1.0
 **/
public class test {

    public static void main(String[] args) throws IOException, TemplateException {
        long l = System.currentTimeMillis();
        JSONObject jj = JSON.parseObject("{}");
        Date flag = jj.getDate("1");
        long l1 = System.currentTimeMillis();
        System.out.println( ((l1-l))+"ms" +flag);
    }
}
