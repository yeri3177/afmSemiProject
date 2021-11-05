package com.kh.afm.common;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Base64.Encoder;

public class MvcUtils {

	/**
	 * 비밀번호 암호화
	 */
	public static String getEncryptedPassword(String password) {
		// 1. 암호화
		MessageDigest md = null;
		byte[] encrypted = null;
		try {
			md = MessageDigest.getInstance("SHA-512");
			byte[] input = password.getBytes("utf-8");
			md.update(input);
			encrypted = md.digest();
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		// 2.인코딩
		Encoder encoder = Base64.getEncoder();
		String encryptedPassword = encoder.encodeToString(encrypted);
		
		return encryptedPassword;
	}


	/**
	 * 페이지바
	 */
	public static String getPagebar(int cPage, int numPerPage, int totalContents, String url) {
		StringBuilder pagebar = new StringBuilder();
		
		// 전체페이지수 
		int totalPage = (int) Math.ceil((double) totalContents / numPerPage);
		
		// 페이지번호를 클릭했을때 링크
		String delimeter = url.contains("?") ? "&" : "?";
		url = url + delimeter + "cPage=";
		
		// 페이지바크기 
		int pagebarSize = 5;
		
		int pageStart = (cPage - 1) / pagebarSize * pagebarSize + 1;
		int pageEnd = pageStart + pagebarSize - 1;
		
		int pageNo = pageStart;
		
		// 1.이전
		if(pageNo == 1) {
			
		}
		else {
			pagebar.append("<a href='" + url + (pageNo - 1) + "'>prev</a>\n");
		}
		
		// 2.pageNo
		while(pageNo <= pageEnd && pageNo <= totalPage) {
			if(pageNo == cPage) {
				// 현재페이지인 경우 링크 제공안함.
				pagebar.append("<span class='cPage'>" + pageNo + "</span>\n");
			}
			else {
				// 현재페이지가 아닌 경우 링크를 제공.
				pagebar.append("<a href='" + url + pageNo + "'>" + pageNo + "</a>\n");
			}
			pageNo++;
		}
		
		// 3.다음
		if(pageNo > totalPage) {
			
		}
		else {
			pagebar.append("<a href='" + url + pageNo + "'>next</a>\n");
		}
		
		return pagebar.toString();
	}

	/**
	 * XSS공격대비 
	 */
	public static String escapeHtml(String s) {
		return s.replaceAll("<", "&lt;").replaceAll(">","&gt;");
	}

	/**
	 * \n 문자 br태그로 변환
	 */
	public static String convertLineFeedToBr(String s) {
		return s.replaceAll("\\n", "<br/>");
	}
}



