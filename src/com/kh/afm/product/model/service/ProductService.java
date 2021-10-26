package com.kh.afm.product.model.service;

import static com.kh.afm.common.JdbcTemplate.*;

import java.sql.Connection;
import java.util.List;

import com.kh.afm.product.model.dao.ProductDao;
import com.kh.afm.product.model.vo.Attachment;
import com.kh.afm.product.model.vo.Product;

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

}
