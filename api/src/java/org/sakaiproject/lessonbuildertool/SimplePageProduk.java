package org.sakaiproject.lessonbuildertool;

public interface SimplePageProduk{
	public int getKodeProduk();
	public String getUserId();
	public String getNamaProduk();
	public int getHarga();
	public int getStok();
	public int getKodeJenisProduk();
	public String getDeskripsi();

	public void setKodeProduk(int kodeProduk);
	public void setUserId(String userId);
	public void setNamaProduk(String namaProduk);
	public void setHarga(int harga);
	public void setStok(int stok);
	public void setKodeJenisProduk(int kodeJenisProduk);
	public void setDeskripsi(String deskripsi);
}