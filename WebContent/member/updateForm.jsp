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
  <h1>WebContent/member/updateForm.jsp</h1>
  <%
	  	String id = (String) session.getAttribute("id");
	
	  	if (id == null)
	  		response.sendRedirect("./MemberLogin.me");
	
	  	// 회원정보 가져오기 
	  	// request.setAttribute("mb", mb);
	  	MemberBean mb = (MemberBean) request.getAttribute("mb");
	
	  	String gender = mb.getGender();
	  	if (gender == null)
	  		gender = "남";
  %>
  
  
  <fieldset>
    <legend> 회원 정보 수정 </legend>
    
    <form action="./MemberUpdateAction.me" method="post">
	      아이디* : <input type="text" name="id" value="<%=mb.getId()%>" readonly><br>
	      비밀번호 : <input type="password" name="pass"><br>
	      이름 : <input type="text" name="name" value="<%=mb.getName()%>"><br>
	      나이 : <input type="text" name="age" value="<%=mb.getAge()%>"><br>
	      성별 : <input type="radio" name="gender" value="남"
	         <% if(gender.equals("남")){%> checked <% } %>
	         > 남
	         <input type="radio" name="gender" value="여"
	         <% 
	         	if(gender.equals("여")){
	         %> 
	        		 checked 
	         <%
	         	} 
	         %>
	         > 여<br>
	      이메일* : <input type="text" name="email" value="<%=mb.getEmail()%>"><br>   
	   <input type="submit" value="회원정보 수정">   
    </form>  
  
  </fieldset>
  
  <h2> *표시된 항목은 데이터 중복X </h2>
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
</body>
</html>