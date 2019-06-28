package org.sakaiproject.lessonbuildertool;

public class SimplePageJenisProdukImpl implements SimplePageJenisProduk{
	private int kodeJenisProduk;
	private String jenisProduk;

	public SimplePageJenisProdukImpl(){}

	public int getKodeJenisProduk(){
		return kodeJenisProduk;
	}

	public String getJenisProduk(){
		return jenisProduk;
	}

	public void setKodeJenisProduk(int kodeJenisProduk){
		this.kodeJenisProduk = kodeJenisProduk;
	}

	public void setJenisProduk(String jenisProduk){
		this.jenisProduk = jenisProduk;
	}
}