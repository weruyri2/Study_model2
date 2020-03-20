package net.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.board.db.BoardDAO;
import net.board.db.BoardDTO;

public class BoardContentAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		System.out.println(" @@@ BoardContentAction_execute() ");
		
		// BoardContent.bo?num=11&pageNum=1
		// 파라미터 num, pageNum 저장 
		int num = Integer.parseInt(request.getParameter("num"));
		String pageNum = request.getParameter("pageNum");
		
		// BoardDAO 객체 생성 
		BoardDAO bdao = new BoardDAO();
		
		// 글 읽기 => 조회수 1증가 
		// updateReadCount(num);
		bdao.updateReadCount(num);
		
		// 글내용 가져오기 (num)
		BoardDTO bdto = bdao.getBoard(num);
				
		// 저장후 페이지 이동
		request.setAttribute("bdto", bdto);
		request.setAttribute("pageNum", pageNum);
		
		
		ActionForward forward = new ActionForward();
		forward.setPath("./board/boardContent.jsp");
		forward.setRedirect(false);
		
		return forward;
	}
	
	
	

}
