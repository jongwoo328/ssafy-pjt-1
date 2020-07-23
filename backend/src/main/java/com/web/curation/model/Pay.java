package com.web.curation.model;

public class Pay {
	private int payno;
	private int servno;
	private int userno;
	
	public Pay() {
		
	}
	
	public Pay(int payno, int servno, int userno) {
		this.payno = payno;
		this.servno = servno;
		this.userno = userno;
	}

	public int getPayno() {
		return payno;
	}

	public void setPayno(int payno) {
		this.payno = payno;
	}

	public int getServno() {
		return servno;
	}

	public void setServno(int servno) {
		this.servno = servno;
	}

	public int getUserno() {
		return userno;
	}

	public void setUserno(int userno) {
		this.userno = userno;
	}
	
	
	
	
}
