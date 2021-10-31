package com.kh.afm.user.model.vo;


public class UserOrderDetail {
	private int orderDetailNo; //주문상세번호
	private int pNo; //상품번호
	private int orderNo; //주문번호
	private int pCount; //상품개수
	private int pPrice; //상품가격
	private String orderStatus; //주문상태
	
	public UserOrderDetail() {
		super();
	}

	public UserOrderDetail(int orderDetailNo, int pNo, int orderNo, int pCount, int pPrice, String orderStatus) {
		super();
		this.orderDetailNo = orderDetailNo;
		this.pNo = pNo;
		this.orderNo = orderNo;
		this.pCount = pCount;
		this.pPrice = pPrice;
		this.orderStatus = orderStatus;
	}

	public int getOrderDetailNo() {
		return orderDetailNo;
	}

	public void setOrderDetailNo(int orderDetailNo) {
		this.orderDetailNo = orderDetailNo;
	}

	public int getpNo() {
		return pNo;
	}

	public void setpNo(int pNo) {
		this.pNo = pNo;
	}

	public int getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}

	public int getpCount() {
		return pCount;
	}

	public void setpCount(int pCount) {
		this.pCount = pCount;
	}

	public int getpPrice() {
		return pPrice;
	}

	public void setpPrice(int pPrice) {
		this.pPrice = pPrice;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	@Override
	public String toString() {
		return "OrderDetail [orderDetailNo=" + orderDetailNo + ", pNo=" + pNo + ", orderNo=" + orderNo + ", pCount="
				+ pCount + ", pPrice=" + pPrice + ", orderStatus=" + orderStatus + "]";
	}
	
}
