<%@page import="net.board.db.BoardDTO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>WebContent/board/boardList.jsp</h1>
	
	<%
			// 리스트 정보를 저장해서
			//request.setAttribute("boardList", boardList);
			ArrayList boardList = (ArrayList)request.getAttribute("boardList");
			    
			// 페이징 처리 정보 저장  
			//	request.setAttribute("pageNum", pageNum);
			// 	request.setAttribute("count", check);
			// 	request.setAttribute("pageCount", pageCount);
			// 	request.setAttribute("pageBlock", pageBlock);
			// 	request.setAttribute("startPage", startPage);
			// 	request.setAttribute("endPage", endPage);
			
			String pageNum = (String)request.getAttribute("pageNum");
			int count = (int)request.getAttribute("count");
			int pageCount = ((Integer)(request.getAttribute("pageCount"))).intValue();
			int pageBlock = (Integer)(request.getAttribute("pageBlock"));
			int startPage = (Integer)(request.getAttribute("startPage"));
			int endPage = (Integer)(request.getAttribute("endPage"));
			
			
		%>
	
	 <h2><a href="./BoardWrite.bo"> 글 쓰기  </a></h2>
	 	 <h2><a href="./FileBoardWrite.bo"> 파일 글 쓰기  </a></h2>
	
	<table border="1">
	  <tr>
	    <td>번호</td>
	    <td>제목</td>
	    <td>작성자</td>
	    <td>날짜</td>
	    <td>조회수</td>
	    <td>IP</td>
	  </tr>
	  
	  <%
	    for(int i=0;i<boardList.size();i++){ 
             BoardDTO bdto = (BoardDTO) boardList.get(i);
	  %>
		  <tr>
		    <td><%=bdto.getNum() %></td>
		    
		    <td>
		   <%
		      int wid = 0;
		      if(bdto.getRe_lev()>0){
		    	  wid = bdto.getRe_lev() * 10;
		    %>
		       <img src="./board/level.gif" width="<%=wid%>" height="15">
		       <img src="./board/re.gif">
		    <%
		      }
		    %> 
		    
		       <a href="./BoardContent.bo?num=<%=bdto.getNum()%>&pageNum=<%=pageNum%>">
		         <%=bdto.getSubject() %>
		       </a>
		    </td>
		    
		    <td><%=bdto.getName() %></td>
		    <td><%=bdto.getDate() %></td>
		    <td><%=bdto.getReadcount() %></td>
		    <td><%=bdto.getIp() %></td>
		  </tr>
	  <% } %>
	
	
	</table>
	
	<%
	// 페이징처리
	  if(count != 0){

		  // 이전
		  if(startPage > pageBlock){
			  %>
			   <a href="./BoardList.bo?pageNum=<%=startPage-pageBlock%>">[이전]</a>
			  <%
		  }
		  
		  // 페이지  숫자
		  for(int i=startPage;i<=endPage;i++){
			  %>
			  <a href="./BoardList.bo?pageNum=<%=i%>">[<%=i %>]</a> 
			  <%
		  }
		  
		  // 다음
		  if( endPage < pageCount ){
			  %>
			   <a href="./BoardList.bo?pageNum=<%=startPage+pageBlock%>">[다음]</a>
			  <%
		  }
		  
		  
		  
	  }
	%>
	
	
	
	
	
	
	
	
	
	

</body>
</html>