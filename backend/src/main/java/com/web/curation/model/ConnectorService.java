package com.web.curation.model;

public class ConnectorService {
	private int servno;
	private int userno;
	private int cateno;
	private String servname;
	private int price;
	private String description;
	private String saddr1;
	private String saddr2;
	private String saddr3;
	private String saddr4;
	private String saddr5;
	private String saddr6;
	private String imgurl;
	
	public ConnectorService() {
		
	}
	
	

	public ConnectorService(int servno, int userno, int cateno, String servname, int price, String description,
			String saddr1, String saddr2, String saddr3, String saddr4, String saddr5, String saddr6, String imgurl) {
		this.servno = servno;
		this.userno = userno;
		this.cateno = cateno;
		this.servname = servname;
		this.price = price;
		this.description = description;
		this.saddr1 = saddr1;
		this.saddr2 = saddr2;
		this.saddr3 = saddr3;
		this.saddr4 = saddr4;
		this.saddr5 = saddr5;
		this.saddr6 = saddr6;
		this.imgurl = imgurl;
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

	public int getCateno() {
		return cateno;
	}

	public void setCateno(int cateno) {
		this.cateno = cateno;
	}

	public String getServname() {
		return servname;
	}

	public void setServname(String servname) {
		this.servname = servname;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getSaddr1() {
		return saddr1;
	}

	public void setSaddr1(String saddr1) {
		this.saddr1 = saddr1;
	}

	public String getSaddr2() {
		return saddr2;
	}

	public void setSaddr2(String saddr2) {
		this.saddr2 = saddr2;
	}

	public String getSaddr3() {
		return saddr3;
	}

	public void setSaddr3(String saddr3) {
		this.saddr3 = saddr3;
	}

	public String getSaddr4() {
		return saddr4;
	}

	public void setSaddr4(String saddr4) {
		this.saddr4 = saddr4;
	}

	public String getSaddr5() {
		return saddr5;
	}

	public void setSaddr5(String saddr5) {
		this.saddr5 = saddr5;
	}

	public String getSaddr6() {
		return saddr6;
	}

	public void setSaddr6(String saddr6) {
		this.saddr6 = saddr6;
	}

	public String getImgurl() {
		return imgurl;
	}

	public void setImgurl(String imgurl) {
		this.imgurl = imgurl;
	}

	@Override
	public String toString() {
		return "ConnectorService [servno=" + servno + ", userno=" + userno + ", cateno=" + cateno + ", servname="
				+ servname + ", price=" + price + ", description=" + description + ", saddr1=" + saddr1 + ", saddr2="
				+ saddr2 + ", saddr3=" + saddr3 + ", saddr4=" + saddr4 + ", saddr5=" + saddr5 + ", saddr6=" + saddr6
				+ ", imgurl=" + imgurl + "]";
	}
	
	
	
	
	
}
