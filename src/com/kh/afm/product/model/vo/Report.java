package com.kh.afm.product.model.vo;

import java.io.Serializable;
import java.sql.Date;
/**
 * 상품신고내역 
 */
public class Report implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int reportNo; // 신고번호
	private String reportTitle; // 신고제목
	private String reportType; // 신고유형
	private String reportContent; // 신고내용
	private String userId; // 신고한 회원 아이디
	private Date reportRegDate; // 신고일
	private String reportStatus; // 신고처리상태
	private int pNo; // 신고된 상품번호 
	
	public Report() {
		super();
	}

	public Report(int reportNo, String reportTitle, String reportType, String reportContent, String userId,
			Date reportRegDate, String reportStatus, int pNo) {
		super();
		this.reportNo = reportNo;
		this.reportTitle = reportTitle;
		this.reportType = reportType;
		this.reportContent = reportContent;
		this.userId = userId;
		this.reportRegDate = reportRegDate;
		this.reportStatus = reportStatus;
		this.pNo = pNo;
	}

	public int getReportNo() {
		return reportNo;
	}

	public void setReportNo(int reportNo) {
		this.reportNo = reportNo;
	}

	public String getReportTitle() {
		return reportTitle;
	}

	public void setReportTitle(String reportTitle) {
		this.reportTitle = reportTitle;
	}

	public String getReportType() {
		return reportType;
	}

	public void setReportType(String reportType) {
		this.reportType = reportType;
	}

	public String getReportContent() {
		return reportContent;
	}

	public void setReportContent(String reportContent) {
		this.reportContent = reportContent;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Date getReportRegDate() {
		return reportRegDate;
	}

	public void setReportRegDate(Date reportRegDate) {
		this.reportRegDate = reportRegDate;
	}

	public String getReportStatus() {
		return reportStatus;
	}

	public void setReportStatus(String reportStatus) {
		this.reportStatus = reportStatus;
	}

	public int getpNo() {
		return pNo;
	}

	public void setpNo(int pNo) {
		this.pNo = pNo;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Report [reportNo=" + reportNo + ", reportTitle=" + reportTitle + ", reportType=" + reportType
				+ ", reportContent=" + reportContent + ", userId=" + userId + ", reportRegDate=" + reportRegDate
				+ ", reportStatus=" + reportStatus + ", pNo=" + pNo + "]";
	}
}
