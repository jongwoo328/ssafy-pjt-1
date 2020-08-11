package com.web.curation.model;

public class Pay {
	private int payno;
	private int servno;
	private int userno;
	private String pdate;
	private String servname;
	private String price;
	private boolean cancelcheck;
	private String imgurl;
	private double avgpoint;
	private int payCount;
	
	public int getPayCount() {
		return payCount;
	}

	public void setPayCount(int payCount) {
		this.payCount = payCount;
	}

	public String getImgurl() {
		return imgurl;
	}

	public void setImgurl(String imgurl) {
		this.imgurl = imgurl;
	}

	

	public double getAvgpoint() {
		return avgpoint;
	}

	public void setAvgpoint(double avgpoint) {
		this.avgpoint = avgpoint;
	}

	public boolean isCancelcheck() {
		return cancelcheck;
	}

	public void setCancelcheck(boolean cancelcheck) {
		this.cancelcheck = cancelcheck;
	}

	public String getServname() {
		return servname;
	}

	public void setServname(String servname) {
		this.servname = servname;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getPdate() {
		return pdate;
	}

	public void setPdate(String pdate) {
		this.pdate = pdate;
	}

	public Pay() {
		
	}
	
	public Pay(int servno, int userno) {
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

	@Override
	public String toString() {
		return "Pay [payno=" + payno + ", servno=" + servno + ", userno=" + userno + ", pdate=" + pdate + "]";
	}
	
	
	
	
}
