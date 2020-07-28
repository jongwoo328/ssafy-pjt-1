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
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userno;
    private String email;
    private String password;
    private String username;
    private String tel;
    private String address;
    private boolean ispro;

    
  
	public User() {}
	public User(String email, String password, String username, String tel, String address, boolean ispro) {
		super();
		this.email = email;
		this.password = password;
		this.username = username;
		this.tel = tel;
		this.address = address;
		this.ispro = ispro;
	}
	public User(int userno, String email, String password, String username, String tel, String address, boolean ispro) {
		super();
		this.userno = userno;
		this.email = email;
		this.password = password;
		this.username = username;
		this.tel = tel;
		this.address = address;
		this.ispro = ispro;
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public boolean isIspro() {
		return ispro;
	}
	public void setIspro(boolean ispro) {
		this.ispro = ispro;
	}
	@Override
	public String toString() {
		return "User [uno=" + userno + ", email=" + email + ", password=" + password + ", username=" + username + ", tel="
				+ tel + ", address=" + address + ", ispro=" + ispro + "]";
	}
}
