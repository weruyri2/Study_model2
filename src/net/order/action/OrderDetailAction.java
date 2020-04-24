package net.order.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.order.db.OrderDAO;

public class OrderDetailAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("OrderDetailAction execute() 호출");
		
		HttpSession session = request.getSession();
		
		String id = (String)session.getAttribute("id");
		
		ActionForward forward = new ActionForward();
		
		if(id==null){
			forward.setPath("./MemberLogin.me");
			forward.setRedirect(true);
			return forward;
		}
		
		//한글 처리
		request.setCharacterEncoding("UTF-8");
		
		//주문번호 정보를 저장
		String trade_num = request.getParameter("trade_num");
		
		//OrderDAO
//		OrderDAO odao = new OrderDAO();
		List orderDetailList 
			= new OrderDAO().orderDetail(trade_num);
		
		//저장 (request)
		request.setAttribute("orderDetailList", orderDetailList);
		
		forward.setPath("/goods_order/order_detail.jsp");
		forward.setRedirect(false);

		
		return forward;
	}

}
