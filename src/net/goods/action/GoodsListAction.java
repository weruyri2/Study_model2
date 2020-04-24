package net.goods.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.goods.db.GoodsDAO;
import net.goods.db.GoodsDTO;


public class GoodsListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
	
		System.out.println("GoodsListAction excute() 호출");
		
		// GoodsDAO 객체 생성
		GoodsDAO agdao= new GoodsDAO();
		// getGoodsList()
		// -> "전체","인기상품","그외 나머지 카테고리"
		
		String item = request.getParameter("item");
		
		System.out.println(item);
		
		
		if(item == null){
			item = "all";
			
		}
			
		
		// 상품리스트를 가져오는 메서드 생성
		ArrayList<GoodsDTO> goodsList = agdao.getGoodsList(item);
		
		System.out.println(goodsList);
		// 상품리스트를 저장
		
		request.setAttribute("goodsList", goodsList);
		
		// 페이지 이동(view)
		ActionForward forward = new ActionForward();
		forward.setPath("./goods/goods_list.jsp");
		forward.setRedirect(false);
		
		
		return forward;
	}

}
