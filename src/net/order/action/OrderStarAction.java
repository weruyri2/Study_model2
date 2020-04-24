package net.order.action;

import java.util.ArrayList;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.basket.db.basketDAO;
import net.basket.db.basketDTO;
import net.member.db.MemberBean;
import net.member.db.MemberDAO;

public class OrderStarAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("OrderStarAction_execute() 호출");
		
		// 구매하기 페이지
		// 사용자 로그인 체크 -> 장바구니,상품정보를 가져오기
		// -> 사용자 정보를 가져오기 -> 필요한 정보를 저장 -> 페이지 이동
		HttpSession session = request.getSession();
		
		String id = (String)session.getAttribute("id");
		
		ActionForward forward = new ActionForward();
		
		if(id==null){
			forward.setPath("./MemberLogin.me");
			forward.setRedirect(true);
			return forward;
		}
		
		request.setCharacterEncoding("UTF-8");
		
		basketDAO bkdao = new basketDAO();
		
		Vector vec = bkdao.getBasketList(id);
		
		ArrayList basketList = (ArrayList)vec.get(0);
		ArrayList goodsList = (ArrayList)vec.get(1);
		
		request.setAttribute("basketList", basketList);
		request.setAttribute("goodsList", goodsList);
		
		MemberDAO mdao = new MemberDAO();
		
		MemberBean memberDTO = new MemberBean();
		
		memberDTO = mdao.getMember(id);
		
		request.setAttribute("memberDTO", memberDTO);
		
		
		forward.setPath("./goods_order/goods_buy.jsp");
		forward.setRedirect(false);
		
		return forward;
	}

}
