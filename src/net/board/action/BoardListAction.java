package net.board.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.board.db.BoardDAO;

public class BoardListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		System.out.println(" @@@ BoardListAction_execute() 호출 ");
		
		// 디비 처리객체 생성
		BoardDAO bdao = new BoardDAO();
		// 게시판의 글여부 판단
		// getBoardCount()
		int check = bdao.getBoardCount();
		
		////////////////////////////////////////////////////////////
		// 페이징 처리 
		// 한 페이지에 보여질 글 개수 
		int pageSize = 5;
		// 현 페이지가 몇 페이지인지를 확인
		String pageNum = request.getParameter("pageNum");
		if( pageNum == null ){
			pageNum = "1";
		}
		
		// 시작행
		int currentPage = Integer.parseInt(pageNum);
		int startRow = (currentPage -1)*pageSize+1;
		
		// 끝행
		int endRow = currentPage * pageSize;
		
		// 전체 페이지수 계산하기
		int pageCount = check / pageSize + (check % pageSize == 0? 0:1);
		// 한 화면에 보여주는 블럭의수(페이지 번호 개수)
		int pageBlock = 3;
		// 시작페이지
		int startPage = ((currentPage-1)/pageBlock) * pageBlock+1;
		// 끝페이지
		int endPage = startPage + pageBlock -1;
		if(endPage > pageCount){
			endPage = pageCount;
		}
		
		
		////////////////////////////////////////////////////////////		
		
		
		// 게시판의 정보를 가져오는 메서드
		// getBoardList()
		ArrayList boardList = null;
		if(check != 0){
			// boardList = bdao.getBoardList();
			boardList = bdao.getBoardList(startRow,pageSize);
		}
		
		// 리스트 정보를 저장
		request.setAttribute("boardList", boardList);
		
		// 페이징 처리 정보 저장  
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("count", check);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("pageBlock", pageBlock);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		
		// 페이지 이동후 출력 (view 페이지)
		ActionForward forward = new ActionForward();
		forward.setPath("./board/boardList.jsp");
		forward.setRedirect(false);
		
		return forward;
	}
}
