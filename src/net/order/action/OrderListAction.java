package net.order.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.order.db.OrderDAO;

public class OrderListAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println("OrderListAction execute() 호출");
		
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
		
		//주문 상태의 정보를 확인 (본인의 주문정보만)
		//view 페이지에서 출력
		
		OrderDAO odao = new OrderDAO();
		
		List orderList = odao.getOrderList(id);
		
		session.setAttribute("orderList", orderList);
		
		forward.setPath("./goods_order/order_list.jsp");
		forward.setRedirect(false);
		
		return forward;
	}

}
