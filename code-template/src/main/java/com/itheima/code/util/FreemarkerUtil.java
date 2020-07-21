package com.itheima.code.util;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

public class FreemarkerUtil {
	
	public static void main(String[] args) throws IOException, TemplateException {
		String StrTemplate = "姓名：${name}；年龄：${age}"; // 测试模板数据（一般存储在数据库中）
		Map<String, Object> map = new HashMap<String, Object>(); // map，需要动态填充的数据
		map.put("name", "张三");
		map.put("age", "25");
		String resultStr = format(StrTemplate, map); // 解析字符串模板的方法，并返回处理后的字符串
		System.out.println(resultStr);
	}

	/**
	 * 解析字符串模板,通用方法
	 * 
	 * @param template      字符串模板
	 * @param model;        数据
	 * @param configuration 配置
	 * @return 解析后内容
	 */
	public static String format(String template, Object model) throws IOException, TemplateException {
		if (template == null) {
			return null;
		}
		Configuration configuration = new Configuration(Configuration.VERSION_2_3_23);
		StringWriter out = new StringWriter();
		new Template("template", new StringReader(template), configuration).process(model, out);
		return out.toString();
	}
}
