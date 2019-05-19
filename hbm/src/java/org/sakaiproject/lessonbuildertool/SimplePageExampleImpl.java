package org.sakaiproject.lessonbuildertool;

import java.util.Date;

public class SimplePageExampleImpl implements SimplePageExample {
	
	private int id;
	private String name;
	private String text;

	public SimplePageExampleImpl() {}

	public int getid(){
		return id;
	}

	public String getName(){
		return name;
	}
	public String getText(){
		return text;
	}

	public void setid(int id){
		this.id = id;
	}

	public void setName(String name){
		this.name = name;

	}
	public void setText(String text){
		this.text = text;
	}

}