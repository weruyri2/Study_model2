<%@page import="net.goods.db.GoodsDTO"%>
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
		<h1>WebCotent/goods/goods_list.jsp</h1>
	
	<%
		ArrayList goodsList = (ArrayList)request.getAttribute("goodsList"); 
	
	
	%>
 
 	<h1>상품리스트 페이지</h1>

	<table border="1">
	 <tr>
	 	<td><a href="GoodsList.go?item=all">전체보기</a></td>
	 	<td><a href="GoodsList.go?item=best">베스트상품</a></td>
	 	<td><a href="GoodsList.go?item=outwear">외투</a></td>
	 	<td><a href="GoodsList.go?item=fulldress">정장/신사복</a></td>
	 	<td><a href="GoodsList.go?item=Tshirts">티셔츠</a></td>
	 	<td><a href="GoodsList.go?item=shirts">와이셔츠</a></td>
	 	<td><a href="GoodsList.go?item=pants">바지</a></td>
	 	<td><a href="GoodsList.go?item=shoes">신발</a></td>
	 	
	 </tr>

	 <tr>
	<%
		for(int i=0; i<goodsList.size();i++){
			GoodsDTO gdto = (GoodsDTO)goodsList.get(i);
			
			String Image = gdto.getImage();
			
			String array[] = Image.split(",");
					
			String conPath = request.getContextPath()+"/upload";
			
			int j = 0;
			
			String imgPath = conPath+"\\"+array[j];
			
			if(i%4==0){
				%>
				</tr>
				<tr>
				<%
			}
	%>
			<td colspan="2">
				<a href="GoodsDetail.go?num=<%=gdto.getNum()%>"><img src="<%=imgPath%>" width=180 height=150></a> <br>								
				<%=gdto.getName() %> <br>
				<%=gdto.getPrice() %> <br>
			</td>

	<%	
	
		}
	
	%>
		</tr>
		
	</table>
	
		<h2><a href="./BasketList.ba">장바구니로</a></h2>
	
</body>
</html>