package com.kh.afm.product.model.service;

import static com.kh.afm.common.JdbcTemplate.*;

import java.sql.Connection;
import java.util.List;

import com.kh.afm.product.model.dao.ProductDao;
import com.kh.afm.product.model.vo.Attachment;
import com.kh.afm.product.model.vo.Product;
import com.kh.afm.product.model.vo.ProductComment;

public class ProductService {

	private static ProductDao productDao = new ProductDao();
	
	public List<Product> selectProductList(int start, int end) {
		
		Connection conn = getConnection();
		List<Product> list = productDao.selectProductList(conn, start, end);
		close(conn);
		return list;
	}

	public int selectTotalContent(int start, int end) {
		Connection conn = getConnection();
		int totalContents = productDao.selectTotalContents(conn);
		close(conn);
		return totalContents;
	}

	public static int insertProduct(Product product) {
		Connection conn = getConnection();
		int result = 0;
		
		try {
			//product테이블 행 추가
			result = productDao.insertProduct(conn, product);
			
			//생성된 product_no 가져오기
			int pNo = productDao.selectLastProductNo(conn);
			System.out.println("pNo = " + pNo);
			
			//product객체에 set -> servlet에서 참조
			product.setpNo(pNo);
			
			//attachment테이블 행 추가
			Attachment attach1 = product.getAttach1();
			if(attach1 != null) {
				attach1.setpNo(pNo);
				result = productDao.insertAttachment(conn, attach1);
			}
			
			Attachment attach2 = product.getAttach2();
			if(attach2 != null) {
				attach2.setpNo(pNo);
				result = productDao.insertAttachment(conn, attach2);
			}
			commit(conn);
		} catch(Exception e) {
			rollback(conn);
			result = 0;
		}
		close(conn);
		return result;
	}

	public Product selectOneProduct(int no) {
		Connection conn = getConnection();
//		List<Attachment> list = productDao.selectAttachment(conn, no);
		//대표이미지 attach1에 담기
		Attachment attach1 = productDao.selectAttachmentY(conn, no);
		System.out.println(attach1);
		//상세이미지 attach2에 담기
		Attachment attach2 = productDao.selectAttachmentN(conn, no);
		System.out.println(attach2);
		Product product = productDao.selectOneProduct(conn, no, attach1, attach2);
		
		close(conn);
		
		return product;
		
	}

	public static int updateProduct(Product product) {
		Connection conn = getConnection();
		int result = 0;
		
		try {
			// 1. 첨부파일1,2 업데이트 하기

			//product업데이트
			result = productDao.updateProduct(conn, product);
			
			//pNo 가져오기
//			int pNo = product.getpNo();
			
			//product객체에 set -> servlet에서 참조
//			product.setpNo(pNo);
			
			//attachment테이블 행 추가
			Attachment attach1 = product.getAttach1();
			if(attach1 != null) {
				result = productDao.updateAttachmentY(conn, product ,attach1);
			}
			
			Attachment attach2 = product.getAttach2();
			if(attach2 != null) {
				result = productDao.updateAttachmentN(conn, product, attach2);
			}
			commit(conn);
			
		} catch (Exception e) {
			rollback(conn);
			throw e;
		} finally {
			close(conn);
		}
		
		return result;
	}

	public int deleteProduct(int no) {
		Connection conn = getConnection();
		int result = 0;
		
		try {
			result = productDao.deleteProduct(conn, no);
			if(result == 0)
				throw new IllegalArgumentException("해당 게시글이 존재하지 않습니다. : " + no );
			commit(conn);
		} catch (Exception e) {
			e.printStackTrace();
			rollback(conn);
		} finally {
			close(conn);
		}
		
		return result;
	}

	public int deleteAttachmentY(int no) {
		Connection conn = getConnection();
		int result = 0;
		
		try {
			result = productDao.deleteAttachmentY(conn, no);
			if(result == 0)
				throw new IllegalArgumentException("해당 첨부파일이 존재하지 않습니다. : " + no );
			commit(conn);
		} catch (Exception e) {
			e.printStackTrace();
			rollback(conn);
		} finally {
			close(conn);
		}
		
		return result;
	}
	
	public int deleteAttachmentN(int no) {
		Connection conn = getConnection();
		int result = 0;
		
		try {
			result = productDao.deleteAttachmentN(conn, no);
			if(result == 0)
				throw new IllegalArgumentException("해당 첨부파일이 존재하지 않습니다. : " + no );
			commit(conn);
		} catch (Exception e) {
			e.printStackTrace();
			rollback(conn);
		} finally {
			close(conn);
		}
		
		return result;

	}

	public int insertProductComment(ProductComment pc) {
		Connection conn = getConnection();
		int result = 0;
		try {
			result = productDao.insertProductComment(conn, pc);
			commit(conn);
		} catch (Exception e) {
			rollback(conn);
			throw e;
		} finally {
			close(conn);
		}
		return result;
	}

	public List<ProductComment> selectCommentList(int no) {
		Connection conn = getConnection();
		List<ProductComment> commentList = productDao.selectCommentList(conn, no);
		close(conn);
		return commentList;
	}

	public int deleteProductComment(int commentNo) {
		Connection conn = getConnection(); 
		int result = 0;
		try {
			result = productDao.deleteBoardComment(conn, commentNo);
			commit(conn);
		} catch(Exception e) {
			rollback(conn);
			throw e;
		}
		return result;
	}
	
	public static int productLike(int pNo, int like) {
		Connection conn = getConnection();
		int result = 0;
		int likeCount = 0;
		try {
			result = productDao.productLike(conn, pNo, like);
			if(result == 0)
				throw new IllegalArgumentException("좋아요 실패 : " + pNo );
			
			likeCount = productDao.productLikeSelect(conn, pNo);
			
			commit(conn);
		} catch(Exception e) {
			rollback(conn);
			throw e;
		}
		return likeCount;
	}


	public String selectProductCategory(int start, int end, String pCategory) {
		Connection conn = getConnection();
		String csvStr = productDao.selectProductCategory(conn, start, end, pCategory);
		close(conn);
		return csvStr;
	}

	public String selectProductAllCategory(int start, int end, String pCategory) {
		Connection conn = getConnection();
		String csvStr = productDao.selectProductAllCategory(conn, start, end, pCategory);
		close(conn);
		return csvStr;
	}

}
