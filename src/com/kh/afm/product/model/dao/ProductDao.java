package com.kh.afm.product.model.dao;

import static com.kh.afm.common.JdbcTemplate.close;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.kh.afm.product.model.exception.ProductException;
import com.kh.afm.product.model.vo.Attachment;
import com.kh.afm.product.model.vo.Product;

public class ProductDao {

	private Properties prop = new Properties();
	
	public ProductDao() {
		String filepath = ProductDao.class.getResource("/sql/product/product-query.properties").getPath();
		try {
			prop.load(new FileReader(filepath));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public List<Product> selectProductList(Connection conn, int start, int end) {
		
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectProductList");
		List<Product> list = new ArrayList<>();
		
		try {
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Product product = new Product();
				
				product.setpNo(rset.getInt("p_no"));
				product.setpTitle(rset.getString("p_title"));
				product.setpPrice(rset.getInt("p_price"));
				product.setUserId(rset.getString("p_user_id"));
				product.setpRegDate(rset.getDate("p_reg_date"));
				product.setpRecommend(rset.getInt("p_recommend"));
				
				if(rset.getString("img_flag").equals("Y")) {
					Attachment attach = new Attachment();
					attach.setAttachNo(rset.getInt("attach_no"));
					attach.setpNo(rset.getInt("p_no"));
					attach.setOriginalFileName(rset.getString("original_filename"));
					attach.setRenamedFileName(rset.getString("renamed_filename"));
					attach.setRegDate(rset.getDate("reg_date"));
					attach.setImgFlag(rset.getString("img_flag"));
					
					product.setAttach1(attach);
				
				list.add(product);
			}
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}

	public int selectTotalContents(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectTotalContents");
		int totalContents = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				totalContents = rset.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return totalContents;
	}

	public int insertProduct(Connection conn, Product product) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertProduct");
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, product.getUserId());
			pstmt.setString(2, product.getpTitle());
			pstmt.setString(3, product.getpContent());
			pstmt.setString(4, product.getpPost());
			pstmt.setInt(5, product.getpPrice());
			pstmt.setInt(6, product.getpCnt());
			pstmt.setString(7, product.getpCategory());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ProductException("게시글 등록 오류", e);
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int selectLastProductNo(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectLastProductNo");
		int pNo = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery(sql);
			if(rset.next()) {
				pNo = rset.getInt(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ProductException("게시글번호 조회 오류", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		return pNo;
	}

	public int insertAttachment(Connection conn, Attachment attach) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertAttachment");
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, attach.getpNo());
			pstmt.setString(2, attach.getOriginalFileName());
			pstmt.setString(3, attach.getRenamedFileName());
			pstmt.setString(4, attach.getImgFlag());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ProductException("첨부파일 등록 오류", e);
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public Product selectOneProduct(Connection conn, int no, Attachment attach1, Attachment attach2) {
		Product product = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = prop.getProperty("selectOneProduct");
		
		try {
			//미완성쿼리문을 가지고 객체 생성
			pstmt = conn.prepareStatement(query);
			//쿼리문미완성
			pstmt.setInt(1, no);
			//쿼리문 실행
			//완성된 쿼리를 가지고 있는 pstmt실행(파라미터 없음)
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				product = new Product();
				product.setUserId(rset.getString("p_user_id"));
				product.setpTitle(rset.getString("p_title"));
				product.setpRegDate(rset.getDate("p_reg_date"));
				product.setpContent(rset.getString("p_content"));
				product.setpPost(rset.getString("p_post"));
				product.setpPrice(rset.getInt("p_price"));
				product.setpCnt(rset.getInt("p_cnt"));
				product.setpCategory(rset.getString("p_category"));
				product.setpRecommend(rset.getInt("p_recommend"));
			}
			
			product.setAttach1(attach1);
			product.setAttach2(attach2);
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return product;

	}

	public Product selectOneProduct1(Connection conn, int no, Attachment attach1, Attachment attach2) {
		Product product = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = prop.getProperty("selectOneProduct");
		
		try {
			//미완성쿼리문을 가지고 객체 생성
			pstmt = conn.prepareStatement(query);
			//쿼리문미완성
			pstmt.setInt(1, no);
			//쿼리문 실행
			//완성된 쿼리를 가지고 있는 pstmt실행(파라미터 없음)
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				product = new Product();
				product.setUserId(rset.getString("p_user_id"));
				product.setpTitle(rset.getString("p_title"));
				product.setpRegDate(rset.getDate("p_reg_date"));
				product.setpContent(rset.getString("p_content"));
				product.setpPost(rset.getString("p_post"));
				product.setpPrice(rset.getInt("p_price"));
				product.setpCnt(rset.getInt("p_cnt"));
				product.setpCategory(rset.getString("p_category"));
				product.setpRecommend(rset.getInt("p_recommend"));
			}
			
			
//				
//			System.out.println("list@Service = " + list.get(0).toString());
//			System.out.println("list@Service = " + list.get(1).toString());
//
//			if(list.get(0) != null) {
//				
//			}
//			
//			for(int i = 0; i < list.size(); i++) {
//				
//				Attachment attach = new Attachment();
//				attach.setAttachNo(rset.getInt("attach_no"));
//				attach.setpNo(rset.getInt("p_no"));
//				attach.setOriginalFileName(rset.getString("original_filename"));
//				attach.setRenamedFileName(rset.getString("renamed_filename"));
//				attach.setRegDate(rset.getDate("reg_date"));
//				attach.setImgFlag(rset.getString("img_flag"));
//				
//				product.setAttach1(attach);
//
//			}
//			
//			list.size();
//			
//			if(rset.getString("img_flag").equals("Y")) {
//				
//				for(int i = 0; i < list.size(); i++) {
//					attach = list[i];
//					product.setAttach1(attach);
//					
////				Attachment attach = new Attachment();
////				attach.setAttachNo(rset.getInt("attach_no"));
////				attach.setpNo(rset.getInt("p_no"));
////				attach.setOriginalFileName(rset.getString("original_filename"));
////				attach.setRenamedFileName(rset.getString("renamed_filename"));
////				attach.setRegDate(rset.getDate("reg_date"));
////				attach.setImgFlag(rset.getString("img_flag"));
//				
//				product.setAttach1(attach);
//			}
//			
//			}
//			
			
			
			product.setAttach1(attach1);
			product.setAttach2(attach2);
			
			
			
			
			
//			if(rset.getString("img_flag").equals("Y")) {
//				Attachment attach = new Attachment();
//				attach.setAttachNo(rset.getInt("attach_no"));
//				attach.setpNo(rset.getInt("p_no"));
//				attach.setOriginalFileName(rset.getString("original_filename"));
//				attach.setRenamedFileName(rset.getString("renamed_filename"));
//				attach.setRegDate(rset.getDate("reg_date"));
//				attach.setImgFlag(rset.getString("img_flag"));
//				
//				product.setAttach1(attach);
//				
//			}
//			
//			if(rset.getString("img_flag").equals("N")) {
//				Attachment attach = new Attachment();
//				attach.setAttachNo(rset.getInt("attach_no"));
//				attach.setpNo(rset.getInt("p_no"));
//				attach.setOriginalFileName(rset.getString("original_filename"));
//				attach.setRenamedFileName(rset.getString("renamed_filename"));
//				attach.setRegDate(rset.getDate("reg_date"));
//				attach.setImgFlag(rset.getString("img_flag"));
//				
//				product.setAttach2(attach);
//				
//			}

			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return product;
	}

	public Attachment selectAttachment(Connection conn, int no) {
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectAttachmentList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			
			rset = pstmt.executeQuery();
			
			
			while(rset.next()) {
				Attachment attach = new Attachment();
				attach.setAttachNo(rset.getInt("attach_no"));
				attach.setpNo(rset.getInt("p_no"));
				attach.setOriginalFileName(rset.getString("original_filename"));
				attach.setRenamedFileName(rset.getString("renamed_filename"));
				attach.setRegDate(rset.getDate("reg_date"));
				attach.setImgFlag(rset.getString("img_flag"));
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return null;
	}
	
	public Attachment selectAttachmentY(Connection conn, int no) {
		Attachment attach = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectAttachmentY");
		
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, no);
			
			rset = pstmt.executeQuery();
			
			
			if(rset.next()) {
				attach = new Attachment();
				
				
				attach.setAttachNo(rset.getInt("attach_no"));
				attach.setpNo(rset.getInt("p_no"));
				attach.setOriginalFileName(rset.getString("original_filename"));
				attach.setRenamedFileName(rset.getString("renamed_filename"));
				attach.setRegDate(rset.getDate("reg_date"));
				attach.setImgFlag(rset.getString("img_flag"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return attach;
		

	}

	public Attachment selectAttachmentN(Connection conn, int no) {
		Attachment attach = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectAttachmentN");
		
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, no);
			
			rset = pstmt.executeQuery();
			
			
			if(rset.next()) {
				attach = new Attachment();
				
				
				attach.setAttachNo(rset.getInt("attach_no"));
				attach.setpNo(rset.getInt("p_no"));
				attach.setOriginalFileName(rset.getString("original_filename"));
				attach.setRenamedFileName(rset.getString("renamed_filename"));
				attach.setRegDate(rset.getDate("reg_date"));
				attach.setImgFlag(rset.getString("img_flag"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return attach;
		

	}


	
}
