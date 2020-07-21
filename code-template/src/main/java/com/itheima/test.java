package com.itheima;

import com.itheima.code.RoleResDto;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.util.ArrayList;
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
        ArrayList<RoleResDto> objects = new ArrayList<>();
        boolean flag = false;
        RoleResDto roleResDto = new RoleResDto();
        roleResDto.setRoleId("100");
        objects.add(roleResDto);
        for (int i = 0; i < 10; i++) {
            RoleResDto roleResDto1 = new RoleResDto();
            roleResDto1.setRoleId("100"+i);
            objects.add(roleResDto1);
        }
        String retStr = "18570550596";
        List<String> collect = objects.stream().map(RoleResDto::getRoleId).collect(Collectors.toList());
        for (String s : collect) {
            System.out.println(s);
        }
        flag = objects.stream().map(RoleResDto::getRoleId).collect(Collectors.toList()).contains("100");
        retStr = retStr.replace(retStr.substring(3, 7), "****");
        long l1 = System.currentTimeMillis();
        System.out.println( ((l1-l))+"ms" +flag);
    }
}
