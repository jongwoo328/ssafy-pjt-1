package com.web.curation.model;

import java.sql.Date;

public class Comment {
	private int commno, boardno, userno;
	private String content;
	private Date cdate;
	public Comment() {
		super();
	}
	public Comment(int commno, int boardno, int userno, String content, Date cdate) {
		super();
		this.commno = commno;
		this.boardno = boardno;
		this.userno = userno;
		this.content = content;
		this.cdate = cdate;
	}
	public int getCommno() {
		return commno;
	}
	public void setCommno(int commno) {
		this.commno = commno;
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
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getCdate() {
		return cdate;
	}
	public void setCdate(Date cdate) {
		this.cdate = cdate;
	}
	@Override
	public String toString() {
		return "Comment [commno=" + commno + ", boardno=" + boardno + ", userno=" + userno + ", content=" + content
				+ ", cdate=" + cdate + "]";
	}
}
