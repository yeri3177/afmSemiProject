package com.kh.afm.user.model.vo;

public class Account {
	private int accountNo; //계좌일련번호
	private String accountNumber; //계좌번호
	private String bankName; //은행명
	private String userId; //회원아이디
	
	public Account() {
		super();
	}

	public Account(int accountNo, String accountNumber, String bankName, String userId) {
		super();
		this.accountNo = accountNo;
		this.accountNumber = accountNumber;
		this.bankName = bankName;
		this.userId = userId;
	}

	public int getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(int accountNo) {
		this.accountNo = accountNo;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "Account [accountNo=" + accountNo + ", accountNumber=" + accountNumber + ", bankName=" + bankName
				+ ", userId=" + userId + "]";
	}
}
