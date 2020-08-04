package com.web.curation.model;

public class Admin {
	private int ano;
	private int userno;
	
	
	public Admin() {
		
	}
	
	public Admin(int ano, int userno) {
		this.ano = ano;
		this.userno = userno;
	}
	public int getAno() {
		return ano;
	}
	public void setAno(int ano) {
		this.ano = ano;
	}
	public int getUserno() {
		return userno;
	}
	public void setUserno(int userno) {
		this.userno = userno;
	}

	@Override
	public String toString() {
		return "Admin [ano=" + ano + ", userno=" + userno + "]";
	}
	
	
	
}
