package com.web.curation.model;

public class ConnectorService {
	private int servno;
	private int userno;
	private int cateno;
	private String servname;
	private int price;
	private String description;
	private String seraddr;
	
	public ConnectorService() {
		
	}
	
	public ConnectorService(int userno, int cateno, String servname, int price, String description, String seraddr) {
		this.userno = userno;
		this.cateno = cateno;
		this.servname = servname;
		this.price = price;
		this.description = description;
		this.seraddr = seraddr;
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

	public String getSeraddr() {
		return seraddr;
	}

	public void setSeraddr(String seraddr) {
		this.seraddr = seraddr;
	}

	@Override
	public String toString() {
		return "Service [servno=" + servno + ", userno=" + userno + ", cateno=" + cateno + ", servname=" + servname
				+ ", price=" + price + ", description=" + description + ", seraddr=" + seraddr + "]";
	}
	
	
	
	
}
