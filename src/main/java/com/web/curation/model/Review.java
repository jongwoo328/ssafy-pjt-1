package com.web.curation.model;

public class Review {
	private int revno;
	private int userno;
	private int servo;
	private String title;
	private String content;
	private int point;
	private String cdate;
	
	public Review() {
		
	}
	
	public Review(int revno, int userno, int servo, String title, String content, int point, String cdate) {
		this.revno = revno;
		this.userno = userno;
		this.servo = servo;
		this.title = title;
		this.content = content;
		this.point = point;
		this.cdate = cdate;
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

	public int getServo() {
		return servo;
	}

	public void setServo(int servo) {
		this.servo = servo;
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

	@Override
	public String toString() {
		return "Review [revno=" + revno + ", userno=" + userno + ", servo=" + servo + ", title=" + title + ", content="
				+ content + ", point=" + point + ", cdate=" + cdate + "]";
	}
	
	
}