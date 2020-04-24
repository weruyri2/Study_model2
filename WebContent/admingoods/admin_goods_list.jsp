<%@page import="net.admin.goods.db.GoodsDTO"%>
<%@page import="net.admin.goods.db.GoodsDAO"%>
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
	<h1>WebCotent/admingoods/admin_goods_list.jsp</h1>
	
	<%
		ArrayList goodsList = (ArrayList)request.getAttribute("goodsList"); 
	
	
	%>
 
 	<h1>관리자 상품리스트 페이지</h1>

	<table border="1">
	 <tr>
	  <td>상품번호</td>
	  <td>카테고리</td>
	  <td>사진</td>
	  <td>상품명</td>
	  <td>컨텐츠</td>
	  <td>사이즈</td>
	  <td>컬러</td>
	  <td>수량</td>
	  <td>가격</td>
	  <td>날짜</td>
	  <td>수정/삭제</td>
	 </tr>
	
	<%
		for(int i=0; i<goodsList.size();i++){
			GoodsDTO gdto = (GoodsDTO)goodsList.get(i);
			
			String Image = gdto.getImage();
			
			String array[] = Image.split(",");
					
			String conPath = request.getContextPath()+"/upload";
			
			String imgPath = conPath+"\\"+array[0];
	%>
		<tr>
			<td><%=gdto.getNum() %></td>
			<td><%=gdto.getCategory() %></td>
			<td>
				<img src="<%=imgPath%>" width=180 height=150>
			</td>
			<td><%=gdto.getName() %></td>
			<td><%=gdto.getCotent() %></td>
			<td><%=gdto.getSize() %></td>
			<td><%=gdto.getColor() %></td>
			<td><%=gdto.getAmount() %></td>
			<td><%=gdto.getPrice() %></td>
			<td><%=gdto.getDate() %></td>
			<td>
			<a href='./GoodsModify.ag?num=<%=gdto.getNum()%>'>수정</a>
			/
			<a href="./GoodsDelete.ag?num=<%=gdto.getNum()%>">삭제</a>
			</td>
		</tr>
	
	<%	
		}
	%>
	</table>
	
	<a href="./GoodsAdd.ag">상품 등록 하기</a>

</body>
</html>