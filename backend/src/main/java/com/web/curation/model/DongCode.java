package com.web.curation.model;

public class DongCode {
	private String ccode;
	private String code;
	private String city;
	private String gugun;
	private String dongcode;
	private String dong;
	private String lat;
	private String lng;
	
	public DongCode() {
		
	}

	public DongCode(String ccode, String code, String city, String gugun, String dongcode, String dong, String lat, String lng) {
		this.ccode = ccode;
		this.code = code;
		this.city = city;
		this.gugun = gugun;
		this.dongcode = dongcode;
		this.dong = dong;
		this.lat = lat;
		this.lng = lng;
	}

	
	
	public String getCcode() {
		return ccode;
	}

	public void setCcode(String ccode) {
		this.ccode = ccode;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getGugun() {
		return gugun;
	}

	public void setGugun(String gugun) {
		this.gugun = gugun;
	}

	public String getDongcode() {
		return dongcode;
	}

	public void setDongcode(String dongcode) {
		this.dongcode = dongcode;
	}

	public String getDong() {
		return dong;
	}

	public void setDong(String dong) {
		this.dong = dong;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getLng() {
		return lng;
	}

	public void setLng(String lng) {
		this.lng = lng;
	}

	@Override
	public String toString() {
		return "DongCode [code=" + code + ", city=" + city + ", gugun=" + gugun + ", dongcode=" + dongcode + ", dong="
				+ dong + ", lat=" + lat + ", lng=" + lng + "]";
	}
	
	
	
	
}
