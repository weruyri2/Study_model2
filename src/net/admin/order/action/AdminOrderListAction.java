package net.admin.order.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.admin.order.db.AdminOrderDAO;



public class AdminOrderListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("AdminOrderListAction execute() 호출");
		
		HttpSession session = request.getSession();
		
		String id = (String)session.getAttribute("id");
		
		ActionForward forward = new ActionForward();
		
		if(id==null || !id.equals("admin")){
			forward.setPath("./MemberLogin.me");
			forward.setRedirect(true);
			return forward;
		}
		
		//한글 처리
		request.setCharacterEncoding("UTF-8");
		
		
		//AdminOrderDAO 객체 생성
		//getAdminOrderList()
		AdminOrderDAO aodao = new AdminOrderDAO();
		
		List adminOrderList = aodao.getAdminOrderList();
	
		//저장
		request.setAttribute("adminOrderList", adminOrderList);
		
		
		//페이지 이동
		forward.setPath("./admin_order/admin_order_list.jsp");
		forward.setRedirect(false);
		
		
		return forward;
	}

	
}
