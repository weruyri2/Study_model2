package net.basket.action;

import java.util.ArrayList;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.basket.db.basketDAO;

public class BasketListAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		System.out.println("BasketListAction_execute() 호출");
		
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
		
		basketDAO bkdao = new basketDAO();
		//장바구니 리스트 가져와서 저장
		Vector vec = bkdao.getBasketList(id);
		
		// 해당정보 request 저장
		ArrayList basketList = (ArrayList)vec.get(0);
		ArrayList goodsList = (ArrayList)vec.get(1);
				
		
		request.setAttribute("basketList", basketList);
		request.setAttribute("goodsList", goodsList);
//		request.setAttribute("goodsList", vec.get(1));
		
		//장바구니 테이블 생성
		
		
		//번호,사진,제품명,크기,색상,수량,가격,취소
		
		
		
		
		//구매하기, 계속쇼핑하기 링크
		
		
		forward.setPath("/goods_order/goods_basket.jsp");
		forward.setRedirect(false);
		
		
		//페이지 이동 (./goods_order/goods_basket.jsp)
		
		
		return forward;
	}

}
