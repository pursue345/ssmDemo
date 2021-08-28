/**
 * 
 */
package com.ssm._5_ajaxAndJson;

import com.ssm._2_xml.Student;

import java.util.ArrayList;
import java.util.List;

public class Classes {

	private int id;
	private String name;

	private List<Student> stus=new ArrayList<Student>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Student> getStus() {
		return stus;
	}

	public void setStus(List<Student> stus) {
		this.stus = stus;
	}

	@Override
	public String toString() {
		return "Classes [id=" + id + ", name=" + name + ", stus=" + stus + "]";
	}

	
	
	

}
