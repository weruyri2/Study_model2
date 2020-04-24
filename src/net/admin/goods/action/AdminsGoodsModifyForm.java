package net.admin.goods.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.admin.goods.db.GoodsDAO;
import net.admin.goods.db.GoodsDTO;

public class AdminsGoodsModifyForm implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("AdminsGoodsModifyForm excute 실행");
		
		int num = Integer.parseInt(request.getParameter("num"));
		
		GoodsDAO gdao = new GoodsDAO();

		GoodsDTO gdto = gdao.goodsInfo(num);
		
		request.setAttribute("gdto", gdto);
		
		ActionForward forward = new ActionForward();
		forward.setPath("./admingoods/admin_goods_modify.jsp");
		forward.setRedirect(false);
		
		return forward;
	}

}
