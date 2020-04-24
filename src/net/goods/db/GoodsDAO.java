package net.goods.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import net.basket.db.basketDTO;
import net.goods.db.GoodsDTO;

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
	
	
	public ArrayList<GoodsDTO> getGoodsList(String item){
		
		ArrayList<GoodsDTO> goodsList = new ArrayList<GoodsDTO>();
		
		StringBuffer SQL = new StringBuffer();
			
			try {
				
				SQL.append("select * from model2_goods");
				
				
				if(item.equals("all")){
					
					pstmt = con.prepareStatement(SQL.toString());
				}else if(item.equals("best")){
					SQL.append(" where best=1");
					
					pstmt = con.prepareStatement(SQL.toString());
					
				}else{
					SQL.append(" where category=?");
					
					pstmt = con.prepareStatement(SQL.toString());
					pstmt.setString(1, item);
					
				}
				
				
				rs = pstmt.executeQuery();
				
				while(rs.next()){
					GoodsDTO gdto = new GoodsDTO();
					gdto.setNum(rs.getInt("num"));
					gdto.setCategory(rs.getString("category"));
					gdto.setName(rs.getString("name"));
					gdto.setContent(rs.getString("content"));
					gdto.setSize(rs.getString("size"));
					gdto.setColor(rs.getString("color"));
					gdto.setAmount(rs.getInt("amount"));
					gdto.setPrice(rs.getInt("price"));
					gdto.setImage(rs.getString("image"));
					gdto.setBest(rs.getInt("best"));
					gdto.setDate(rs.getDate("date"));
					
					goodsList.add(gdto);
					
				}
				System.out.println("리스트 DB 꺼내기 완료");
				
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				closeDB();
			}
		

		
		
		
		return goodsList;
	}
	
	
	public GoodsDTO getGoods(int num){
		
		GoodsDTO gdto = new GoodsDTO();

		try {
			
			sql= "select * from model2_goods where num = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()){

				gdto.setNum(rs.getInt("num"));
				gdto.setCategory(rs.getString("category"));
				gdto.setName(rs.getString("name"));
				gdto.setContent(rs.getString("content"));
				gdto.setSize(rs.getString("size"));
				gdto.setColor(rs.getString("color"));
				gdto.setAmount(rs.getInt("amount"));
				gdto.setPrice(rs.getInt("price"));
				gdto.setImage(rs.getString("image"));
				gdto.setBest(rs.getInt("best"));
				gdto.setDate(rs.getDate("date"));
				

			}
			
			System.out.println("상품 정보 DB 완료");
			
	
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			closeDB();
		}
		
		
		
		return gdto;
	}
	//getGoods(num)
	
	//updateAmount(basketList)
	public void updateAmount(List basketList){
		
		try {
			con = getConnection();
			
			for(int i=0; i<basketList.size();i++){
				basketDTO bkdto = (basketDTO)basketList.get(i);
				
			sql = "UPDATE model2_goods SET amount = amount-?"
					+ " where num=?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, bkdto.getB_g_amount());
			pstmt.setInt(2, bkdto.getB_g_num());
			
			pstmt.executeUpdate();
			
			System.out.println(bkdto.getB_g_num()+"번 상품 수량 변경 완료!");
			}// for
			
			System.out.println(" 장바구니 (결제완료)상품 수량 변경 완료");
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeDB();
		}
		
		
		
	}
	
	//updateAmount(basketList)

	
	
	
	
	
	
}
