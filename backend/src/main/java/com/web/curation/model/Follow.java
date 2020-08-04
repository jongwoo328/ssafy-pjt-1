package com.web.curation.model;

public class Follow {
	private int folno;
	private int userno;
	private int prono;
	
	public Follow() {
		
	}
	
	public Follow(int folno, int userno, int prono) {
		this.folno = folno;
		this.userno = userno;
		this.prono = prono;
	}
	public int getFolno() {
		return folno;
	}
	public void setFolno(int folno) {
		this.folno = folno;
	}
	public int getUserno() {
		return userno;
	}
	public void setUserno(int userno) {
		this.userno = userno;
	}
	public int getProno() {
		return prono;
	}
	public void setProno(int prono) {
		this.prono = prono;
	}

	@Override
	public String toString() {
		return "Follow [folno=" + folno + ", userno=" + userno + ", prono=" + prono + "]";
	}
	
	
	
}
