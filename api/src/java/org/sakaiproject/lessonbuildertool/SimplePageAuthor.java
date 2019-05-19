/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sakaiproject.lessonbuildertool;

/**
 *
 * @author Faza
 */
import java.util.Date;
public interface SimplePageAuthor {
    
    	public long getId();
	public void setId(long id);
	
	public long getItemId();
	public void setItemId(long itemId);
	
	public long getPageId();
	public void setPageId(long pageId);
	
	public Date getTimePosted();
	public void setTimePosted(Date date);
	
	public String getAuthor();
	public void setAuthor(String author);
        
        public String getUUID();
	public void setUUID(String UUID);
        
        public int compareTo(Object o);
    
}
