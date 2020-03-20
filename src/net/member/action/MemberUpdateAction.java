package net.member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.member.db.MemberBean;
import net.member.db.MemberDAO;

public class MemberUpdateAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		 System.out.println("@@@ MemberUpdateAction_execute() ");
		 
		 // 세션처리 
		 HttpSession session = request.getSession();
		 String id = (String)session.getAttribute("id");
		 
		 ActionForward forward = new ActionForward();
		 if(id == null){
			 forward.setPath("./MemberLogin.me");
			 forward.setRedirect(true);
			 return forward;
		 }
		 
		 
		 // 한글처리 
		 request.setCharacterEncoding("UTF-8");
		 
		 // 이전페이지에서 전달한 정보 저장(파라미터)
		 // MemberBean 객체 생성(자바빈)
		 MemberBean mb = new MemberBean();
		 
		 mb.setAge(Integer.parseInt(request.getParameter("age")));
		 mb.setEmail(request.getParameter("email"));
		 mb.setGender(request.getParameter("gender"));
		 mb.setId(id);
		 mb.setName(request.getParameter("name"));
		 mb.setPass(request.getParameter("pass"));
		 
		 // MemberDAO 객체 생성
		 MemberDAO mdao = new MemberDAO();
		 // 수정 처리 메서드 : updateMember(mb)
		 int check = mdao.updateMember(mb);
		 
		 // 0,1,-1 결과에 따라서 처리 
		 // 0 => 비밀번호 오류
		 // 1 => 정상수정
		 // -1 => 아이디 없음
		 // alert 메세지 후 뒤로이동, 메인페이지로 이동  
		 
		 response.setContentType("text/html; charset=UTF-8");
		 PrintWriter out = response.getWriter();
		 
		 if( check == 0 ){
			 out.print("<script>");
			 out.print(" alert('비밀번호 오류'); ");
			 out.print(" history.back(); ");
			 out.print("</script>");
			 out.close();
			 return null;
		 }else if(check == -1){
			 out.print("<script>");
			 out.print(" alert('아이디 없음'); ");
			 out.print(" history.back(); ");
			 out.print("</script>"); 
			 out.close();
			 return null;
		 }
		 // check == 1;
		 out.print("<script>");
		 out.print(" alert('정보 수정 성공'); ");
		 out.print(" location.href='./Main.me'; ");
		 out.print("</script>"); 
		 out.close();
		 return null;
		
	}

}
