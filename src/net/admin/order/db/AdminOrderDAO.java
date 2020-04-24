package net.admin.order.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import net.order.db.OrderDAO;
import net.order.db.OrderDTO;

public class AdminOrderDAO {

	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	String sql = "";
	
	private Connection getConnection() throws Exception {
		
		Context init = new InitialContext();
		
		DataSource ds
		 = (DataSource) init.lookup("java:comp/env/jdbc/model2DB");
		
		con = ds.getConnection();
		
		System.out.println("DB 연결 완료 " + con);
		return con;
	}
	
	public void closeDB(){
		try {
			if(rs != null) rs.close();
			if(pstmt != null) pstmt.close();
			if(con != null) con.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	//getAdminOrderList()
	public List getAdminOrderList(){
		
		List adminOrderList = new ArrayList();
		
		try{
			
			con = getConnection();
			
			sql = "SELECT "
					+"o_trade_num,o_g_name,o_g_amount,o_g_size,o_g_color,"
					+ "sum(o_sum_money) as o_sum_money,"
					+ "o_trans_num,o_date,o_status,o_trade_type, "
					+ "o_m_id from model2_order "
					+ "GROUP BY o_trade_num "
					+ "ORDER BY o_trade_num desc";

			pstmt = con.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				
				OrderDTO odto = new OrderDTO();
				
				odto.setO_m_id(rs.getString("o_m_id"));
				
				odto.setO_trade_num(rs.getString("o_trade_num"));
				odto.setO_g_name(rs.getString("o_g_name"));
				odto.setO_g_amount(rs.getInt("o_g_amount"));
				odto.setO_g_size(rs.getString("o_g_size"));
				odto.setO_sum_money(rs.getInt("o_sum_money"));
				odto.setO_trans_num(rs.getString("o_trans_num"));
				odto.setO_date(rs.getDate("o_date"));
				odto.setO_status(rs.getInt("o_status"));
				odto.setO_trade_type(rs.getString("o_trade_type"));
				
				adminOrderList.add(odto);
			}
			
			System.out.println("adminOrderList 저장 완료");
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeDB();
		}
		
		
		return adminOrderList;
	}
	//getAdminOrderList()
	
	
	//adminOrderDetail
	public List adminOrderDetail(String trade_num){
		
		List adminOrderDetail = new ArrayList();
		
		try {
			con = getConnection();
			
			sql="select * from model2_order where o_trade_num = ?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, trade_num);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				
				OrderDTO odto = new OrderDTO();
				
				odto.setO_date(rs.getDate("o_date"));
				odto.setO_g_name(rs.getString("o_g_name"));
				odto.setO_g_color(rs.getString("o_g_color"));
				odto.setO_g_amount(rs.getInt("o_g_amount"));
				odto.setO_g_size(rs.getString("o_g_size"));
				odto.setO_sum_money(rs.getInt("o_sum_money"));
				odto.setO_trade_num(rs.getString("o_trade_num"));
				odto.setO_trans_num(rs.getString("o_trans_num"));
				odto.setO_status(rs.getInt("o_status"));
				odto.setO_trade_type(rs.getString("o_trade_type"));
				
				
				adminOrderDetail.add(odto);
				
				
				
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeDB();
		}
		
		
		
		return adminOrderDetail;
	}
	//adminOrderDetail()	
	
	
	//updateOrder()	
	public void updateOrder(OrderDTO odto) {
		
		try {
			
			con = getConnection();
			
			sql="UPDATE model2_order SET o_trans_num=?, "
					+ "o_status=? WHERE o_trade_num = ?";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, odto.getO_trans_num());
			pstmt.setInt(2, odto.getO_status());
			pstmt.setString(3, odto.getO_trade_num());
			
			pstmt.executeUpdate();
			
			System.out.println("DB 수정 완료");
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeDB();
		}
		
		
		
		
		
	}
	//updateOrder()	
	
	
	
	//adminOrderDelete()
	public void adminOrderDelete(String trade_num){
		
		try{
			
			con = getConnection();
			
			sql = "DELETE FROM model2_order WHERE o_trade_num=?";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, trade_num);
			
			pstmt.executeUpdate();
			
			System.out.println("삭제 성공");

		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeDB();
		}
		
	}
	//adminOrderDelete()
	
}
