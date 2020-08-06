package com.web.curation.model;

import java.sql.Date;

public class Msg {
	private int msgno, writerno, reciverno;
	private String title, content;
	private String senddate;
	private String writername;
	private String recivername;
	private boolean readcheck;
	private boolean sendcheck;
	private boolean recivcheck;
	
	
	public boolean isSendcheck() {
		return sendcheck;
	}

	public void setSendcheck(boolean sendcheck) {
		this.sendcheck = sendcheck;
	}

	public boolean isRecivcheck() {
		return recivcheck;
	}

	public void setRecivcheck(boolean recivcheck) {
		this.recivcheck = recivcheck;
	}

	public Msg() {
		
	}
	
	public Msg(int msgno, int writerno, int reciverno, String title, String content, String senddate) {
		this.msgno = msgno;
		this.writerno = writerno;
		this.reciverno = reciverno;
		this.title = title;
		this.content = content;
		this.senddate = senddate;
	}
	public int getMsgno() {
		return msgno;
	}
	public void setMsgno(int msgno) {
		this.msgno = msgno;
	}
	public int getWriterno() {
		return writerno;
	}
	public void setWriterno(int writerno) {
		this.writerno = writerno;
	}
	public int getReciverno() {
		return reciverno;
	}
	public void setReciverno(int reciverno) {
		this.reciverno = reciverno;
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
	public boolean isReadcheck() {
		return readcheck;
	}

	public void setReadcheck(boolean readcheck) {
		this.readcheck = readcheck;
	}

	public String getSenddate() {
		return senddate;
	}
	public void setSenddate(String senddate) {
		this.senddate = senddate;
	}
	public String getWritername() {
		return writername;
	}
	public void setWritername(String writername) {
		this.writername = writername;
	}
	public String getRecivername() {
		return recivername;
	}
	public void setRecivername(String recivername) {
		this.recivername = recivername;
	}

	@Override
	public String toString() {
		return "Msg [msgno=" + msgno + ", writerno=" + writerno + ", reciverno=" + reciverno + ", title=" + title
				+ ", content=" + content + ", senddate=" + senddate + ", writername=" + writername + ", recivername="
				+ recivername + ", readcheck=" + readcheck + ", sendcheck=" + sendcheck + ", recivcheck=" + recivcheck
				+ "]";
	}


	
}
