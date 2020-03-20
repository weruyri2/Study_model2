package net.member.action;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.member.db.MemberBean;
import net.member.db.MemberDAO;

public class MemberJoinAction implements Action{
	// 회원가입 처리 Model객체 
	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		System.out.println("@@@ MemberJoinAction_execute() ");
		
		// 한글처리 
		request.setCharacterEncoding("UTF-8");
		
		// 전달된 회원정보를 저장 객체생성(자바빈객체)
		MemberBean mb = new MemberBean();
		// 전달된 파라미터정보를 저장 
		
		mb.setAge(Integer.parseInt(request.getParameter("age")));
		mb.setEmail(request.getParameter("email"));
		mb.setGender(request.getParameter("gender"));
		mb.setId(request.getParameter("id"));
		mb.setName(request.getParameter("name"));
		mb.setPass(request.getParameter("pass"));
		mb.setReg_date(new Timestamp(System.currentTimeMillis()));
		
		// 확인
		//System.out.println(mb.toString());

		// DB에 값을 저장하기 위한 객체생성 (DAO객체)
		MemberDAO mdao = new MemberDAO();
		
		// insertMember(자바빈);
		mdao.insertMember(mb);
		
		// 페이지 이동(로그인 페이지로이동)
		// ActionForward 객체를 생성해서 값 저장 후 이동
		ActionForward forward = new ActionForward();
		forward.setPath("./MemberLogin.me");
		forward.setRedirect(true);		
		
		return forward;
	}

	
	
	

}
