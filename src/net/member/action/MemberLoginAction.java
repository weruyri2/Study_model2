package net.member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.member.db.MemberDAO;

public class MemberLoginAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		System.out.println("@@@ MemberLoginAction_execute() ");

		// LoginPro.jsp 페이지 동작처럼 사용

		// 전달받은 정보 저장(ID,PW)
		String id = request.getParameter("id");
		String pass = request.getParameter("pass");

		// DB 처리객체 생성 - MemberDAO
		MemberDAO mdao = new MemberDAO();

		// 회원여부 판단하는 메서드 생성 - int idCheck(id,pass);
		int check = mdao.idCheck(id, pass);
		// 0 - "비밀번호 오류" alert창
		// -1 - "아이디가 없음" alert창
		// 1 - "로그인 처리"

		if (check == 0) {
			// 응답정보의 타입을 html 형식으로 응답하겠다.설정
			response.setContentType("text/html; charset=UTF-8");

			// 출력객체를 생성(response객체의 정보를 가지고 생성)
			PrintWriter out = response.getWriter();

			out.print("<script>");
			out.print("  alert('비밀번호 오류 입니다.'); ");
			out.print("  history.back(); ");
			out.print("</script>");

			out.close();

			return null;

		} else if (check == -1) {
			// 응답정보의 타입을 html 형식으로 응답하겠다.설정
			response.setContentType("text/html; charset=UTF-8");

			// 출력객체를 생성(response객체의 정보를 가지고 생성)
			PrintWriter out = response.getWriter();

			// 자바스크립트를 통한 페이지 이동은 컨트롤러 없이 바로 이동 
			out.print("<script>");
			out.print("  alert('아이디가 없습니다.'); ");
			//out.print("  history.back(); ");
			out.print(" location.href='./MemberLogin.me'; ");
			out.print("</script>");
            // out 객체의 자원 해제 
			out.close();

			return null;

		}

		// 로그인 성공시 ID값을 세션객체에 저장해서 사용
		// check == 1
		// session 내장객체는 JSP에서 제공되는 내장객체이다.
		
		// request 객체를 사용해서 세션 객체를 생성
		HttpSession session = request.getSession();
		
		session.setAttribute("id", id);
		
		// 페이지 이동(메인페이지)
		ActionForward forward = new ActionForward();
		forward.setPath("./Main.me");
		forward.setRedirect(true);
		return forward;
	}

}
