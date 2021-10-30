package com.kh.afm.order.model.vo;

import java.io.Serializable;

public class OrderAddress implements Serializable {
	private static final long serialVersionUID = 1L;
	private String userId;
	private int addressNo;
	private String addressName;
	private String addressRoad;
	private String addressDetail;
	public OrderAddress() {
		super();
		// TODO Auto-generated constructor stub
	}
	public OrderAddress(String userId, int addressNo, String addressName, String addressRoad, String addressDetail) {
		super();
		this.userId = userId;
		this.addressNo = addressNo;
		this.addressName = addressName;
		this.addressRoad = addressRoad;
		this.addressDetail = addressDetail;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public int getAddressNo() {
		return addressNo;
	}
	public void setAddressNo(int addressNo) {
		this.addressNo = addressNo;
	}
	public String getAddressName() {
		return addressName;
	}
	public void setAddressName(String addressName) {
		this.addressName = addressName;
	}
	public String getAddressRoad() {
		return addressRoad;
	}
	public void setAddressRoad(String addressRoad) {
		this.addressRoad = addressRoad;
	}
	public String getAddressDetail() {
		return addressDetail;
	}
	public void setAddressDetail(String addressDetail) {
		this.addressDetail = addressDetail;
	}
	@Override
	public String toString() {
		return "OrderAddress [userId=" + userId + ", addressNo=" + addressNo + ", addressName=" + addressName
				+ ", addressRoad=" + addressRoad + ", addressDetail=" + addressDetail + "]";
	}
	
}
