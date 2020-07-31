package com.web.curation.model;

public class Qna {
	private int qnano;
	private int userno;
	private String qtitle;
	private String qcontent;
	private String awriter;
	private String qdate;
	private String adate;
	
	public Qna() {
		
	}
	
	public Qna(int qnano, int userno, String qtitle, String qcontent, String awriter, String qdate, String adate) {
	
		this.qnano = qnano;
		this.userno = userno;
		this.qtitle = qtitle;
		this.qcontent = qcontent;
		this.awriter = awriter;
		this.qdate = qdate;
		this.adate = adate;
	}

	public int getQnano() {
		return qnano;
	}

	public void setQnano(int qnano) {
		this.qnano = qnano;
	}

	public int getUserno() {
		return userno;
	}

	public void setUserno(int userno) {
		this.userno = userno;
	}

	public String getQtitle() {
		return qtitle;
	}

	public void setQtitle(String qtitle) {
		this.qtitle = qtitle;
	}

	public String getQcontent() {
		return qcontent;
	}

	public void setQcontent(String qcontent) {
		this.qcontent = qcontent;
	}

	public String getAwriter() {
		return awriter;
	}

	public void setAwriter(String awriter) {
		this.awriter = awriter;
	}

	public String getQdate() {
		return qdate;
	}

	public void setQdate(String qdate) {
		this.qdate = qdate;
	}

	public String getAdate() {
		return adate;
	}

	public void setAdate(String adate) {
		this.adate = adate;
	}

	@Override
	public String toString() {
		return "Qna [qnano=" + qnano + ", userno=" + userno + ", qtitle=" + qtitle + ", qcontent=" + qcontent
				+ ", awriter=" + awriter + ", qdate=" + qdate + ", adate=" + adate + "]";
	}
	
	
	
	
	
	
}
