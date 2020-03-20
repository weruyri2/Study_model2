<%@page import="net.member.db.MemberBean"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>WebContent/member/memberList.jsp</h1>

	<%
		// 세션값 제어( 로그인,관리자 여부 )
		String id = (String) session.getAttribute("id");
	    
	    if(id == null || !id.equals("admin")){
	    	response.sendRedirect("MemberLogin.me");
	    }

		// 이전페이지에서 전달된 데이터를 처리 (회원목록리스트)
		// request 영역에 저장
		// request.setAttribute("memberList", memberList);
		List<MemberBean> memberList 
		   = (List<MemberBean>)request.getAttribute("memberList");

		// 표를 사용해서 데이터 출력
	%>
	  <table border="1">
	    <tr>
	      <td>아이디</td>
	      <td>비밀번호</td>
	      <td>이름</td>
	      <td>가입일자</td>
	      <td>나이</td>
	      <td>성별</td>
	      <td>이메일</td>
	    </tr>
	    
	    <%
	       for(int i=0;i<memberList.size();i++){
	    	      MemberBean mb = memberList.get(i);
	    	   %>
	    	    <tr>
			      <td><%=mb.getId() %></td>
			      <td><%=mb.getPass() %></td>
			      <td><%=mb.getName() %></td>
			      <td><%=mb.getReg_date() %></td>
			      <td><%=mb.getAge() %></td>
			      <td><%=mb.getGender() %></td>
			      <td><%=mb.getEmail() %></td>
			    </tr>
	    	   <%
	       }
	    %>
	  </table>
	  
	  <h2><a href="./Main.me"> 메인페이지 이동 </a></h2>















</body>
</html>