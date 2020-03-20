package net.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.member.db.MemberBean;
import net.member.db.MemberDAO;

public class MemberUpdate implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		System.out.println("@@@ MemberUpdate_execute() ");
		
		// 세션값 제어
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		
		ActionForward forward = new ActionForward();
		if(id == null){
			forward.setPath("./MemberLogin.me");
			forward.setRedirect(true);
			return forward;
		}
		
		// MemberDAO 객체 생성
		MemberDAO mdao = new MemberDAO();
		
		// getMember(id) 사용해서 사용자 정보를 가져와서 출력
		// => MemberBean 객체를 사용해서 저장후 출력
		MemberBean mb = mdao.getMember(id);
		
		// request 객체에 저장
		request.setAttribute("mb", mb);
		
		// 페이지 이동 (./updateForm.jsp)
		forward.setPath("./member/updateForm.jsp");
		forward.setRedirect(false);
		
		return forward;
	}

}
