package org.sakaiproject.lessonbuildertool;

import java.util.Date;

public interface SimplePagePenjualan {

	public int getId();
	public void setId(int id);

	public int getKode_produk();
	public void setKode_produk(int kode_produk);

	public int getTotal();
	public void setTotal(int total);
	
	public int getQty();
	public void setQty(int qty);

	public String getUser_id();
	public void setUser_id(String user_id);
}