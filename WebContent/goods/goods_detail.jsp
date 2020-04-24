<%@page import="net.goods.db.GoodsDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
		<h1>WebCotent/goods/goods_list.jsp</h1>
	
	<%
	 GoodsDTO gdto = (GoodsDTO)request.getAttribute("gdto");
	
	String conPath = request.getContextPath()+"/upload";
	
	String Image = gdto.getImage();
	
	String array[] = Image.split(",");
	
	String imgPath = conPath+"\\"+array[0];
	
	%>
	
	<h1> 제품 상세 보기 </h1>
	
	<form action="" method="post" name="fr">
	<input type="hidden" name="num" value="<%=gdto.getNum() %>" >

	<table border="1">
	<tr>
		<td><img src="<%=imgPath%>" 
			idth="500" height="250"
		>
		</td>
		<td>
			상품명 : <%=gdto.getName() %> <br><hr>
			판매가격 : <%=gdto.getPrice() %> <br><hr>
			수량 : <input type="text" name="amount" value=1> <br><hr>
			남은 수량 : <%=gdto.getAmount() %> <br><hr>
			
			크기 :
			<select name="size">
			 <option value="">크기를 선택하세요</option>
				
				<c:forTokens var="size" items="${gdto.size }"
					delims=",">
					<option value="${size }">${size} </option>
				
				</c:forTokens>
			</select>
			<br>
			
			색상 :
			<select name="color">
			 <option value="">색상을 선택하세요</option>
				
				<c:forTokens var="color" items="${gdto.color}"
					delims=",">
				<option value="${color }">${color} </option>
				
				</c:forTokens>
			</select>
			<br>
			<hr><hr>
			
			<a href="javascript:isBasket()">[장바구니 담기]</a>
			<a href="javascript:isBuy()">[구매하기]</a>
		
		
		</td>
	</tr>
	
	<tr>
		<td colspan="2">
		 <img src="./upload/<%=gdto.getImage().split(",")[1] %>"
		 	width="500" height="250"
		 	>
		</td>
	
	</tr>
	
	</table>
	</form>
	
	  <script type="text/javascript">
     function isBasket() {
    	 //alert(" 장바구니 버튼 클릭 ");
    	 
    	 // 크기 , 색상 선택없을시 요소 포커싱
    	 if(document.fr.size.value==""){
    		 alert(" 크기를 선택 하시오 ! ");
    		 document.fr.size.focus();
    		 return;
    	 }
    	 if(document.fr.color.value==""){
    		 alert(" 색상을 선택 하시오 ! ");
    		 document.fr.color.focus();
    		 return;
    	 }
    	 
    	 // "장바구니에 저장 하시겠습니까?"
         // 확인 - 페이지이동(submit)
         // 취소 - 변화 없음    	 
    	 var check = confirm("장바구니에 저장 하시겠습니까?");
    	 
    	 if(check){
    		 document.fr.action="./BasketAdd.ba";
    		 document.fr.submit();
    	 }else{
    		 return;
    	 }
	 }
     
     function isBuy() {
    	 
    	 // 크기 , 색상 선택없을시 요소 포커싱
    	 if(document.fr.size.value==""){
    		 alert(" 크기를 선택 하시오 ! ");
    		 document.fr.size.focus();
    		 return;
    	 }
    	 if(document.fr.color.value==""){
    		 alert(" 색상을 선택 하시오 ! ");
    		 document.fr.color.focus();
    		 return;
    	 }
    	 
    	 var check = confirm("장바구니에 저장 하시겠습니까?");
    	 
    	 if(check){
    		 document.fr.action="/BasketAdd.ba";
    		 document.fr.submit();
    	 }else{
    		 alert(" 결제 페이지로 이동합니다. ");
    		 document.fr.action="";
    		 document.fr.submit();
    	 }
	 }
  </script>

</body>
</html>