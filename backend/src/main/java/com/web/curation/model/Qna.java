package com.web.curation.model;

public class Qna {
	private int qnano;
	private int userno;
	private String qtitle;
	private String qcontent;
	private String acontent;
	private String awriter;
	private String qdate;
	private String adate;
	private String qwriter;
	
	public Qna() {
		
	}

	public Qna(int qnano, int userno, String qtitle, String qcontent, String acontent, String awriter, String qdate, String adate,
			String qwriter) {
		this.qnano = qnano;
		this.userno = userno;
		this.qtitle = qtitle;
		this.qcontent = qcontent;
		this.acontent = acontent;
		this.awriter = awriter;
		this.qdate = qdate;
		this.adate = adate;
		this.qwriter = qwriter;
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
	public String getAcontent() {
		return acontent;
	}
	
	public void setAcontent(String acontent) {
		this.acontent = acontent;
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

	public String getQwriter() {
		return qwriter;
	}

	public void setQwriter(String qwriter) {
		this.qwriter = qwriter;
	}

	@Override
	public String toString() {
		return "Qna [qnano=" + qnano + ", userno=" + userno + ", qtitle=" + qtitle + ", qcontent=" + qcontent
				+ ", acontent=" + acontent + ", awriter=" + awriter + ", qdate=" + qdate + ", adate=" + adate
				+ ", qwriter=" + qwriter + "]";
	}

	
}
