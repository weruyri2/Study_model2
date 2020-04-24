package net.admin.goods.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
	
	//getList
	public ArrayList<GoodsDTO> getList(){
		
		ArrayList<GoodsDTO> goodsList = new ArrayList<GoodsDTO>();
		
		
		try {
			sql = "select * from model2_goods";
			
			pstmt = con.prepareStatement(sql);
			
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
	
	public GoodsDTO goodsInfo(int num){
		
		GoodsDTO gdto = new GoodsDTO();
		
		try {
			sql = "select * from model2_goods where num = ? ";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				
				gdto.setNum(num);
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
				
				System.out.println(gdto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeDB();
		}
		
		return gdto;

	}
	
	public void ModifyGoods(GoodsDTO gdto){
		
		try {
			sql="update model2_goods "
					+ "set category=?,name=?,content=?,size=?,"
					+ "color=?, amount=?, price=?, best=?, date=now() where num=?";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, gdto.getCategory());
			pstmt.setString(2, gdto.getName());
			pstmt.setString(3, gdto.getCotent());
			pstmt.setString(4, gdto.getSize());
			pstmt.setString(5, gdto.getColor());
			pstmt.setInt(6, gdto.getAmount());
			pstmt.setInt(7, gdto.getPrice());
			pstmt.setInt(8, gdto.getBest());
			pstmt.setInt(9, gdto.getNum());
			
			pstmt.executeUpdate();
			
			System.out.println("---------DB 수정 완료-----------");
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeDB();
		}
	}

	
	public void deleteGoods(int num) {
		
		try {
			sql="delete from model2_goods where num =?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, num);
			
			pstmt.executeUpdate();
			
			System.out.println("------DB 삭제 완료 ----------");
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeDB();
		}
	}



}
