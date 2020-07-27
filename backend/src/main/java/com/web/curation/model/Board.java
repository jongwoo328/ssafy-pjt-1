package com.web.curation.model;

import java.sql.Date;

public class Board {
	private int boardno, userno;
	private String title, content;
	private Date bdate;
	public Board() {
		super();
	}
	public Board(int boardno, int userno, String title, String content, Date bdate) {
		super();
		this.boardno = boardno;
		this.userno = userno;
		this.title = title;
		this.content = content;
		this.bdate = bdate;
	}
	public int getBoardno() {
		return boardno;
	}
	public void setBoardno(int boardno) {
		this.boardno = boardno;
	}
	public int getUserno() {
		return userno;
	}
	public void setUserno(int userno) {
		this.userno = userno;
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
	public Date getBdate() {
		return bdate;
	}
	public void setBdate(Date bdate) {
		this.bdate = bdate;
	}
	@Override
	public String toString() {
		return "Board [boardno=" + boardno + ", userno=" + userno + ", title=" + title + ", content=" + content
				+ ", bdate=" + bdate + "]";
	}
}
