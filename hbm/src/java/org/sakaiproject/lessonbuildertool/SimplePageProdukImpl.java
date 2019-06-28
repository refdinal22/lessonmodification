package org.sakaiproject.lessonbuildertool;

public class SimplePageProdukImpl implements SimplePageProduk{
	private int kodeProduk;
	private String userId;
	private String namaProduk;
	private int harga;
	private int stok;
	private int kodeJenisProduk;
	private String deskripsi;

	public SimplePageProdukImpl(){}

	public int getKodeProduk(){
		return kodeProduk;
	}

	public String getUserId(){
		return userId;
	}

	public String getNamaProduk(){
		return namaProduk;
	}

	public int getHarga(){
		return harga;
	}

	public int getStok(){
		return stok;
	}

	public int getKodeJenisProduk(){
		return kodeJenisProduk;
	}

	public String getDeskripsi(){
		return deskripsi;
	}

	public void setKodeProduk(int kodeProduk){
		this.kodeProduk = kodeProduk;
	}

	public void setUserId(String userId){
		this.userId = userId;
	}

	public void setNamaProduk(String namaProduk){
		this.namaProduk = namaProduk;
	}

	public void setHarga(int harga){
		this.harga = harga;
	}

	public void setStok(int stok){
		this.stok = stok;
	}

	public void setKodeJenisProduk(int kodeJenisProduk){
		this.kodeJenisProduk = kodeJenisProduk;
	}

	public void setDeskripsi(String deskripsi){
		this.deskripsi = deskripsi;
	}
}