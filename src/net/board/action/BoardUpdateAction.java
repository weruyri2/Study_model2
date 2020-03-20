package net.board.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.board.db.BoardDAO;
import net.board.db.BoardDTO;

public class BoardUpdateAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		System.out.println(" @@@ BoardUpdateAction_execute() ");
		
		// 한글 처리 
		request.setCharacterEncoding("UTF-8");
		
		// 전달된 파라미터값 저장 ( num,name,pass,subject,content, pageNum)
		// -> 해당정보를 DTO객체를 사용해서 저장 
		String pageNum = request.getParameter("pageNum");
		
		BoardDTO bdto = new BoardDTO();
		bdto.setNum(Integer.parseInt(request.getParameter("num")));
		bdto.setName(request.getParameter("name"));
		bdto.setPass(request.getParameter("pass"));
		bdto.setSubject(request.getParameter("subject"));
		bdto.setContent(request.getParameter("content"));
		
		// DB 처리객체 생성 DAO객체 생성
		BoardDAO bdao = new BoardDAO();
		// 메서드 : updateBoard(DTO);
		int check = bdao.updateBoard(bdto);		
		// 정수값 0,1,-1
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();

		if(check == 0){
			out.println("<script>");
			out.println(" alert('비밀번호 오류!!'); ");
			out.println(" history.back(); ");
			out.println("</script>");
			out.close();
			System.out.println(" 자바스크립트 사용 이동 완료 ");
			return null;			
		}else if(check == -1){
			out.println("<script>");
			out.println(" alert('게시판 글없음!!'); ");
			out.println(" history.back(); ");
			out.println("</script>");
			out.close();
			System.out.println(" 자바스크립트 사용 이동 완료 ");
			return null;			
		}
		
		// 수정후/처리후( 자바스크립트 사용 페이지 이동 )
		// check == 1
		out.println("<script>");
		out.println(" alert('글 수정 성공'); ");
		out.println(" location.href='./BoardList.bo?pageNum="+pageNum+"'; ");
		out.println("</script>");
		out.close();
		System.out.println(" 자바스크립트 사용 이동 완료 ");
		return null;	
		
	}

}
