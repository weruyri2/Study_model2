<%@page import="net.goods.db.GoodsDTO"%>
<%@page import="net.basket.db.basketDTO"%>
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
	
	<h1>WebCotent/goods/goods_basket.jsp</h1>
	
	<%
		ArrayList goodsList = (ArrayList)request.getAttribute("goodsList"); 
		ArrayList basketList = (ArrayList)request.getAttribute("basketList"); 
	
	
	%>
	<h1>장바구니 리스트</h1>
	
	<table border="1">
	 <tr>
	  <td>번호</td>
	  <td>사진</td>
	  <td>제품명</td>
	  <td>크기</td>
	  <td>색상</td>
	  <td>수량</td>
	  <td>가격</td>
	  <td>삭제</td>
	 </tr>
	 

	 <%
	 	for(int i=0; i<basketList.size(); i++){
	 		basketDTO bkdto = (basketDTO)basketList.get(i);
	 		GoodsDTO gdto = (GoodsDTO)goodsList.get(i);
	 		
	 		String arr[] = gdto.getImage().split(",");
	 	
	 %>
	 	 <tr>
	 
	 	<td>
	 		<%=bkdto.getB_num()%>
	 	</td>
	 	<td>
	 		<img src="upload/<%=arr[0]%>" width="250" height="200">
	 	</td>
	 	<td>
	 		<%=gdto.getName() %>
	 	</td>
	 	<td>
	 		<%=bkdto.getB_g_size() %>
	 	</td>
	 	<td>
	 		<%=bkdto.getB_g_color() %>
	 	</td>
	 	<td>
	 		<%=bkdto.getB_g_amount() %>
	 	</td>
	 	<td>
	 		<%=gdto.getPrice() %>
	 	</td>
	 	
	 	<td>
	 		<a href="BasketDelete.ba?b_num=<%=bkdto.getB_num() %>">삭제</a>
	 	</td>
	 	
	 	<%
	 	}
	 	%>
	 	</tr>
	
	
	</table>
	
	
	<h3><a href="./OrderStar.or">[구매하기]</a></h3>
	<h3><a href="./GoodsList.go">상품리스트 이동</a></h3>
	<h3><a href="./Main.me">메인 페이지 이동</a></h3>
	
</body>
</html>