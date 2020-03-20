package net.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.board.db.BoardDAO;
import net.board.db.BoardDTO;

public class BoardWriteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println(" @@@ BoardWriteAction_execute() 호출");
		
		// 한글처리 
		request.setCharacterEncoding("UTF-8");
		
		// 전달된 정보를 저장(자바빈객체)
		BoardDTO bdto = new BoardDTO();
		
		// DTO객체 안에 저장
		bdto.setName(request.getParameter("name"));
		bdto.setPass(request.getParameter("pass"));
		bdto.setSubject(request.getParameter("subject"));
		bdto.setContent(request.getParameter("content"));
		
		// IP 정보 추가 저장
		bdto.setIp(request.getRemoteAddr());
		
		// 디비 처리 객체 
		BoardDAO bdao = new BoardDAO();
		// insertBoard();
		bdao.insertBoard(bdto);
		
		// 페이지 이동( 글 목록 페이지이동 )
		ActionForward forward = new ActionForward();
		forward.setPath("./BoardList.bo");
		forward.setRedirect(true);		
		return forward;
	}

}
