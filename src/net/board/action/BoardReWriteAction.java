package net.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.board.db.BoardDAO;
import net.board.db.BoardDTO;

public class BoardReWriteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		System.out.println("BoardReWriteAction_execute() 실행");
		
		// 요청정보에 대한 한글처리 (인코딩)
		request.setCharacterEncoding("UTF-8");
		
		// 전달된 정보를 BoardDTO 객체에 저장
		BoardDTO bdto = new BoardDTO();
		bdto.setName(request.getParameter("name"));
		bdto.setPass(request.getParameter("pass"));
		bdto.setSubject(request.getParameter("subject"));
		bdto.setContent(request.getParameter("content"));
		bdto.setNum(Integer.parseInt(request.getParameter("num")));
		bdto.setRe_ref(Integer.parseInt(request.getParameter("re_ref")));
		bdto.setRe_lev(Integer.parseInt(request.getParameter("re_lev")));
		bdto.setRe_seq(Integer.parseInt(request.getParameter("re_seq")));
		bdto.setIp(request.getRemoteAddr());
		
		// BoardDAO 객체를 생성후 메서드  reInsertBoard()
		BoardDAO bdao = new BoardDAO();
		bdao.reInsertBoard(bdto);
		
		// 페이지 이동( 글 리스트 )	
		ActionForward forward = new ActionForward();
		forward.setPath("./BoardList.bo");
		forward.setRedirect(true);
		return forward;
	}

}
