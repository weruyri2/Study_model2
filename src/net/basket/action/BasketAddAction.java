package net.basket.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.basket.db.basketDAO;
import net.basket.db.basketDTO;

public class BasketAddAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println("BasketAddAction_execute() 호출");
		
		// 세션값을 사용해서 장바구니 사용가능
		// 세션값이 없을경우 장바구니 사용불가능 (로그인페이지이동)
		HttpSession session = request.getSession();
		
		String id = (String)session.getAttribute("id");
		
		ActionForward forward = new ActionForward();
		
		if(id==null){
			forward.setPath("./MemberLogin.me");
			forward.setRedirect(true);
			return forward;	
		}
		
		request.setCharacterEncoding("UTF-8");
		//한글 처리
		
		//BasketDTO 객체 생성 -> 정보 저장
		basketDTO bkdto = new basketDTO();
		bkdto.setB_g_num(Integer.parseInt(request.getParameter("num")));
		bkdto.setB_g_amount(Integer.parseInt(request.getParameter("amount")));
		bkdto.setB_g_size(request.getParameter("size"));
		bkdto.setB_g_color(request.getParameter("color"));
		bkdto.setB_m_id(id);
		
		
		//BasketDAO 저장
		basketDAO bkdao = new basketDAO();
		int check = bkdao.checkGoods(bkdto);
		
		if(check != 1){
			bkdao.basketAdd(bkdto);
		}
		
		// 페이지 이동 (장바구니 목록 페이지)
		forward.setPath("./BasketList.ba");
		forward.setRedirect(true);	
		return forward;
	}

}
