package net.basket.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import net.goods.db.GoodsDTO;

public class basketDAO {
	
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	String sql = "";
	
	
	public basketDAO() {
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
	
	
	//basketAdd
	public void basketAdd(basketDTO bkdto){
		
		int b_num = 0;
		
		try {
			
			con = getConnection();
			
			sql="select max(b_num) from model2_basket";
			
			pstmt = con.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				b_num = rs.getInt(1)+1;
			}
			
			System.out.println("장바구니 번호 : " + b_num);
			
			
			
			sql="insert into model2_basket "
					+ "values(?,?,?,?,?,?,now())";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, b_num);
			pstmt.setString(2, bkdto.getB_m_id());
			pstmt.setInt(3, bkdto.getB_g_num());
			pstmt.setInt(4, bkdto.getB_g_amount());
			pstmt.setString(5, bkdto.getB_g_size());
			pstmt.setString(6, bkdto.getB_g_color());
			
			pstmt.executeUpdate();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeDB();
		}
		
	}
	//basketAdd
	
	//checkGoods
	public int checkGoods(basketDTO bkdto){
		int check=0;
		
		try {
			sql="select * from model2_basket where b_m_id=? and b_g_num= ? "
					+ "and b_g_size= ? and b_g_size=?";
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, bkdto.getB_m_id());
			pstmt.setInt(2, bkdto.getB_g_num());
			pstmt.setString(3, bkdto.getB_g_size());
			pstmt.setString(4, bkdto.getB_g_color());
			
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				
				sql="update from model2_basket set b_g_amount = b_g_amount+? where b_m_id=? "
						+ "and b_g_num= ? and b_g_size= ? and b_g_size=?";
				
				pstmt = con.prepareStatement(sql);
				
				pstmt.setInt(1, bkdto.getB_g_amount());
				pstmt.setString(2, bkdto.getB_m_id());
				pstmt.setInt(3, bkdto.getB_g_num());
				pstmt.setString(4, bkdto.getB_g_size());
				pstmt.setString(5, bkdto.getB_g_color());
				
				check = pstmt.executeUpdate();
			}
			
			System.out.println("장바구니 체크 완료 (0:신규,1:기존) " +check);
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeDB();
		}

		return check;
	}
	//checkGoods
	
	//getBasketList
	public Vector getBasketList(String id){
		
		Vector vec = new Vector();
		
		//상품정보 저장
		ArrayList goodsList = new ArrayList();
		//장바구니 정보 저장
		ArrayList basketList = new ArrayList();
		
		PreparedStatement pstmt2 = null;
		
		ResultSet rs2 = null;
		
		
		try {
			
			con = getConnection();
			
			sql = "SELECT * FROM model2_basket WHERE b_m_id=?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				//장바구니 정보를 저장
				//BasketDTO생성
				basketDTO bkdto = new basketDTO();
				bkdto.setB_date(rs.getDate("b_date"));
				bkdto.setB_g_amount(rs.getInt("b_g_amount"));
				bkdto.setB_g_color(rs.getString("b_g_color"));
				bkdto.setB_g_num(rs.getInt("b_g_num"));
				bkdto.setB_g_size(rs.getString("b_g_size"));
				bkdto.setB_m_id(rs.getString("b_m_id"));
				bkdto.setB_num(rs.getInt("b_num"));
				
				//장바구니 1개의 정보를 리스트 한칸에 저장
				basketList.add(bkdto);
				
				//각각의 장바구니에 해당하는 상품 정보 저장
				sql = "SELECT * FROM model2_goods "
						+ "WHERE num=?";
				
				pstmt2 = con.prepareStatement(sql);
				
				pstmt2.setInt(1, bkdto.getB_g_num());
				
				rs2 = pstmt2.executeQuery();
					
				if(rs2.next()){
					//장바구니에 들어간 상품의 추가 정보 가져오기
					GoodsDTO gdto = new GoodsDTO();
					
					gdto.setImage(rs2.getString("image"));
					gdto.setName(rs2.getString("name"));
					gdto.setPrice(rs2.getInt("price"));
					//필요한 추가정보...
					
					// 상품정보 하나를 리스트 한칸에 저장
					
					goodsList.add(gdto);
				} //rs2 끝
			} //rs 끝
			
			//장바구니 리스트, 상품정보리스트
			//벡터에 0,1 위치에 각각 저장
			vec.add(0, basketList);
			vec.add(1, goodsList);
			
			
			System.out.println("장바구니, 상품정보 리스트 벡터에 저장완료" +vec);
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeDB();
		}
		
		
		
		return vec;
	}
	//getBasketList
	
	//basketDelete
	public void basketDelete(int b_num){
		
		try {
		
			sql = "delete from model2_basket where b_num = ?";
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, b_num);
			
			pstmt.executeUpdate();
			
			System.out.println("----장바구니 DB 삭제 완료 -----");
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeDB();
		}
		
	}
	//basketDelete
	
	public void basketDelete(String id){
		
		try {
		
			sql = "delete from model2_basket where b_m_id = ?";
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, id);
			
			pstmt.executeUpdate();
			
			System.out.println("----장바구니 DB 삭제 완료 -----");
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeDB();
		}
		
	}
	
	
	
}
