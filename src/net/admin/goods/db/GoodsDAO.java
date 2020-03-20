package net.admin.goods.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class GoodsDAO {
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	String sql = "";
	
	
	public GoodsDAO() {
		try {
			getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 디비 연결(커넥션 풀 사용)
	private Connection getConnection() throws Exception {
		// Context 객체를 생성
		Context init = new InitialContext();

		DataSource ds = (DataSource) init.lookup("java:comp/env/jdbc/model2DB");

		con = ds.getConnection();
		
		System.out.println(" 디비 연결 완료 : "+con);
		return con;
	}

	// 자원 해제

	public void closeDB() {
		try {
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
			if (con != null)
				con.close();
			
			System.out.println(" 자원해제 완료 ");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void insertGoods(GoodsDTO gdto) {
		int num = 0;

		try {
			// sql
			sql = "select max(num) from model2_goods";
			
			pstmt = con.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				
				num = rs.getInt("max(num)")+1;
			}
			
			System.out.println("글번호 num : "+num);
			
			sql = "insert into model2_goods "
					+ "values(?,?,?,?,?,?,?,?,?,?,now())";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.setString(2, gdto.getCategory());
			pstmt.setString(3, gdto.getName());
			pstmt.setString(4, gdto.getCotent());
			pstmt.setString(5, gdto.getSize());
			pstmt.setString(6, gdto.getColor());
			pstmt.setInt(7, gdto.getAmount());
			pstmt.setInt(8, gdto.getPrice());
			pstmt.setString(9, gdto.getImage());
			pstmt.setInt(10, gdto.getBest());
			
			pstmt.executeUpdate();
			
			
			System.out.println("상품 DB 추가 완료 !");
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			closeDB();
		}
		
		// ?추가
		
		// 실행
	}
	

	
	

}
