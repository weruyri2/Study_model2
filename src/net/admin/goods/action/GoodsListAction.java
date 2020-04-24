package net.admin.goods.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.admin.goods.db.GoodsDAO;
import net.admin.goods.db.GoodsDTO;

public class GoodsListAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
	
		System.out.println("GoodsListAction_execute() 호출");
		
		// AdminGoodsDAO 객체 생성
		GoodsDAO agdao= new GoodsDAO();
		
		// 상품리스트를 가져오는 메서드 생성
		ArrayList<GoodsDTO> goodsList = agdao.getList();
		
		System.out.println(goodsList);
		// 상품리스트를 저장
		
		request.setAttribute("goodsList", goodsList);
		
		// 페이지 이동(view)
		ActionForward forward = new ActionForward();
		forward.setPath("./admingoods/admin_goods_list.jsp");
		forward.setRedirect(false);
		
		// ./admingoods/admin_goods_list.jsp
		
		
		return forward;
	}
	

}
