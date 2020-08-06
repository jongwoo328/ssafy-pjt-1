package com.web.curation.model;

public class Category {
	
	private int cateno;
	private String cname;

	public Category() {
		
	}
	
	public Category(int cateno, String cname) {
		this.cateno = cateno;
		this.cname = cname;
	}

	public int getCateno() {
		return cateno;
	}

	public void setCateno(int cateno) {
		this.cateno = cateno;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	@Override
	public String toString() {
		return "Category [cateno=" + cateno + ", cname=" + cname + "]";
	}
	
	
	
	
}
