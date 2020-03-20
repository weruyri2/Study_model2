<%@page import="net.member.db.MemberBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
  <h1>WebContent/member/memberinfo.jsp</h1>
  <%
    //세션값 처리 
    String id =(String) session.getAttribute("id");
  
    if(id == null){
    	response.sendRedirect("./MemberLogin.me");
    }
    
    
    // MemberInfoAction 클래스에서 전달된 정보를 저장 (request영역데이터)
    MemberBean mb = (MemberBean)request.getAttribute("mb");
  %>
  <h1> 회원정보 페이지 </h1>
  
  <h2> 아이디 : <%=mb.getId() %></h2>
  <h2> 비밀번호 : <%=mb.getPass() %></h2>
  <h2> 이름: <%=mb.getName() %></h2>
  <h2> 나이 : <%=mb.getAge() %></h2>
  <h2> 성별 : <%=mb.getGender() %></h2>
  <h2> 이메일 : <%=mb.getEmail() %></h2>
  <h2> 회원가입일 : <%=mb.getReg_date() %></h2>
  
  
  <h3><a href="./Main.me"> main 페이지 </a></h3>
  
  
  
  
  
  
  
  
  
  
  
  
</body>
</html>