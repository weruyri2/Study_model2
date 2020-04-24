package net.order.action;

import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.basket.db.basketDAO;
import net.goods.db.GoodsDAO;
import net.order.db.OrderDAO;
import net.order.db.OrderDTO;

public class OrderAddAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("OrderAddAction execute() 호출");
		
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
		
		OrderDTO odto = new OrderDTO();
		//장바구니 상품정보를 가져오기
		odto.setO_m_id(id);
		odto.setO_receive_name(request.getParameter("o_receive_name"));
		odto.setO_receive_addr1(request.getParameter("o_receive_addr1"));
		odto.setO_receive_addr2(request.getParameter("o_receive_addr2"));
		odto.setO_receive_mobile(request.getParameter("o_receive_mobile"));
		odto.setO_receive_phone(request.getParameter("o_receive_phone"));
		odto.setO_memo(request.getParameter("o_memo"));
		
		//결제 타입을 추가적으로 저장
		odto.setO_trade_type("온라인입금");
		
		//장바구니 상품정보를 가져오기
		basketDAO bkdao = new basketDAO();
		Vector vec = bkdao.getBasketList(id);
		
		List basketList = (List)vec.get(0);
		List goodsList = (List)vec.get(1);
		
		// 결제 모듈(동작) 처리
		
		//OrderDAO 객체 생성 -> addOrder()
		OrderDAO odao = new OrderDAO();
		odao.addOrder(odto,basketList,goodsList);
		
		//메일, 문자 발송
		// -> GoodsDAO 객체 사용 처리 (AdminGoodsDAO 처리 가능)
		GoodsDAO gdao = new GoodsDAO();
		gdao.updateAmount(basketList);
		
		//장바구니 비우기
		bkdao.basketDelete(id);
		
		
		forward.setPath("OrderList.or");
		forward.setRedirect(true);
		
		//페이지 이동
		
		return forward;
	}

}
