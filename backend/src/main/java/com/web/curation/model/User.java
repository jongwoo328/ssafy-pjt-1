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

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
@JsonInclude(JsonInclude.Include.NON_NULL)
public class User {
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private String userno;
    
    private String email;
    
    @JsonIgnore
    private String pw;
    
    private String name;
    private String tel;
    private String addr;
    private boolean ispro;
  
	public User() {}

	public User(String email, String pw, String name, String tel, String addr, boolean ispro) {
		this.email = email;
		this.pw = pw;
		this.name = name;
		this.tel = tel;
		this.addr = addr;
		this.ispro = ispro;
	}

	public String getUserno() {
		return userno;
	}

	public void setUserno(String userno) {
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

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public boolean isIspro() {
		return ispro;
	}

	public void setIspro(boolean ispro) {
		this.ispro = ispro;
	}



    
}
