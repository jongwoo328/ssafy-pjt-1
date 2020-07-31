package com.web.curation.model;

public class Profile {
	private int pno;
	private int userno;
	private String comment;
	private String imgurl;
	
	
	public Profile() {
		
	}
	
	public Profile(int pno, int userno, String comment ,String imgurl) {
		this.pno = pno;
		this.userno = userno;
		this.comment = comment;
		this.imgurl = imgurl;
	}
	public int getPno() {
		return pno;
	}
	public void setPno(int pno) {
		this.pno = pno;
	}
	public int getUserno() {
		return userno;
	}
	public void setUserno(int userno) {
		this.userno = userno;
	}
	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getImgurl() {
		return imgurl;
	}
	public void setImgurl(String imgurl) {
		this.imgurl = imgurl;
	}

	@Override
	public String toString() {
		return "Profile [pno=" + pno + ", userno=" + userno + ", comment=" + comment + ", imgurl=" + imgurl + "]";
	}

	
	
	
}