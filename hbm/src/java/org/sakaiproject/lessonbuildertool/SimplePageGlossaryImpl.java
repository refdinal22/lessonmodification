package org.sakaiproject.lessonbuildertool;

public class SimplePageGlossaryImpl implements SimplePageGlossary{
	private int id;	
	private String term;
	private String description;
	private String category;

	public int getid(){
		return id;
	}
	public String getTerm(){
		return term;
	}
	public String getDescription(){
		return description;
	}
	public String getCategory(){
		return category;
	}

	public void setid(int id){
		this.id = id;
	}
	public void setTerm(String term){
		this.term = term;
	}
	public void setDescription(String desc){
		this.description = desc;
	}
	public void setCategory(String category){
		this.category = category;
	}

}