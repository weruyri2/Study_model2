<%@page import="sun.security.krb5.internal.PAEncTSEnc"%>
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
	<h1>WebContent/board/boardContent.jsp</h1>
    <h1> 글 본문 내용 보기 </h1>
    <%
 		// 이전페이지에서 저장된 정보를 받아와서 처리 
 		//request.setAttribute("bdto", bdto);
 		//request.setAttribute("pageNum", pageNum);
 		
 		BoardDTO bdto = (BoardDTO)request.getAttribute("bdto");
 		String pageNum = (String)request.getAttribute("pageNum");
    
    %>
    
    
    <table border="1">
      <tr>
        <td>글번호</td><td><%=bdto.getNum() %></td>
        <td>조회수</td><td><%=bdto.getReadcount() %></td>
      </tr>
      <tr>
        <td>작성자</td><td><%=bdto.getName() %></td>
        <td>작성일</td><td><%=bdto.getDate() %></td>
      </tr>
      <tr>
        <td>제목</td><td colspan="3"><%=bdto.getSubject() %></td>
      </tr>
      <tr>
        <td>첨부파일</td><td colspan="3"><a href="./upload/<%=bdto.getFile() %>"><%=bdto.getFile() %></td>
      </tr>
      <tr>
        <td>내용</td><td colspan="3"><%=bdto.getContent() %></td>
      </tr>
      <tr>
         <td colspan="4">
            <input type="button" value="글 수정" 
                   onclick="location.href='./BoardUpdate.bo?num=<%=bdto.getNum() %>&pageNum=<%=pageNum %>'"
            >
            <input type="button" value="글 삭제"
                             onclick="location.href='./BoardDelete.bo?num=<%=bdto.getNum() %>&pageNum=<%=pageNum %>'"
            >
            <input type="button" value="답글 달기"
        		onclick="location.href='./BoardReWrite.bo?num=<%=bdto.getNum() %>&re_ref=<%=bdto.getRe_ref() %>&re_lev=<%=bdto.getRe_lev() %>&re_seq=<%=bdto.getRe_seq() %>'"
            >
            <input type="button" value="목록이동" 
                   onclick="location.href='./BoardList.bo?pageNum=<%=pageNum %>'"
            >
         
         </td>
      </tr>
    
    
    
    </table>	
	
	
	
	
	
	

</body>
</html>