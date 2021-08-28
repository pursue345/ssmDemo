package com.ssm._2_xml;


import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/*
 * 解析XML文件--遍历XML文件
 */
public class Demo1 {

    public static void main(String[] args) throws Exception {
        Demo1 d = new Demo1();
        String resource = "C:\\idea_workspace\\ssmDemo\\src\\test\\com\\com\\ssm\\_2_xml\\students.xml";

//        d.query(resource);

        List<Student> students = d.parseXml(resource);
        for (Student s : students) {
            System.out.println(s);
        }

    }

    public void query(String resource) throws DocumentException {
        // 1.创建解析器对象
        SAXReader reader = new SAXReader();
        // 2.创建一个Document XML文档对象
        File f = new File(resource);
        Document doc = reader.read(f);
        // 3.获取根元素
        Element rootElement = doc.getRootElement(); // 根元素
        // 4.获取根元素下子元素集合
        List<Element> elements = rootElement.elements(); // 获取根元素的子元素集合
        // 5.迭代集合
        for (Element element : elements) {
            Attribute attribute = element.attribute("id"); // 获取id属性d对象
            String attributeName = attribute.getName(); // 属性名
            String attributeText = attribute.getText(); // 属性值
            System.out.println(attributeName + ":" + attributeText);
            List<Element> childs = element.elements(); // 子元素的下一级子元素集合
            for (Element c : childs) {
                String name = c.getName();// 标签名
                String text = c.getText(); // 标签值
                System.out.println(name + ":" + text);
            }
            System.out.println("-----------------------------");
        }
    }

    public List<Student> parseXml(String resource) throws Exception {
        List<Student> list = new ArrayList<Student>();

        int id = 0;
        String name = "";
        int age = 0;
        double weight = 0.0;
        double score = 0.0;

        // 1.创建解析器对象
        SAXReader reader = new SAXReader();
        // 2.创建一个Document XML文档对象
        File f = new File(resource);
        Document doc = reader.read(f);
        // 3.获取根元素
        Element rootElement = doc.getRootElement(); // 根元素
        // 4.获取根元素下子元素集合
        List<Element> elements = rootElement.elements(); // 获取根元素的子元素集合
        // 5.迭代集合
        for (Element element : elements) {
            Attribute attribute = element.attribute("id"); // 获取id属性d对象
            id = Integer.parseInt(attribute.getText()); // 属性值
            List<Element> childs = element.elements(); // 子元素的下一级子元素集合
            for (Element c : childs) {
                String nodeName = c.getName();// 标签名
                if ("name".equals(nodeName)) {
                    name = c.getText(); // 标签值
                } else if ("age".equals(nodeName)) {
                    age = Integer.parseInt(c.getText()); // 标签值
                } else if ("weight".equals(nodeName)) {
                    weight = Double.parseDouble(c.getText()); // 标签值
                } else if ("score".equals(nodeName)) {
                    score = Double.parseDouble(c.getText()); // 标签值
                }
            }
            Student s = new Student(id, name, age, weight, score);
            list.add(s);
        }
        return list;
    }
}


