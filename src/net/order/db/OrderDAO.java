package net.order.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import net.basket.db.basketDTO;
import net.goods.db.GoodsDTO;

public class OrderDAO {
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

	
	//addOrder(odto,basketList,goodsList)
	public void addOrder(OrderDTO odto, List basketList, List goodsList){
		
		int o_num = 0; // 주문 일련번호
		int trade_num = 0; //주문 번호
		
		// 주문번호 변경을 하기위한 객체
		Calendar cal = Calendar.getInstance();
		// -> 싱글턴패턴 : 시스템의 시간정보를 가져다 사용하는 추상클래스                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		
		
		try {
			con = getConnection();
			
			// 주문 일련번호 계산하기 (o_num)
			sql = "SELECT MAX(o_num) FROM model2_order";
			
			pstmt = con.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				o_num = rs.getInt(1)+1;
			}
			
			trade_num = o_num;
			
			System.out.println(" 주문 일련번호 : "+o_num+", 주문번호 : "+trade_num);
			
			
			for(int i=0; i<basketList.size(); i++){
			
				basketDTO bkdto = (basketDTO)basketList.get(i);
				GoodsDTO gdto = (GoodsDTO)goodsList.get(i);
				
				// 18번, 20번 ? -> now() 변경
			sql = "INSERT INTO model2_order "
					+ "values("
					+ "?,?,?,?,?,"
					+ "?,?,?,?,?,"
					+ "?,?,?,?,?,"
					+ "?,?,now(),?,now(),"
					+ "? )";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, o_num);
			//pstmt.setString(2, trade_num);
			pstmt.setString(2, 
					sdf.format(cal.getTime()).toString()+"-"+trade_num);
			pstmt.setInt(3, bkdto.getB_g_num());
			pstmt.setString(4, gdto.getName());
			pstmt.setInt(5, bkdto.getB_g_amount());
			pstmt.setString(6, bkdto.getB_g_size());
			pstmt.setString(7, bkdto.getB_g_color());
			pstmt.setString(8, odto.getO_m_id());
			
			pstmt.setString(9, odto.getO_receive_name());
			pstmt.setString(10, odto.getO_receive_addr1());
			pstmt.setString(11, odto.getO_receive_addr2());
			pstmt.setString(12, odto.getO_receive_phone());
			pstmt.setString(13, odto.getO_receive_mobile());
			pstmt.setString(14, odto.getO_memo());
			
			// 물건별 총액 (구매 수량 * 물건 낱개 가격)
			pstmt.setInt(15, bkdto.getB_g_amount()*gdto.getPrice());
			pstmt.setString(16, odto.getO_trade_type());
			pstmt.setString(17, odto.getO_trade_payer());
			//18번 ? 함수로 사용됨(now())
			pstmt.setString(18, ""); // 운송장 번호
			//20번 ? 함수로 사용됨(now())
			pstmt.setInt(19, 0); // 주문 상태표시
			
			pstmt.executeUpdate();
			
			System.out.println(o_num+"번 구매정보 저장완료 ! ");
			
			o_num++;
			}//model2_order 테이블 INSERT동작 반복
			
			System.out.println("장바구니에 있던 모든 상품정보를 주문 완료 !");
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeDB();
		}
		
	}

	//addOrder(odto,basketList,goodsList)
	
	
	//getOrderList(id)
	public List getOrderList(String id){
		
		List orderList = new ArrayList();
		
		try {
			
			con = getConnection();
			
//			sql = "select * from model2_order where o_m_id = ?";
			
			sql = "SELECT "
					+"o_trade_num,o_g_name,o_g_amount,o_g_size,o_g_color,"
					+ "sum(o_sum_money) as o_sum_money,"
					+ "o_trans_num,o_date,o_status,o_trade_type "
					+ "from model2_order where o_m_id=? "
					+ "GROUP BY o_trade_num "
					+ "ORDER BY o_trade_num desc";
			
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				OrderDTO odto = new OrderDTO();
				
				odto.setO_trade_num(rs.getString("o_trade_num"));
				odto.setO_g_name(rs.getString("o_g_name"));
				odto.setO_g_amount(rs.getInt("o_g_amount"));
				odto.setO_g_size(rs.getString("o_g_size"));
				odto.setO_sum_money(rs.getInt("o_sum_money"));
				odto.setO_trans_num(rs.getString("o_trans_num"));
				odto.setO_date(rs.getDate("o_date"));
				odto.setO_status(rs.getInt("o_status"));
				odto.setO_trade_type(rs.getString("o_trade_type"));
	
				
				
				orderList.add(odto);
				
			}
			
			System.out.println("orderList 저장 완료 ");
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeDB();
		}
	
		return orderList;		
	}
	//getOrderList(id)
	
	//orderDetail(trade_num)
	public List orderDetail(String trade_num){
		
		List orderDetailList = new ArrayList();
		
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
				
				
				orderDetailList.add(odto);
				
				
				
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeDB();
		}
		
		
		
		return orderDetailList;
	}
	//orderDetail(trade_num)
	
	
	
	
	
	
	
}
