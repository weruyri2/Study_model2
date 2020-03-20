package net.member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class MemberLogoutAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		System.out.println("@@@ MemberLogoutAction_execute()");
		
		// 로그아웃 처리 동작 
		// 세션정보 초기화 
		HttpSession session = request.getSession();
		
		session.invalidate();
		
		
		// "로그아웃 성공! " alert 메세지 출력후 
		// 메인페이지로 이동	
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		out.print("<script>");
		out.print(" alert('로그아웃 성공'); ");
		out.print(" location.href='./Main.me'; ");
		out.print("</script>");
		
		out.close();
		
		return null;
	}

}
