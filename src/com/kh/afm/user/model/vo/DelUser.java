package com.kh.afm.user.model.vo;

import java.io.Serializable;
import java.sql.Date;

public class DelUser implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String deleteUId; //회원아이디
	private String deleteUName; //이름
	private String deleteUEmail; //이메일
	private String deletePassword; //비밀번호
	private String deleteBirthday; //생일
	private String deletePhone; //전화번호
	private Date deleteUEnroll_date; //가입일
	private String userRole; //회원권한
	private String userExpose; //회원공개여부
	private Date deleteUDate; // 탈퇴일 
	
	public DelUser() {
		super();
	}
	public DelUser(String deleteUId, String deleteUName, String deleteUEmail, String deletePassword,
			String deleteBirthday, String deletePhone, Date deleteUEnroll_date, String userRole, String userExpose,
			Date deleteUDate) {
		super();
		this.deleteUId = deleteUId;
		this.deleteUName = deleteUName;
		this.deleteUEmail = deleteUEmail;
		this.deletePassword = deletePassword;
		this.deleteBirthday = deleteBirthday;
		this.deletePhone = deletePhone;
		this.deleteUEnroll_date = deleteUEnroll_date;
		this.userRole = userRole;
		this.userExpose = userExpose;
		this.deleteUDate = deleteUDate;
	}
	public String getDeleteUId() {
		return deleteUId;
	}
	public void setDeleteUId(String deleteUId) {
		this.deleteUId = deleteUId;
	}
	public String getDeleteUName() {
		return deleteUName;
	}
	public void setDeleteUName(String deleteUName) {
		this.deleteUName = deleteUName;
	}
	public String getDeleteUEmail() {
		return deleteUEmail;
	}
	public void setDeleteUEmail(String deleteUEmail) {
		this.deleteUEmail = deleteUEmail;
	}
	public String getDeletePassword() {
		return deletePassword;
	}
	public void setDeletePassword(String deletePassword) {
		this.deletePassword = deletePassword;
	}
	public String getDeleteBirthday() {
		return deleteBirthday;
	}
	public void setDeleteBirthday(String deleteBirthday) {
		this.deleteBirthday = deleteBirthday;
	}
	public String getDeletePhone() {
		return deletePhone;
	}
	public void setDeletePhone(String deletePhone) {
		this.deletePhone = deletePhone;
	}
	public Date getDeleteUEnroll_date() {
		return deleteUEnroll_date;
	}
	public void setDeleteUEnroll_date(Date deleteUEnroll_date) {
		this.deleteUEnroll_date = deleteUEnroll_date;
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
	public Date getDeleteUDate() {
		return deleteUDate;
	}
	public void setDeleteUDate(Date deleteUDate) {
		this.deleteUDate = deleteUDate;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	@Override
	public String toString() {
		return "DelUser [deleteUId=" + deleteUId + ", deleteUName=" + deleteUName + ", deleteUEmail=" + deleteUEmail
				+ ", deletePassword=" + deletePassword + ", deleteBirthday=" + deleteBirthday + ", deletePhone="
				+ deletePhone + ", deleteUEnroll_date=" + deleteUEnroll_date + ", userRole=" + userRole
				+ ", userExpose=" + userExpose + ", deleteUDate=" + deleteUDate + "]";
	}
}
