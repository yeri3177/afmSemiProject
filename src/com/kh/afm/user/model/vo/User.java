package com.kh.afm.user.model.vo;

import java.io.Serializable;
import java.sql.Date;

public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	private String userId; //회원아이디
	private String userName; //이름
	private String userEmail; //이메일
	private String password; //비밀번호
	private Date birthday; //생일
	private String phone; //전화번호
	private Date userEnrollDate; //가입일
	private String userRole; //회원권한
	private String userExpose; //회원공개여부
	private Account account; //계좌
	private Address address; //주소
	
	public User() {
		super();
	}
	
	public User(String userId, String userName, String userEmail, String password, Date birthday, String phone,
			Date userEnrollDate, String userRole, String userExpose) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.userEmail = userEmail;
		this.password = password;
		this.birthday = birthday;
		this.phone = phone;
		this.userEnrollDate = userEnrollDate;
		this.userRole = userRole;
		this.userExpose = userExpose;
	}

	public User(String userId, String userName, String userEmail, String password, Date birthday, String phone,
			Date userEnrollDate, String userRole, String userExpose, Account account, Address address) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.userEmail = userEmail;
		this.password = password;
		this.birthday = birthday;
		this.phone = phone;
		this.userEnrollDate = userEnrollDate;
		this.userRole = userRole;
		this.userExpose = userExpose;
		this.account = account;
		this.address = address;
	}

	public String getUserId() {
		return userId;
	}
	
	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Date getUserEnrollDate() {
		return userEnrollDate;
	}

	public void setUserEnrollDate(Date userEnrollDate) {
		this.userEnrollDate = userEnrollDate;
	}

	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

	public String getUserExpose() {
		return userExpose;
	}

	public void setUserExpose(String userExpose) {
		this.userExpose = userExpose;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName + ", userEmail=" + userEmail + ", password="
				+ password + ", birthday=" + birthday + ", phone=" + phone + ", userEnrollDate=" + userEnrollDate
				+ ", userRole=" + userRole + ", userExpose=" + userExpose + ", account=" + account + ", address="
				+ address + "]";
	}
}
