package net.board.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.board.db.BoardDAO;

public class BoardDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		System.out.println("BoardDeleteAction_execute() 실행");
		
		// num,pass / pageNum 저장
		
		String pageNum=request.getParameter("pageNum");
		
		int num = Integer.parseInt(request.getParameter("num"));
		String pass = request.getParameter("pass");
		
		// DB객체를 생성해서 처리
		BoardDAO bdao = new BoardDAO();
		// deleteBoard(num,pass)
		int result = bdao.deleteBoard(num,pass);
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		if(result == 0){
			out.print("<script>");
			out.print("  alert('비밀번호 오류'); ");
			out.print("  history.back(); ");
			out.print("</script>");
			out.close();
			
			return null;			
		}else if(result == -1){
			out.print("<script>");
			out.print("  alert('게시판 글 없음'); ");
			out.print("  history.back(); ");
			out.print("</script>");
			out.close();
			
			return null;
		}
		
		//result == 1
		// 글 리스트로 이동 (+페이지번호)
		out.print("<script>");
		out.print("  alert('글삭제 성공'); ");
		out.print("  location.href='./BoardList.bo?pageNum="+pageNum+"'; ");
		out.print("</script>");
		out.close();
		
		return null;
	}
	
}
