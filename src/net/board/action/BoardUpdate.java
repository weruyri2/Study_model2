package net.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.board.db.BoardDAO;
import net.board.db.BoardDTO;

public class BoardUpdate implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		System.out.println(" @@@ BoardUpdate_execute() 호출");
		
		// num, pageNum 파라미터값 저장
		int num = Integer.parseInt(request.getParameter("num"));
		String pageNum = request.getParameter("pageNum");
		
		// DB 처리객체 생성 
		BoardDAO bdao = new BoardDAO();
		
		// getBoard(num) 사용  글정보 가져오기
		BoardDTO bdto = bdao.getBoard(num);
		
		// 글정보, pageNum 저장(request)
		request.setAttribute("bdto", bdto);
		request.setAttribute("pageNum", pageNum);
		
		// 페이지 이동 (./board/updateForm.jsp)
		ActionForward forward = new ActionForward();
		forward.setPath("./board/updateForm.jsp");
		forward.setRedirect(false);	
		
		return forward;
	}
	

}
