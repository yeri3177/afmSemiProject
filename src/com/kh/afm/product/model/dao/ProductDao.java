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

import com.kh.afm.admin.model.exception.AdminException;
import com.kh.afm.product.model.exception.ProductException;
import com.kh.afm.product.model.vo.Attachment;
import com.kh.afm.product.model.vo.Product;
import com.kh.afm.product.model.vo.ProductComment;

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
				product.setpCnt(rset.getInt("p_cnt"));
				product.setpCategory(rset.getString("p_category"));
				
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
			throw new ProductException("전체상품 조회 오류",e);
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
			throw new ProductException("전체 컨텐츠 조회 오류",e);
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
				product.setpNo(rset.getInt("p_no"));
				product.setUserId(rset.getString("p_user_id"));
				product.setpTitle(rset.getString("p_title"));
				product.setpRegDate(rset.getDate("p_reg_date"));
				product.setpContent(rset.getString("p_content"));					
				if(rset.getString("p_content") != null) {
					product.setpContent(rset.getString("p_content"));
				}
				else {
					product.setpContent("없음");
				}
				//product.setpContent(rset.getString("p_content") == null ? "1" : rset.getString("p_content"));					
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
			throw new ProductException("상품 한개 조회 오류", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return product;

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
			throw new ProductException("대표이미지 오류", e);
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
			throw new ProductException("상세 이미지 오류", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return attach;
		

	}


	public int updateProduct(Connection conn, Product product) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("updateProduct");
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, product.getpTitle());
			pstmt.setInt(2, product.getpPrice());
			pstmt.setInt(3, product.getpCnt());
			pstmt.setString(4, product.getpCategory());
			pstmt.setString(5, product.getpPost());
			pstmt.setString(6, product.getpContent());
			pstmt.setInt(7, product.getpNo());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ProductException("게시글 등록 오류", e);
		} finally {
			close(pstmt);
		}
		
		return result;

	}

	public int updateAttachmentY(Connection conn,  Product product, Attachment attach1) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("updateAttachmentY");
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, attach1.getOriginalFileName());
			pstmt.setString(2, attach1.getRenamedFileName());
			pstmt.setInt(3, product.getpNo());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ProductException("첨부파일 등록 오류", e);
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int updateAttachmentN(Connection conn, Product product, Attachment attach2) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("updateAttachmentN");
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, attach2.getOriginalFileName());
			pstmt.setString(2, attach2.getRenamedFileName());
			pstmt.setInt(3, product.getpNo());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ProductException("첨부파일 등록 오류", e);
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int deleteProduct(Connection conn, int no) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = prop.getProperty("deleteProduct");
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1, no);
		
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ProductException("상품 삭제 오류", e);
		} finally {
			close(pstmt);
		}
		
		
		return result;
	}

	public int deleteAttachmentY(Connection conn, int no) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = prop.getProperty("deleteAttachmentY");
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1, no);
		
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ProductException("대표이미지 삭제 오류", e);
		} finally {
			close(pstmt);
		}
		
		
		return result;
	}

	public int deleteAttachmentN(Connection conn, int no) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = prop.getProperty("deleteAttachmentN");
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1, no);
		
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ProductException("상세이미지 삭제 오류", e);
		} finally {
			close(pstmt);
		}
		
		
		return result;
	}

	public int insertProductComment(Connection conn, ProductComment pc) {
		int result = 0;
		PreparedStatement pstmt = null;
		System.out.println(111);
		String query = prop.getProperty("insertProductComment");
		
		try {
			//미완성쿼리문을 가지고 객체생성.
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, pc.getpNo());
			pstmt.setString(2, pc.getUserId());
			pstmt.setInt(3, pc.getCommentLevel());
			pstmt.setString(4, pc.getCommentContent());
			pstmt.setObject(5, pc.getCommentRef() == 0 ? null : pc.getCommentRef());
			
			//쿼리문실행 : 완성된 쿼리를 가지고 있는 pstmt실행(파라미터 없음)
			//DML은 executeUpdate()
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ProductException("댓글 등록 오류!", e);
		} finally {
			close(pstmt);
		}
		return result;
	}

	public List<ProductComment> selectCommentList(Connection conn, int no) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectCommentList");
		List<ProductComment> commentList = new ArrayList<>();
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			
			rset = pstmt.executeQuery();
			while(rset.next()) {
				ProductComment pc = new ProductComment();
				pc.setCommentNo(rset.getInt("comment_no"));
				pc.setpNo(rset.getInt("p_no"));
				pc.setUserId(rset.getString("user_id"));
				pc.setCommentLevel(rset.getInt("comment_level"));
				pc.setCommentContent(rset.getString("comment_content"));
				pc.setCommentRef(rset.getInt("comment_ref"));
				pc.setRegDate(rset.getDate("reg_date"));
				
				commentList.add(pc);
		
			}
		} catch (SQLException e) {
			throw new ProductException("댓글 조회 오류!", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		return commentList;
	}

	public int deleteBoardComment(Connection conn, int commentNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("deleteBoardComment");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, commentNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new ProductException("댓글 삭제 오류", e);
		} finally {
			close(pstmt);
		}
		
		return result;
		
	}

	public int productLike(Connection conn, int pNo, int like) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("productLike");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, like);
			pstmt.setInt(2, pNo);
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			throw new ProductException("좋아요 업데이트 오류", e);
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int productLikeSelect(Connection conn, int pNo) {
		Attachment attach = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("productLikeSelect");
		int likeCount = 0;
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, pNo);
			
			rset = pstmt.executeQuery();
			
			
			if(rset.next()) {
				likeCount = rset.getInt(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ProductException("상품 좋아요 오류", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return likeCount;
	}

	public String selectProductCategory(Connection conn, int start, int end, String pCategory) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String csvStr = null;
		StringBuilder csv = new StringBuilder();
		String sql = prop.getProperty("selectProductCategory");
		List<Product> list = new ArrayList<>();
		
		try {
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, pCategory);
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Product product = new Product();
				
				product.setpNo(rset.getInt("p_no"));
				product.setpTitle(rset.getString("p_title"));
				product.setpPrice(rset.getInt("p_price"));
				product.setUserId(rset.getString("p_user_id"));
				product.setpRegDate(rset.getDate("p_reg_date"));
				product.setpRecommend(rset.getInt("p_recommend"));
				product.setpCnt(rset.getInt("p_cnt"));
				product.setpCategory(rset.getString("p_category"));
				
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
			for(int i = 0; i < list.size(); i++) {
				csv.append(list.get(i));
				if(i != list.size() -1)
					csv.append("\n");
			}
			
			csvStr = csv.toString();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ProductException("카테고리별 상품 조회 오류", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return csvStr;
	}

	public String selectProductAllCategory(Connection conn, int start, int end, String pCategory) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String csvStr = null;
		StringBuilder csv = new StringBuilder();
		String sql = prop.getProperty("selectProductAllCategory");
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
				product.setpCnt(rset.getInt("p_cnt"));
				product.setpCategory(rset.getString("p_category"));
				
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
			for(int i = 0; i < list.size(); i++) {
				csv.append(list.get(i));
				if(i != list.size() -1)
					csv.append("\n");
			}
			
			csvStr = csv.toString();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ProductException("모두보기 조회 오류", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return csvStr;
	}

	public List<Product> selectProductSearchList(Connection conn, int start, int end, String searchKeyword) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectProductSearchList");
		List<Product> list = new ArrayList<>();
		
		System.out.println("searchKeyword = " + searchKeyword);
		
		try {
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, searchKeyword);
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Product product = new Product();
				
				product.setpNo(rset.getInt("p_no"));
				product.setpTitle(rset.getString("p_title"));
				product.setpPrice(rset.getInt("p_price"));
				product.setUserId(rset.getString("p_user_id"));
				product.setpRegDate(rset.getDate("p_reg_date"));
				product.setpRecommend(rset.getInt("p_recommend"));
				product.setpCnt(rset.getInt("p_cnt"));
				product.setpCategory(rset.getString("p_category"));
				
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
			throw new ProductException("카테고리별 상품 조회 오류", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}

	/**
	 * 상품카테고리 검색시 총 상품수 
	 */
	public int searchProductCount(Connection conn, String searchKeyword) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int totalContents = 0;
		String sql = prop.getProperty("searchProductCountByCategory");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, searchKeyword);
			rset = pstmt.executeQuery();
			if (rset.next())
				totalContents = rset.getInt(1);
		} catch (SQLException e) {
			throw new AdminException("검색결과 페이징 오류", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		return totalContents;
	}

	
}
