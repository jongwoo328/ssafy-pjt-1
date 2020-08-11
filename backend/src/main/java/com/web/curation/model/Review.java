package com.web.curation.model;

public class Review {
	private int revno;
	private int userno;
	private String writer;
	private int servno;
	private String title;
	private String content;
	private int point;
	private String cdate;
	private String imgurl;
	
	public Review(int userno, int servno) {
		this.servno =servno;
		this.userno = userno;
	}
	
	public String getImgurl() {
		return imgurl;
	}

	public void setImgurl(String imgurl) {
		this.imgurl = imgurl;
	}

	public Review() {
		
	}

	public int getRevno() {
		return revno;
	}

	public void setRevno(int revno) {
		this.revno = revno;
	}

	public int getUserno() {
		return userno;
	}

	public void setUserno(int userno) {
		this.userno = userno;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public int getServno() {
		return servno;
	}

	public void setServno(int servno) {
		this.servno = servno;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	public String getCdate() {
		return cdate;
	}

	public void setCdate(String cdate) {
		this.cdate = cdate;
	}

	public Review(int revno, int userno, String writer, int servno, String title, String content, int point,
			String cdate) {
		this.revno = revno;
		this.userno = userno;
		this.writer = writer;
		this.servno = servno;
		this.title = title;
		this.content = content;
		this.point = point;
		this.cdate = cdate;
	}

	@Override
	public String toString() {
		return "Review [revno=" + revno + ", userno=" + userno + ", writer=" + writer + ", servno=" + servno + ", title="
				+ title + ", content=" + content + ", point=" + point + ", cdate=" + cdate + "]";
	}
	
	
	
}
