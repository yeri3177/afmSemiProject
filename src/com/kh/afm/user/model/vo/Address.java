package com.kh.afm.user.model.vo;

import java.io.Serializable;

public class Address implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private int adrNo; //배송지번호
	private String adrName; //배송지명
	private String adrRoad; //도로명주소
	private String adrDetail; //상세주소
	private String userId; //회원아이디
	
	public Address() {
		super();
	}
	public Address(int adrNo, String adrName, String adrRoad, String adrDetail, String userId) {
		super();
		this.adrNo = adrNo;
		this.adrName = adrName;
		this.adrRoad = adrRoad;
		this.adrDetail = adrDetail;
		this.userId = userId;
	}
	public int getAdrNo() {
		return adrNo;
	}
	public void setAdrNo(int adrNo) {
		this.adrNo = adrNo;
	}
	public String getAdrName() {
		return adrName;
	}
	public void setAdrName(String adrName) {
		this.adrName = adrName;
	}
	public String getAdrRoad() {
		return adrRoad;
	}
	public void setAdrRoad(String adrRoad) {
		this.adrRoad = adrRoad;
	}
	public String getAdrDetail() {
		return adrDetail;
	}
	public void setAdrDetail(String adrDetail) {
		this.adrDetail = adrDetail;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	@Override
	public String toString() {
		return "Address [adrNo=" + adrNo + ", adrName=" + adrName + ", adrRoad=" + adrRoad + ", adrDetail=" + adrDetail
				+ ", userId=" + userId + "]";
	}
}
