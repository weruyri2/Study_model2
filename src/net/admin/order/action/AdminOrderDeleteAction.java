package net.admin.order.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.admin.order.db.AdminOrderDAO;

public class AdminOrderDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("AdminOrderDeleteAction execute() 호출");
		
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
		
		String trade_num = (String)request.getParameter("trade_num");
		
		//AdminOrderDAO 객체 생성
		//getAdminOrderList()
		AdminOrderDAO aodao = new AdminOrderDAO();
		
		aodao.adminOrderDelete(trade_num);
		
		//페이지 이동
		forward.setPath("./AdminOrderList.ao");
		forward.setRedirect(true);
		
		
		return forward;
	}


}
