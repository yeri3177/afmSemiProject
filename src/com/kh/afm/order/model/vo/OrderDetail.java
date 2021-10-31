package com.kh.afm.order.model.vo;

import java.io.Serializable;

public class OrderDetail implements Serializable {

	private static final long serialVersionUID = 1L;
	private int orderDetailNo;
	private int orderNo;
	private int productNo;
	private int pCnt;
	private int pPrice;
	private String payStatus;
	
	public OrderDetail() {
		super();
		// TODO Auto-generated constructor stub
	}
	public OrderDetail(int orderDetailNo, int orderNo, int productNo, int pCnt, int pPrice, String payStatus) {
		super();
		this.orderDetailNo = orderDetailNo;
		this.orderNo = orderNo;
		this.productNo = productNo;
		this.pCnt = pCnt;
		this.pPrice = pPrice;
		this.payStatus = payStatus;
	}
	public int getOrderDetailNo() {
		return orderDetailNo;
	}
	public void setOrderDetailNo(int orderDetailNo) {
		this.orderDetailNo = orderDetailNo;
	}
	public int getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}
	public int getProductNo() {
		return productNo;
	}
	public void setProductNo(int productNo) {
		this.productNo = productNo;
	}
	public int getpCnt() {
		return pCnt;
	}
	public void setpCnt(int pCnt) {
		this.pCnt = pCnt;
	}
	public int getpPrice() {
		return pPrice;
	}
	public void setpPrice(int pPrice) {
		this.pPrice = pPrice;
	}
	public String getPayStatus() {
		return payStatus;
	}
	public void setPayStatus(String payStatus) {
		this.payStatus = payStatus;
	}
	@Override
	public String toString() {
		return "OrderDetail [orderDetailNo=" + orderDetailNo + ", orderNo=" + orderNo + ", productNo=" + productNo
				+ ", pCnt=" + pCnt + ", pPrice=" + pPrice + ", payStatus=" + payStatus + "]";
	}
	
}
