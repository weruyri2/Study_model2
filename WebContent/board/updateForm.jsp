<%@page import="net.board.db.BoardDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
   <h1>WebContent/board/updateForm.jsp</h1>
	<h1> 게시판 글 수정(정보 수정) </h1>
	<%
	   BoardDTO bdto =(BoardDTO)request.getAttribute("bdto");
	   String pageNum = (String)request.getAttribute("pageNum");	
	%>
	
	<fieldset>
	  <legend> 글수정  </legend>
	  <form action="./BoardUpdateAction.bo?pageNum=${pageNum }" method="post" >
	      <input type="hidden" name="num" value="${bdto.num }">
		    글쓴이 : <input type="text" name="name" value="<%=bdto.getName() %>"> <br>
		    비밀번호 : <input type="password" name="pass"><br>
		    제목 : <input type="text" name="subject" value="${bdto.subject }"><br>
		    내용 : <br><textarea rows="10" cols="20" name="content" ><%=bdto.getContent() %></textarea>  <br>
		  <input type="submit" value="글쓰기">
	  </form>	
	</fieldset>
	


</body>
</html>