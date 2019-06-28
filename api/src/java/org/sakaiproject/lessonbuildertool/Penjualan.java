package org.sakaiproject.lessonbuildertool;

import java.util.Date;

public interface Penjualan {

	public int getKode_produk();
	public void setKode_produk(int kode_produk);
	public String getNama_produk();
	public void setNama_produk(String nama_produk);
	public int getHarga();
	public void setHarga(int harga);
	public int getStok();
	public void setStok(int stok);
	public String getDeskripsi();
	public void setDeskripsi(String deskripsi);
	public String getUser_id();
	public void setUser_id(String user_id);
}