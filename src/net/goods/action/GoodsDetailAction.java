package net.goods.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.goods.action.Action;
import net.goods.action.ActionForward;
import net.goods.db.GoodsDAO;
import net.goods.db.GoodsDTO;

public class GoodsDetailAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("GoodsDetailAction execute() 호출");
		
		// num 파라미터 저장
		int num = Integer.parseInt(request.getParameter("num"));
		
		System.out.println("num = "+num);
		
		// GoodsDAO 생성
		GoodsDAO gdao = new GoodsDAO();
		
		GoodsDTO gdto = gdao.getGoods(num);
		
		request.setAttribute("gdto", gdto);

		//-> getGoods(num)
		// GoodsDTO객체 담아서 전달
		// ->request 영역에 저장
		
		//페이지 이동(./goods/goods_detail.jsp)
		ActionForward forward = new ActionForward();
		forward.setPath("./goods/goods_detail.jsp");
		forward.setRedirect(false);

		
		
		
		
		return forward;
	}

}
