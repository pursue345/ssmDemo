/**
 * 
 */
package com.ssm._5_ajaxAndJson;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.ssm._2_xml.Student;
import org.junit.Test;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @ClassName: Demo1
 * @Description:TODO
 */
public class Demo1 {

	// 解析
	@Test
	public void test1() throws Exception {
		// 数组
		String json2 = "[\"北京\",\"天津\",\"杭州\"]";
		ObjectMapper mapper = new ObjectMapper();

		ArrayList<String> list = mapper.readValue(json2, new TypeReference<ArrayList<String>>() {});
		System.out.println(list);

	}

	/*
	 * jackSon 对象--->JSON
	 */
	@Test
	public void test2() throws Exception {
		Classes c = new Classes();
		c.setId(10);
		c.setName("Java高级研发班");

		Student s1 = new Student(1, "小张", 18);
		Student s2 = new Student(2, "小张", 18);
		Student s3 = new Student(3, "小张", 18);
		ArrayList<Student> list = new ArrayList<Student>();
		list.add(s1);
		list.add(s2);
		list.add(s3);
		c.setStus(list);
		System.out.println(c);

		ObjectMapper mapper = new ObjectMapper();
		String jsonString = mapper.writeValueAsString(c);

		System.out.println("转换之后的内容：" + jsonString);
	}

	/*
	 * Gson 对象--->JSON
	 */
	@Test
	public void test3() throws Exception {
		Classes c = new Classes();
		c.setId(10);
		c.setName("Java高级研发班");

		Student s1 = new Student(1, "小张", 18);
		Student s2 = new Student(2, "小张", 18);
		Student s3 = new Student(3, "小张", 18);
		ArrayList<Student> list = new ArrayList<Student>();
		list.add(s1);
		list.add(s2);
		list.add(s3);
		c.setStus(list);
		System.out.println(c);

		Gson g=new Gson();
		String jsonString = g.toJson(c);

		System.out.println("转换之后的内容：" + jsonString);
	}

}
