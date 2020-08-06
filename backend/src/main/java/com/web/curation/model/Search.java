package com.web.curation.model;

public class Search {
	private int cateno;
	private String keyword;
	private String saddr5;
	public int getCateno() {
		return cateno;
	}
	public void setCateno(int cateno) {
		this.cateno = cateno;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public String getSaddr5() {
		return saddr5;
	}
	public void setSaddr5(String saddr5) {
		this.saddr5 = saddr5;
	}
	@Override
	public String toString() {
		return "Search [cateno=" + cateno + ", keyword=" + keyword + ", saddr5=" + saddr5 + "]";
	}
	
	
	
	
	
}
