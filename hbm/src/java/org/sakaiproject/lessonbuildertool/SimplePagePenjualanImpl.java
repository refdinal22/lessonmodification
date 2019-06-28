package org.sakaiproject.lessonbuildertool;

public class SimplePagePenjualanImpl implements SimplePagePenjualan{
	
	int id;
	int kode_produk;
	int total;
	int qty;
	String user_id;
	
	public SimplePagePenjualanImpl() {}
	
	public int getId(){
		return id;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getKode_produk() {
		return kode_produk;
	}
	public void setKode_produk(int kode_produk) {
		this.kode_produk = kode_produk;
	}
	
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}

	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}

	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
}
