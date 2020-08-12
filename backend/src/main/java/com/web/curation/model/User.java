// 하단 DB 설정 부분은 Sub PJT II에서 데이터베이스를 구성한 이후에 주석을 해제하여 사용.

package com.web.curation.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.DynamicInsert;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDateTime;

public class User {
	// @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userno;
	private String email;
	private String pw;
	private String name;
	private String tel;
	private String addr1;
	private String addr2;
	private String addr3;
	private String addr4;
	private String addr5;
	private String addr6;
	private boolean ispro;
	private int msgcount;
	private int folcount;
	private boolean checkadmin;
	private boolean checkfollow;
	
	
	public boolean isCheckfollow() {
		return checkfollow;
	}
	public void setCheckfollow(boolean checkfollow) {
		this.checkfollow = checkfollow;
	}
	public int getFolcount() {
		return folcount;
	}
	public void setFolcount(int folcount) {
		this.folcount = folcount;
	}
	public boolean isCheckadmin() {
		return checkadmin;
	}
	public void setCheckadmin(boolean checkadmin) {
		this.checkadmin = checkadmin;
	}
	public int getMsgcount() {
		return msgcount;
	}
	public void setMsgcount(int msgcount) {
		this.msgcount = msgcount;
	}
	public int getUserno() {
		return userno;
	}
	public void setUserno(int userno) {
		this.userno = userno;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getAddr1() {
		return addr1;
	}
	public void setAddr1(String addr1) {
		this.addr1 = addr1;
	}
	public String getAddr2() {
		return addr2;
	}
	public void setAddr2(String addr2) {
		this.addr2 = addr2;
	}
	public String getAddr3() {
		return addr3;
	}
	public void setAddr3(String addr3) {
		this.addr3 = addr3;
	}
	public String getAddr4() {
		return addr4;
	}
	public void setAddr4(String addr4) {
		this.addr4 = addr4;
	}
	public String getAddr5() {
		return addr5;
	}
	public void setAddr5(String addr5) {
		this.addr5 = addr5;
	}
	public String getAddr6() {
		return addr6;
	}
	public void setAddr6(String addr6) {
		this.addr6 = addr6;
	}
	public boolean isIspro() {
		return ispro;
	}
	public void setIspro(boolean ispro) {
		this.ispro = ispro;
	}
	
	@Override
	public String toString() {
		return "User [userno=" + userno + ", email=" + email + ", pw=" + pw + ", name=" + name + ", tel=" + tel
				+ ", addr1=" + addr1 + ", addr2=" + addr2 + ", addr3=" + addr3 + ", addr4=" + addr4 + ", addr5=" + addr5
				+ ", addr6=" + addr6 + ", ispro=" + ispro + ", msgcount=" + msgcount + "]";
	}
	
	
	
}
