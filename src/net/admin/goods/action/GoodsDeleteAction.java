package net.admin.goods.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.admin.goods.db.GoodsDAO;

public class GoodsDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("GoodsDeleteAction excute 실행");
		
		int num = Integer.parseInt(request.getParameter("num"));
		
		GoodsDAO gdao = new GoodsDAO();
		
		gdao.deleteGoods(num);
		
		ActionForward forward =  new ActionForward();
		forward.setPath("./GoodsList.ag");		
		forward.setRedirect(true);		
		
		return forward;
	}

}
