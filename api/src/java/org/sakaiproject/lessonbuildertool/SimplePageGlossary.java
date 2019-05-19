package org.sakaiproject.lessonbuildertool;

public interface SimplePageGlossary {
	
	public int getid();
	public String getTerm();
	public String getDescription();
	public String getCategory();

	public void setid(int id);
	public void setTerm(String term);
	public void setDescription(String desc);
	public void setCategory(String category);

}