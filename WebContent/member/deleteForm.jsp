<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
   <h1>WebContent/member/deleteForm.jsp</h1>
   <%
      //ID값 가져오기 
      String id = (String) session.getAttribute("id");
      if(id == null){
    	 response.sendRedirect("./MemberLogin.me");  
      }   
   %>   
   
   <fieldset>
    <legend>회원 탈퇴하기</legend>
     <form action="./MemberDeleteAction.me" method="post">
     <input type="hidden" name="id" value="<%=id%>">
           비밀번호 : <input type="password" name="pass">
       <input type="submit" value="탈퇴하기">
     </form>    
   </fieldset>
   
   
   
   
   
</body>
</html>